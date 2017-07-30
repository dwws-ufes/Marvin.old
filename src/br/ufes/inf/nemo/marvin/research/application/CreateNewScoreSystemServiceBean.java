package br.ufes.inf.nemo.marvin.research.application;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.research.domain.Qualis;
import br.ufes.inf.nemo.marvin.research.domain.Score;
import br.ufes.inf.nemo.marvin.research.domain.ScoreSystem;
import br.ufes.inf.nemo.marvin.research.persistence.QualisDAO;
import br.ufes.inf.nemo.marvin.research.persistence.ScoreDAO;
import br.ufes.inf.nemo.marvin.research.persistence.ScoreSystemDAO;

@Stateless
@RolesAllowed({ "SysAdmin" })
public class CreateNewScoreSystemServiceBean implements CreateNewScoreSystemService {

	/** Serialization id. */
	private static final long serialVersionUID = 4963783587733653515L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(CreateNewScoreSystemServiceBean.class.getCanonicalName());

	@EJB
	private QualisDAO qualisDAO;

	@EJB
	private ScoreDAO scoreDAO;

	@EJB
	private ScoreSystemDAO scoreSystemDAO;

	@Override
	public List<Score> constructScoresFromQualis() {
		// TODO Auto-generated method stub
		List<Qualis> registeredQualis = qualisDAO.retrieveAll();
		List<Score> scores = new LinkedList<Score>();
		for (Qualis q : registeredQualis) {
			Score s = new Score(q);
			scores.add(s);
		}
		return scores;
	}

	@Override
	public void createNewScoreSystem(List<Score> scores) {
		// TODO Auto-generated method stub
		try {
			ScoreSystem currentScoreSystem = scoreSystemDAO.retrieveCurrentScoreSystem();

			// There is at least one score system registered
			Date now = new Date(System.currentTimeMillis());
			ScoreSystem newScoreSystem = new ScoreSystem(now);
			if (currentScoreSystem != null) {
				currentScoreSystem.setEndDate(now);
				scoreSystemDAO.save(currentScoreSystem);
			}
			scoreSystemDAO.save(newScoreSystem);
			for (Score s : scores) {
				s.setScoreSystem(newScoreSystem);
				scoreDAO.save(s);
			}
		}
		catch (MultiplePersistentObjectsFoundException e) {
			// This is a bug. Log and throw a runtime exception.
			logger.log(Level.SEVERE, "Multiple score systems that are active at the moment.");
			throw new EJBException(e);
		}
		catch (PersistentObjectNotFoundException e) {
			// Not an issue, let the new Score System be created normally
			Date now = new Date(System.currentTimeMillis());
			ScoreSystem newScoreSystem = new ScoreSystem(now);
			scoreSystemDAO.save(newScoreSystem);
			for (Score s : scores) {
				s.setScoreSystem(newScoreSystem);
				scoreDAO.save(s);
			}
		}
	}

}
