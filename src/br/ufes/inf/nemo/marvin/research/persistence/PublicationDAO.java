package br.ufes.inf.nemo.marvin.research.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.research.domain.Publication;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Local
public interface PublicationDAO extends BaseDAO<Publication> {
	/**
	 * TODO: document this method.
	 * 
	 * @param academic
	 * @return
	 */
	long retrieveCountByAcademic(Academic academic);

	/**
	 * TODO: document this method.
	 * 
	 * @param academic
	 * @return
	 */
	List<Publication> retrieveByAcademic(Academic academic);
}
