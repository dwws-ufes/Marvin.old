package br.ufes.inf.nemo.marvin.core.application;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.domain.Course;
import br.ufes.inf.nemo.marvin.core.domain.CourseCoordination;
import br.ufes.inf.nemo.marvin.core.persistence.AcademicDAO;
import br.ufes.inf.nemo.marvin.core.persistence.CourseCoordinationDAO;
import br.ufes.inf.nemo.marvin.core.persistence.CourseDAO;

/**
 * TODO: document this type.
 *
 * @author Gabriel Martins Miranda (gabrielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Stateless
@RolesAllowed("SysAdmin")
public class ManageCourseCoordinationsServiceBean extends CrudServiceBean<CourseCoordination> implements ManageCourseCoordinationsService {
	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageCourseCoordinationsServiceBean.class.getCanonicalName());

	/** TODO: document this field. */
	@EJB
	private AcademicDAO academicDAO;
	
	/** TODO: document this field. */
	@EJB
	private CourseDAO courseDAO;
	
	/** TODO: document this field. */
	@EJB
	private CourseCoordinationDAO courseCoordinationDAO;
	
	/** TODO: document this field. */
	@EJB
	private CoreInformation coreInformation;
	
	/** TODO: document this field. */
	@Resource
	private SessionContext sessionContext;

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.ListingService#getDAO() */
	@Override
	public BaseDAO<CourseCoordination> getDAO() {
		return courseCoordinationDAO;
	}

	
	/////////////////////////
	//TODO Fazer o desativar courseCoordination (colocar data final na coordenacao e tirar o role de coordenador do academic)
	//////////////////////
	
	
	/** @see br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean#create(br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObject) */
	@Override
	public void create(CourseCoordination entity) {
		// Performs the method as inherited (create the academic).
		entity.setStartDate(Calendar.getInstance().getTime());
		super.create(entity);
		// Retrieves the current user, i.e., the admin.
	}

	@Override
	public Academic retrieveCourseCordinator(Long idCourse) {
		return courseCoordinationDAO.retrieveCourseCordinator(idCourse);
	}
}
