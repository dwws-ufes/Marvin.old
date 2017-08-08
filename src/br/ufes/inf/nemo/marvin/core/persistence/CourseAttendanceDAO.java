package br.ufes.inf.nemo.marvin.core.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.domain.Course;
import br.ufes.inf.nemo.marvin.core.domain.CourseAttendance;

/**
 * Interface for a DAO for objects of the Course Attendance domain class.
 * 
 * Using a mini CRUD framework for EJB3, basic DAO operation definitions are inherited from the superclass, whereas
 * operations that are specific to the managed domain class (if any) are specified in this class.
 * 
 * @author Gabriel Martins Miranda (garielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Local
public interface CourseAttendanceDAO extends BaseDAO<CourseAttendance> {
	public List<CourseAttendance> retriveCourseAttendances(Academic academic);

	public List<Course> retriveCoursesByAcademic(Academic academic);
	
	public boolean courseInCourseAttendance (Course course);
}
