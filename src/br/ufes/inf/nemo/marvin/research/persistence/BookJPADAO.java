package br.ufes.inf.nemo.marvin.research.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.marvin.research.domain.Book;

/**
 * TODO: document this type.
 *
 * @author Thiago Rocha Salvatore
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Stateless
public class BookJPADAO extends BaseJPADAO<Book> implements BookDAO {
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

	/** @see br.ufes.inf.nemo.marvin.research.persistence.BookDAO#retrieveByTitle(java.lang.String) */
	@Override
	public Book retrieveByTitle(String title) {
		String jpql = "SELECT b FROM Book b where b.title = :title";
		TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
		query.setParameter("title", title);

		if (query.getResultList().size() > 0) { return query.getResultList().get(0); }
		return null;

	}
}
