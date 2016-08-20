package br.ufes.inf.nemo.marvin.research.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.research.domain.Article;

@Local
public interface ArticleDAO extends BaseDAO<Article> {

	public Article getArticleByTitle(String title);

	public Article getArticleByDOI(String doi);

}
