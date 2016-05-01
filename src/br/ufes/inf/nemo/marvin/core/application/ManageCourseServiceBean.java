package br.ufes.inf.nemo.marvin.core.application;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.marvin.core.domain.Course;
import br.ufes.inf.nemo.marvin.core.persistence.CourseDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;


/**
 * Stateless session bean implementing the "Manage Course" use case component. See the implemented interface
 * documentation for details.
 * 
 * @author Bruno Manzoli (manzoli2122@gmail.com)
 * @see sae.core.application.ManageAdministradorService
 */
@Stateless
//@DeclareRoles({"Admin", "egresso" , "guest"})
//@RolesAllowed({ "Admin" })
public class ManageCourseServiceBean extends CrudServiceBean<Course> implements ManageCourseService{

	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	
	/** The DAO for Course objects. */
	@EJB
	private CourseDAO courseDAO;

	
	
	
	@Override
	public void authorize() {
		super.authorize();
	}
	
	
	
	/** @see br.ufes.inf.nemo.util.ejb3.application.CrudService#getDAO() */
	@Override
	public BaseDAO<Course> getDAO() {
		return courseDAO;
	}

	
	
	/** @see sae.core.application.CrudServiceBean#createNewEntity() */
	@Override
	protected Course createNewEntity() {
		return new Course();
	}
	
	
	
	/** @see sae.core.application.CrudServiceBean#update(Course entity) */
	@Override
	public void update(Course entity) {
		Course oldEntity = getDAO().retrieveById(entity.getId());
		if ( oldEntity.getName().equals(entity.getName()) &&  oldEntity.getCode().equals(entity.getCode())){
			super.update(entity);
		}
	}

}
