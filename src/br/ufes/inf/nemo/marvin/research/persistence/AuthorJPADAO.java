package br.ufes.inf.nemo.marvin.research.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.marvin.research.domain.Author;

/**
 * TODO: document this type.
 *
 * @author Thiago Rocha Salvatore
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Stateless
public class AuthorJPADAO extends BaseJPADAO<Author> implements AuthorDAO {
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

	/** @see br.ufes.inf.nemo.marvin.research.persistence.AuthorDAO#retrieveByName(java.lang.String) */
	@Override
	public Author retrieveByName(String name) {
		String jpql = "SELECT a FROM Author a where a.name = :name";
		TypedQuery<Author> query = entityManager.createQuery(jpql, Author.class);
		query.setParameter("name", name);

		if (query.getResultList().size() > 0) { return query.getResultList().get(0); }
		return null;
	}
}
