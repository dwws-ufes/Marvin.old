package br.ufes.inf.nemo.marvin.research.persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.marvin.research.domain.Score;
import br.ufes.inf.nemo.marvin.research.domain.ScoreSystem;
import br.ufes.inf.nemo.marvin.research.domain.Score_;

@Stateless
public class ScoreJPADAO extends BaseJPADAO<Score> implements ScoreDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ScoreJPADAO.class.getCanonicalName());

	/** The application's persistent context provided by the application server. */
	@PersistenceContext
	private EntityManager entityManager;

	/** @see br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO#getEntityManager() */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<Score> retrieveByScoreSystem(ScoreSystem scoreSystem) {
		logger.log(Level.FINE, "Retrieving the Score count of ScoreSystem", new Object[] { scoreSystem.getStartDate(), scoreSystem.getEndDate() });

		// Constructs the query over the Score class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Score> cq = cb.createQuery(Score.class);
		Root<Score> root = cq.from(Score.class);

		// Filters the query with the scoreSystem.
		cq.where(cb.equal(root.get(Score_.scoreSystem), scoreSystem));
		List<Score> result = entityManager.createQuery(cq).getResultList();
		logger.log(Level.INFO, "Retrieve score of scoreSystem \"{0}\" returned {1} results.", new Object[] { scoreSystem.getStartDate(), scoreSystem.getEndDate(), result.size() });

		return result;

	}
}
