package br.ufes.inf.nemo.marvin.core.application;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.marvin.core.domain.Course;
import br.ufes.inf.nemo.marvin.core.persistence.CourseDAO;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;


/**
 * Stateless session bean implementing the "Manage Course" use case component. See the implemented interface
 * documentation for details.
 * 
 * @author Bruno Manzoli (manzoli2122@gmail.com)
 * @see sae.core.application.ManageAdministradorService
 */
@Stateless
@DeclareRoles({ "Admin" , "Alumni" , "Researcher" , "Student" , "Teacher" })
@RolesAllowed({ "Admin" })
public class ManageCourseServiceBean extends CrudServiceBean<Course> implements ManageCourseService{

	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	
	/** The DAO for Course objects. */
	@EJB
	private CourseDAO courseDAO;

	
	
	/** @see br.ufes.inf.nemo.util.ejb3.application.CrudService#getDAO() */
	@Override
	public BaseDAO<Course> getDAO() {
		return courseDAO;
	}
	
	
	/** @see sae.core.application.CrudServiceBean#validateUpdate(Course entity) */
	@Override
	public void validateUpdate(Course entity) throws CrudException {
		Course oldEntity = getDAO().retrieveById(entity.getId());
		if (!oldEntity.getName().equals(entity.getName())){
			throw new CrudException(null, null, null);
		}
	}


}
