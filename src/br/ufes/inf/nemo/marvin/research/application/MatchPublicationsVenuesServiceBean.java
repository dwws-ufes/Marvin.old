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
import org.apache.commons.text.similarity.SimilarityScore;

import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.research.domain.ConferencePaper;
import br.ufes.inf.nemo.marvin.research.domain.JournalPaper;
import br.ufes.inf.nemo.marvin.research.domain.Publication;
import br.ufes.inf.nemo.marvin.research.domain.Venue;
import br.ufes.inf.nemo.marvin.research.domain.VenueCategory;
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

	/** TODO: document this field. */
	@EJB
	private PublicationDAO publicationDAO;
	
	/** TODO: document this field. */
	@EJB
	private VenueDAO venueDAO;

	/** @see br.ufes.inf.nemo.marvin.research.application.MatchPublicationsVenuesService#matchPublicationsVenues(br.ufes.inf.nemo.marvin.research.application.MatchPublicationsEvent) */
	@Override
	public void matchPublicationsVenues(@Observes MatchPublicationsEvent event) {
		Academic researcher = event.getResearcher();
		logger.log(Level.INFO, "Matching publications with venues for researcher: {0}", new Object[] { (researcher == null) ? "(empty)" : researcher.getName() });

		List<Publication> publications = null;
		
		if (researcher == null) publications = publicationDAO.retrieveAll();
		else publications = publicationDAO.retrieveByAcademic(researcher);
		
		List<Venue> venues = venueDAO.retrieveAll();
		
		Map<String, Venue> journals = new HashMap<String, Venue>();
		Map<String, Venue> conferences = new HashMap<String, Venue>();
		
		for (Venue v : venues) {
			if (v.getCategory().equals(VenueCategory.CONFERENCE))
				conferences.put(v.getName().toLowerCase().trim(), v);
			else
				journals.put(v.getName().toLowerCase().trim(), v);
		}
		
		for (Publication publication : publications) {
			if (publication.getVenue() == null) {
				System.out.println("Publication: " + publication.getTitle());
				if (publication instanceof ConferencePaper) {
					ConferencePaper paper = (ConferencePaper) publication;
					String conferenceProceedings = paper.getBookTitle().toLowerCase().trim();
					System.out.println("Publication " + publication.getTitle() + " is conference!" );

					double threshold = 0.3;
					LevenshteinDistance similarityFunction = new LevenshteinDistance();
					double lower = threshold;
					String foundConference = conferenceProceedings;
					for (String conferenceName : conferences.keySet()) {
						// Since the conference proceedings never match the actual name of the conference, checks 
						// similarity using Levenshtein Distance. Uses the lowest distance.
						double distance = calculateVenueSimilarity(similarityFunction, conferenceProceedings, conferenceName);
						if (distance < lower) {
							foundConference = conferenceName;
							lower = distance;
						}
					}
					if (conferences.containsKey(foundConference)) {
						paper.setVenue(conferences.get(foundConference));
						publicationDAO.save(paper);
					}
				}
				else if (publication instanceof JournalPaper) {
					JournalPaper paper = (JournalPaper) publication;
					String journalName = paper.getJournal().toLowerCase().trim();
					System.out.println("Publication " + publication.getTitle() + " is journal!" );
					if (journals.containsKey(journalName)) {
						paper.setVenue(journals.get(journalName));
						publicationDAO.save(paper);
					}				
				}
			}
		}
	}

	private static double calculateVenueSimilarity(SimilarityScore<Integer> similarityFunction, String lattesName, String qualisName) {
		double score = similarityFunction.apply(lattesName, qualisName);
		score /= (lattesName.length() + qualisName.length());
		return score;
	}
	
	public static void main(String[] args) {
		LevenshteinDistance distance = new LevenshteinDistance(30);
		System.out.println("FOIS/SSC: " + calculateVenueSimilarity(distance,
				"Formal Ontology in Information Systems",
				"SSC - Social Simulation Conference"));
		System.out.println("ER/SSC: " + calculateVenueSimilarity(distance,
				"Conceptual Modeling - ER 2015",
				"SSC - Social Simulation Conference"));
		System.out.println("APCCM/ER: " + calculateVenueSimilarity(distance,
				"Proceedings of the 10th Asia-Pacific Conference on Conceptual Modelling",
				"ER - International Conference on Conceptual Modeling"));
		System.out.println("SEAMS: " + calculateVenueSimilarity(distance,
				"Proceedings of the 8th International Symposium on Software Engineering for Adaptive and Self-Managing Systems",
				"SEAMS - International Symposium on Software Engineering for Adaptive and Self-Managing Systems"));
		System.out.println("ER@BR/SET: " + calculateVenueSimilarity(distance,
				"Requirements Engineering@Brazil - ER@BR2013",
				"SET - Software Engineering Track"));
		System.out.println("iStar/SSC: " + calculateVenueSimilarity(distance,
				"4th i* Workshop Program",
				"SSC - Social Simulation Conference"));
		System.out.println("WebMedia: " + calculateVenueSimilarity(distance,
				"WebMedia '05 Proceedings of the 11th Brazilian Symposium on Multimedia and the web",
				"WebMedia - Brazilian Symposium on Multimedia and the Web"));
	}
	
}
