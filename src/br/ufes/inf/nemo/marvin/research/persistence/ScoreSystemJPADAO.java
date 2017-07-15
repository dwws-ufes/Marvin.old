package br.ufes.inf.nemo.marvin.research.persistence;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.research.domain.ScoreSystem;
import br.ufes.inf.nemo.marvin.research.domain.ScoreSystem_;

@Stateless
public class ScoreSystemJPADAO extends BaseJPADAO<ScoreSystem> implements ScoreSystemDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ScoreSystemJPADAO.class.getCanonicalName());

	/** The application's persistent context provided by the application server. */
	@PersistenceContext
	private EntityManager entityManager;

	/** @see br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO#getEntityManager() */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public ScoreSystem retrieveCurrentScoreSystem() throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {
		logger.log(Level.FINE, "Retrieving the current score system.");

		// Constructs the query over the Publication class, with Long result.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ScoreSystem> cq = cb.createQuery(ScoreSystem.class);
		Root<ScoreSystem> root = cq.from(ScoreSystem.class);

		// Filters the query with the academic.
		cq.where(cb.isNull(root.get(ScoreSystem_.endDate)));

		// Retrieve the value and return.
		ScoreSystem currentScoreSystem = executeSingleResultQuery(cq);
		logger.log(Level.INFO, "Retrieve current score system.");
		return currentScoreSystem;
	}
}
