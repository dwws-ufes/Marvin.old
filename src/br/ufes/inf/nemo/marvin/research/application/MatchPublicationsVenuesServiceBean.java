package br.ufes.inf.nemo.marvin.research.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

import org.apache.commons.text.similarity.LevenshteinDistance;

import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.research.domain.ConferencePaper;
import br.ufes.inf.nemo.marvin.research.domain.JournalPaper;
import br.ufes.inf.nemo.marvin.research.domain.Publication;
import br.ufes.inf.nemo.marvin.research.domain.Venue;
import br.ufes.inf.nemo.marvin.research.persistence.PublicationDAO;
import br.ufes.inf.nemo.marvin.research.persistence.VenueDAO;

/**
 * TODO: document this type.
 *
 * @author Allan Araujo
 * @author Gabriel Macena
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Stateless
public class MatchPublicationsVenuesServiceBean implements MatchPublicationsVenuesService {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(MatchPublicationsVenuesServiceBean.class.getCanonicalName());

	/** The minimum similarity to consider a match. */
	private static final double MIN_SIMILARITY = 0.7; // FIXME: this could be part of MarvinConfiguration.

	/**
	 * The minimum percentage for the size of an alternate name with respect to venue name in order to consider it in
	 * matching.
	 */
	private static final double MIN_PERCENTAGE_ALTERNATE_VENUE_NAME = 0.5; // FIXME: this could be part of
																																					// MarvinConfiguration.

	/** DAO for Publication objects. */
	@EJB
	private PublicationDAO publicationDAO;

	/** DAO for Venue objects. */
	@EJB
	private VenueDAO venueDAO;

	/**
	 * @see br.ufes.inf.nemo.marvin.research.application.MatchPublicationsVenuesService#matchPublicationsVenues(br.ufes.inf.nemo.marvin.research.application.MatchPublicationsEvent)
	 */
	@Override
	public void matchPublicationsVenues(@Observes MatchPublicationsEvent event) {
		Academic researcher = event.getResearcher();
		logger.log(Level.INFO, "Matching publications with venues for researcher: {0}", new Object[] { (researcher == null) ? "(empty)" : researcher.getName() });

		// Retrieves the publication from the researcher, if present. Otherwise, retrieve all.
		List<Publication> publications = null;
		if (researcher == null) publications = publicationDAO.retrieveAll();
		else publications = publicationDAO.retrieveByAcademic(researcher);

		// Retrieve all venues, separate them in journals and conferences, indexed by their names.
		List<Venue> venues = venueDAO.retrieveAll();
		Map<String, Venue> journals = new HashMap<>();
		Map<String, Venue> journalsByISSN = new HashMap<>();
		Map<String, Venue> conferences = new HashMap<>();
		for (Venue v : venues)
			switch (v.getCategory()) {
			case CONFERENCE:
				conferences.put(v.getName().toLowerCase().trim(), v);
				break;
			case JOURNAL:
				journals.put(v.getName().toLowerCase().trim(), v);

				// Also indexes journals by ISSN, removing any non-numerical character (e.g. "-").
				journalsByISSN.put(v.getIssn().replaceAll("[^\\d]", ""), v);
				break;
			}

		// Goes through all publications and tries to match them with venues.
		for (Publication publication : publications) {
			// Only tries to match publications that haven't already been matched.
			if (publication.getVenue() == null) {
				// Checks the type of publication and performs the match using the appropriate mape of known venues.
				if (publication instanceof ConferencePaper) findMatchByName(publication, ((ConferencePaper) publication).getBookTitle(), ((ConferencePaper) publication).getEventName(), conferences, (researcher == null) ? "(empty)" : researcher.getName());
				else if (publication instanceof JournalPaper) {
					if (!findMatchByISSN(publication, ((JournalPaper) publication).getJournal(), ((JournalPaper) publication).getIssn(), journalsByISSN, (researcher == null) ? "(empty)" : researcher.getName())) {
						findMatchByName(publication, ((JournalPaper) publication).getJournal(), null, journals, (researcher == null) ? "(empty)" : researcher.getName());
					}
				}
			}
		}
	}

	/**
	 * Attempts to match a publication with a venue using the publication's declared ISSN. To be used only with
	 * publications that have ISSN (e.g. journal publications).
	 * 
	 * @param paper
	 *          The publication to match.
	 * @param paperVenue
	 *          The name of the venue declared for the publication.
	 * @param issn
	 *          The ISSN of the publication.
	 * @param knownVenuesByISSN
	 *          The map of known venues, indexed by ISSN.
	 * @param researcherName
	 *          The name of the researcher, for logging purposes only.
	 * @return
	 */
	private boolean findMatchByISSN(Publication paper, String paperVenue, String issn, Map<String, Venue> knownVenuesByISSN, String researcherName) {
		logger.log(Level.INFO, "Trying to match (by ISSN) paper: {0}; declared venue: {1}; declared ISSN: {2}; researcher: {3}", new Object[] { paper.getTitle(), paperVenue, issn, researcherName });

		// Remove any non-numerical character from the given ISSN.
		issn = issn.replaceAll("[^\\d]", "");

		// Tries to find a match in the map of known venues (by ISSN).
		if (knownVenuesByISSN.containsKey(issn)) {
			paper.setVenue(knownVenuesByISSN.get(issn));
			logger.log(Level.INFO, "Match (by ISSN) found for paper: {0}; declared venue: {1}; declared ISSN: {2}; matched venue: {3}; researcher: {4}", new Object[] { paper.getTitle(), paperVenue, issn, paper.getVenue().getName(), researcherName });
			publicationDAO.save(paper);
		}
		else logger.log(Level.INFO, "No match (by ISSN) for paper: {0}; declared venue: {1}; declared ISSN: {2}; researcher: {3}", new Object[] { paper.getTitle(), paperVenue, issn, researcherName });

		return false;
	}

	/**
	 * Attempts to match a publication with a venue by comparing the publication's declared venue with all known venues
	 * and calculating the string similarity between names. Uses the one with greater similarity among those who are above
	 * the minimum similarity value (a constant attribute of the class).
	 * 
	 * @param paper
	 *          The publication to match.
	 * @param paperVenue
	 *          The name of the venue declared for the publication.
	 * @param alternateName
	 *          An alternate name of the venue, if one is available (e.g., for conference papers, there's the event name
	 *          and the proceedings name).
	 * @param knownVenues
	 *          The map of known venues (the type of venue should be the same as the paper).
	 * @param researcherName
	 *          The name of the researcher, for logging purposes only.
	 */
	private void findMatchByName(Publication paper, String paperVenue, String alternateName, Map<String, Venue> knownVenues, String researcherName) {
		logger.log(Level.INFO, "Trying to match (by name) paper: {0}; declared venue: {1}; researcher: {2}", new Object[] { paper.getTitle(), paperVenue, researcherName });

		// Uses lower case for the declared venue and alternate name. Initializes the match and the best similarity so far.
		String declaredVenue = paperVenue.toLowerCase().trim();
		alternateName = (alternateName == null) ? null : alternateName.toLowerCase().trim();
		String matchedVenue = null;
		double bestSimilarity = 0;

		// Goes through all known venues and looks for the greatest similarity, above the minimum.
		for (String knownVenue : knownVenues.keySet()) {
			double similarity = calculateSimilarity(declaredVenue, knownVenue.toLowerCase());
			if (similarity > MIN_SIMILARITY && similarity > bestSimilarity) {
				bestSimilarity = similarity;
				matchedVenue = knownVenue;
			}

			// If a valid alternate name was provided, also calculates similarity using it.
			if (validAlternateName(paperVenue, alternateName)) {
				similarity = calculateSimilarity(alternateName, knownVenue.toLowerCase());
				if (similarity > MIN_SIMILARITY && similarity > bestSimilarity) {
					bestSimilarity = similarity;
					matchedVenue = knownVenue;
				}
			}
		}

		// If found, perform the match.
		if (matchedVenue != null) {
			logger.log(Level.INFO, "Match (by name) found for paper: {0}; declared venue: {1}; matched venue: {2}; researcher: {3}", new Object[] { paper.getTitle(), paperVenue, matchedVenue, researcherName });
			paper.setVenue(knownVenues.get(matchedVenue));
			publicationDAO.save(paper);
		}
		else logger.log(Level.INFO, "No match (by name) for paper: {0}; declared venue: {1}; researcher: {2}", new Object[] { paper.getTitle(), paperVenue, researcherName });
	}

	/**
	 * Verifies if an alternate name is valid with respect to a venue name. The alternate name is valid if it's not null
	 * and (a) it's longer than the venue name; or (b) it's not too short w.r.t. the venue name. "Too short" is defined by
	 * a percentage (a constant attribute of the class).
	 * 
	 * @param declaredVenue
	 *          The name of the venue.
	 * @param alternateName
	 *          The alternate name for the venue.
	 * @return <code>true</code> if the alternate name is valid, <code>false</code> otherwise.
	 */
	private boolean validAlternateName(String declaredVenue, String alternateName) {
		// Trivial case: if alternate name is null, can't be used.
		if (alternateName == null) return false;

		// Calculates the different in size from the declared venue name and alternate name.
		int lVenue = declaredVenue.length();
		int lBook = alternateName.length();
		double diff = lVenue - lBook;

		// If the alternate name is longer than the venue name, it's OK to use.
		if (diff < 0) return true;

		// Otherwise, checks if it is at least a minimum percentage of the size of the venue name.
		diff /= (lVenue > lBook ? lVenue : lBook);
		return (diff < MIN_PERCENTAGE_ALTERNATE_VENUE_NAME);
	}

	/**
	 * Returns the similarity between two strings as a percentage (value between 0 and 1).
	 * 
	 * @param s1
	 *          The first string.
	 * @param s2
	 *          The second string.
	 * @return A value between 0 and 1, indicating the percentage of similarity.
	 */
	private static double calculateSimilarity(String s1, String s2) {
		// Determines the length of the longest string.
		int len = s1.length() > s2.length() ? s1.length() : s2.length();

		// Calculates the Levenshtein ratio: 1 - dist/len, dist being the Levenshtein distance.
		LevenshteinDistance levenshtein = new LevenshteinDistance();
		double dist = levenshtein.apply(s1, s2);
		double ratio = 1 - dist / len;
		return ratio;
	}
}
