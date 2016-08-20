package br.ufes.inf.nemo.marvin.research.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.research.domain.Author;

/**
 * TODO: document this type.
 *
 * @author Thiago Rocha Salvatore
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Local
public interface AuthorDAO extends BaseDAO<Author> {
	/**
	 * TODO: document this method.
	 * 
	 * @param name
	 * @return
	 */
	Author retrieveByName(String name);
}
