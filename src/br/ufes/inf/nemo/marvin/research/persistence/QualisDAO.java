package br.ufes.inf.nemo.marvin.research.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.research.domain.Qualis;

@Local
public interface QualisDAO extends BaseDAO<Qualis> {
	/**
	 * TODO: document this method.
	 * 
	 * @param level
	 * @return
	 * @throws PersistentObjectNotFoundException
	 * @throws MultiplePersistentObjectsFoundException
	 */
	Qualis retrieveByLevel(String level) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException;
}
