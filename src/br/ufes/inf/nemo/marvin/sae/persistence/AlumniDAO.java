package br.ufes.inf.nemo.marvin.sae.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.core.domain.CourseAttendance;
import br.ufes.inf.nemo.marvin.sae.domain.Alumni;

/**
 * Interface for a DAO for objects of the Alumni domain class.
 * 
 * Using a mini CRUD framework for EJB3, basic DAO operation definitions are inherited from the superclass, whereas
 * operations that are specific to the managed domain class (if any) are specified in this class.
 * 
 * @author Gabriel Martins Miranda (garielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Local
public interface AlumniDAO extends BaseDAO<Alumni> {
	public Alumni retriveAlumni (CourseAttendance courseAttendance) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException;
}
