package br.ufes.inf.nemo.marvin.research.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.research.domain.AcademicScore;
import br.ufes.inf.nemo.marvin.research.domain.ConferencePaper;
import br.ufes.inf.nemo.marvin.research.domain.Publication;
import br.ufes.inf.nemo.marvin.research.domain.PublicationScore;
import br.ufes.inf.nemo.marvin.research.domain.Qualification;
import br.ufes.inf.nemo.marvin.research.domain.Qualis;
import br.ufes.inf.nemo.marvin.research.domain.Score;
import br.ufes.inf.nemo.marvin.research.domain.ScoreSystem;
import br.ufes.inf.nemo.marvin.research.domain.Venue;
import br.ufes.inf.nemo.marvin.research.domain.VenueCategory;
import br.ufes.inf.nemo.marvin.research.exceptions.ScoreSystemNotRegisteredException;
import br.ufes.inf.nemo.marvin.research.persistence.PublicationDAO;
import br.ufes.inf.nemo.marvin.research.persistence.QualificationDAO;
import br.ufes.inf.nemo.marvin.research.persistence.ScoreDAO;
import br.ufes.inf.nemo.marvin.research.persistence.ScoreSystemDAO;

@Stateless
@RolesAllowed({ "SysAdmin" })
public class CalculateAcademicsScoresServiceBean implements CalculateAcademicsScoresService {

	/** Serialization id. */
	private static final long serialVersionUID = -494464487767933751L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(CalculateAcademicsScoresServiceBean.class.getCanonicalName());

	@EJB
	private PublicationDAO publicationDAO;

	@EJB
	private ScoreSystemDAO scoreSystemDAO;

	@EJB
	private ScoreDAO scoreDAO;

	@EJB
	private QualificationDAO qualificationDAO;

	@Override
	public List<AcademicScore> calculateAcademicsScore(List<Academic> academics, int startYear, int endYear) throws ScoreSystemNotRegisteredException {
		try {
			// Retrieve the current scores
			ScoreSystem currentScoreSystem = scoreSystemDAO.retrieveCurrentScoreSystem();
			List<Score> currentScoresList = scoreDAO.retrieveByScoreSystem(currentScoreSystem);
			Map<Qualis, Score> scoreQualisMap = new HashMap<Qualis, Score>();
			for (Score s : currentScoresList) {
				scoreQualisMap.put(s.getQualis(), s);
			}

			List<AcademicScore> academicScoreList = new ArrayList<AcademicScore>();
			for (Academic a : academics) {
				AcademicScore as = new AcademicScore();
				as.setAcademic(a);

				List<Publication> publicationsList = publicationDAO.retrieveByAcademicAndYearRange(a, startYear, endYear);
				List<PublicationScore> publicationScoreList = new ArrayList<PublicationScore>();

				// Stores the academic score in each category
				int scoreConferenceAcademic = 0;
				int scoreJournalAcademic = 0;

				for (Publication p : publicationsList) {
					Venue pubVenue = p.getVenue();
					if (pubVenue == null) continue; // No way of calculating the score of publications without associated venues.
					try {
						// FIXME: allow user to choose before or after.
						// Qualification quaPubVenue = qualificationDAO.retrieveClosestBeforeByVenueAndYear(pubVenue, p.getYear());
						Qualification quaPubVenue = qualificationDAO.retrieveClosestAfterByVenueAndYear(pubVenue, p.getYear());
						PublicationScore publicationScore = new PublicationScore();

						publicationScore.setPublication(p);

						// Stores the current publication score
						int currentScore = 0;
						boolean venueIsConference = pubVenue.getCategory().equals(VenueCategory.CONFERENCE);
						
						// FIXME: Improve the domain classes regarding publications.
						// Journal papers can also be full or not (at least according to Lattes). Journal and conference papers have
						// a lot of similarities. Maybe a common superclass would help in some application code. Moreover, names of
						// classes and attributes follow BibTeX names. They could be closer to the real world instead.
						
						// Only counts if full paper.
						if (venueIsConference && ! ((ConferencePaper)p).isFullPaper()) continue;

						Qualis qualis = quaPubVenue.getQualis();
						Score score = scoreQualisMap.get(qualis);

						if (venueIsConference) currentScore += score.getScoreConference();
						else currentScore += score.getScoreJournal();

						publicationScore.setScore(currentScore);

						if (venueIsConference) scoreConferenceAcademic += currentScore;
						else scoreJournalAcademic += currentScore;

						publicationScoreList.add(publicationScore);
					}
					catch (PersistentObjectNotFoundException e) {
						// If there is no qualification that applies to the current publication,
						// skip this publication and go to the next one;
						logger.log(Level.WARNING, "No qualification from an year that is less than or equal to the publication's year was found.");
					}
					catch (MultiplePersistentObjectsFoundException e) {
						// This is a bug. Log and throw a runtime exception.
						logger.log(Level.SEVERE, "Multiple qualifications found that have the same year and belong to the same venue.", e);
						throw new EJBException(e);
					}
				}
				as.setPublicationsScores(publicationScoreList);
				as.setScoreConference(scoreConferenceAcademic);
				as.setScoreJournal(scoreJournalAcademic);
				as.setScoreTotal(scoreConferenceAcademic + scoreJournalAcademic);
				academicScoreList.add(as);
			}
			return academicScoreList;
		}
		catch (PersistentObjectNotFoundException e) {
			// If there is no Score System that is currently active, throw an
			// exception from the domain.
			logger.log(Level.WARNING, "No currently active score system was found.");
			throw new ScoreSystemNotRegisteredException();
		}
		catch (MultiplePersistentObjectsFoundException e) {
			// This is a bug. Log and throw a runtime exception.
			logger.log(Level.SEVERE, "Multiple score systems found that are currently active.", e);
			throw new EJBException(e);
		}
	}
}
