package br.ufes.inf.nemo.marvin.research.persistence;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.research.domain.Publication;
import br.ufes.inf.nemo.marvin.research.domain.Publication_;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Stateless
public class PublicationJPADAO extends BaseJPADAO<Publication> implements PublicationDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(PublicationJPADAO.class.getCanonicalName());

	/** The application's persistent context provided by the application server. */
	@PersistenceContext
	private EntityManager entityManager;

	/** @see br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO#getEntityManager() */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/** @see br.ufes.inf.nemo.marvin.research.persistence.PublicationDAO#retrieveCountByAcademic(br.ufes.inf.nemo.marvin.core.domain.Academic) */
	@Override
	public long retrieveCountByAcademic(Academic academic) {
		logger.log(Level.FINE, "Retrieving the publication count of academic \"{0}\" ({1})...", new Object[] { academic.getName(), academic.getEmail() });

		// Constructs the query over the Publication class, with Long result.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Publication> root = cq.from(Publication.class);
		cq.select(cb.count(root));

		// Filters the query with the academic.
		cq.where(cb.equal(root.get(Publication_.owner), academic));

		// Retrieve the value and return.
		TypedQuery<Long> query = entityManager.createQuery(cq);
		Long count = query.getSingleResult();
		logger.log(Level.INFO, "Retrieve publication count of academic \"{0}\" ({1}) returned \"{2}\"", new Object[] { academic.getName(), academic.getEmail(), count });
		return count;
	}
}
