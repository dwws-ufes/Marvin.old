package br.ufes.inf.nemo.marvin.research.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.marvin.research.domain.Article;

/**
 * TODO: document this type.
 *
 * @author Thiago Rocha Salvatore
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Stateless
public class ArticleJPADAO extends BaseJPADAO<Article> implements ArticleDAO {
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

	/** @see br.ufes.inf.nemo.marvin.research.persistence.ArticleDAO#getArticleByTitle(java.lang.String) */
	@Override
	public Article getArticleByTitle(String title) {
		String jpql = "SELECT a FROM Article a where a.title = :title";
		TypedQuery<Article> query = entityManager.createQuery(jpql, Article.class);
		query.setParameter("title", title);

		if (query.getResultList().size() > 0) { return query.getResultList().get(0); }
		return null;

	}

	/** @see br.ufes.inf.nemo.marvin.research.persistence.ArticleDAO#getArticleByDOI(java.lang.String) */
	@Override
	public Article getArticleByDOI(String doi) {
		String jpql = "SELECT a FROM Article a where a.doi = :doi";
		TypedQuery<Article> query = entityManager.createQuery(jpql, Article.class);
		query.setParameter("doi", doi);

		if (query.getResultList().size() > 0) { return query.getResultList().get(0); }
		return null;
	}
}
