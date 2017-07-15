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
import br.ufes.inf.nemo.marvin.research.domain.Qualis;
import br.ufes.inf.nemo.marvin.research.domain.Qualis_;

@Stateless
public class QualisJPADAO extends BaseJPADAO<Qualis> implements QualisDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(QualisJPADAO.class.getCanonicalName());

	/** The application's persistent context provided by the application server. */
	@PersistenceContext
	private EntityManager entityManager;

	/** @see br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO#getEntityManager() */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Qualis retrieveByLevel(String level) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {
		logger.log(Level.FINE, "Retrieving the qualis whose level is \"{0}\"...", level);

		// Constructs the query over the Qualis class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Qualis> cq = cb.createQuery(Qualis.class);
		Root<Qualis> root = cq.from(Qualis.class);

		// Filters the query with the email.
		cq.where(cb.equal(root.get(Qualis_.level), level));
		Qualis result = executeSingleResultQuery(cq, level);
		logger.log(Level.INFO, "Retrieve qualis by the level \"{0}\" returned \"{1}\"", new Object[] { level, result });
		return result;
	}

}
