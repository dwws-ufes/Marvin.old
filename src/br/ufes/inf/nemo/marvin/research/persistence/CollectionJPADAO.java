package br.ufes.inf.nemo.marvin.research.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.marvin.research.domain.InCollection;

/**
 * TODO: document this type.
 *
 * @author Thiago Rocha Salvatore
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Stateless
public class CollectionJPADAO extends BaseJPADAO<InCollection> implements CollectionDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The application's persistent context provided by the application server. */
	@PersistenceContext
	private EntityManager entityManager;

	/** @see br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO#getEntityManager() */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/** @see br.ufes.inf.nemo.marvin.research.persistence.CollectionDAO#retrieveByTitle(java.lang.String) */
	@Override
	public InCollection retrieveByTitle(String title) {
		String jpql = "SELECT c FROM Collection c where c.title = :title";
		TypedQuery<InCollection> query = entityManager.createQuery(jpql, InCollection.class);
		query.setParameter("title", title);

		if (query.getResultList().size() > 0) { return query.getResultList().get(0); }
		return null;

	}
}
