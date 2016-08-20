package br.ufes.inf.nemo.marvin.research.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.marvin.research.domain.InProceedings;

/**
 * TODO: document this type.
 *
 * @author Thiago Rocha Salvatore
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Stateless
public class ProceedingJPADAO extends BaseJPADAO<InProceedings> implements ProceedingDAO {
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

	/** @see br.ufes.inf.nemo.marvin.research.persistence.ProceedingDAO#retrieveByTitle(java.lang.String) */
	@Override
	public InProceedings retrieveByTitle(String title) {
		String jpql = "SELECT a FROM Proceeding a where a.title = :title";
		TypedQuery<InProceedings> query = entityManager.createQuery(jpql, InProceedings.class);
		query.setParameter("title", title);

		if (query.getResultList().size() > 0) { return query.getResultList().get(0); }
		return null;
	}

	/** @see br.ufes.inf.nemo.marvin.research.persistence.ProceedingDAO#retrieveByDOI(java.lang.String) */
	@Override
	public InProceedings retrieveByDOI(String doi) {
		String jpql = "SELECT a FROM Proceeding a where a.doi = :doi";
		TypedQuery<InProceedings> query = entityManager.createQuery(jpql, InProceedings.class);
		query.setParameter("doi", doi);

		if (query.getResultList().size() > 0) { return query.getResultList().get(0); }
		return null;
	}
}
