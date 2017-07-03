package br.ufes.inf.nemo.marvin.core.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.domain.Course;
import br.ufes.inf.nemo.marvin.core.domain.CourseCoordination;

/**
 * Interface for a DAO for objects of the Role domain class.
 * 
 * Using a mini CRUD framework for EJB3, basic DAO operation definitions are inherited from the superclass, whereas
 * operations that are specific to the managed domain class (if any) are specified in this class.
 * 
 * @author Vitor E. Silva Souza (vitorsouza@gmail.com)
 * @see br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO
 */
@Local
public interface CourseCoordinationDAO extends BaseDAO<CourseCoordination> {
	public Academic retrieveCourseCordinator(Long idCourse);
	public boolean academicWasCoordinator(Academic academic);
	public boolean courseHasCoordinations(Course course);
	public boolean courseHasActiveCoordinations(Course course);
}
