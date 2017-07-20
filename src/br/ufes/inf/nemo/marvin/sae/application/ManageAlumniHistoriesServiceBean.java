package br.ufes.inf.nemo.marvin.sae.application;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.Course;
import br.ufes.inf.nemo.marvin.core.domain.CourseAttendance.Situation;
import br.ufes.inf.nemo.marvin.sae.domain.Alumni;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory.DegreeArea;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory.PracticeArea;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory.SalaryRange;
import br.ufes.inf.nemo.marvin.sae.domain.Education.EducationType;
import br.ufes.inf.nemo.marvin.sae.persistence.AlumniDAO;
import br.ufes.inf.nemo.marvin.sae.persistence.AlumniHistoryDAO;

/**
 * TODO: document this type.
 *
 * @author Gabriel Martins Miranda (gabrielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Stateless
@RolesAllowed("SysAdmin")
public class ManageAlumniHistoriesServiceBean extends CrudServiceBean<AlumniHistory> implements ManageAlumniHistoriesService {
	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageAlumniHistoriesServiceBean.class.getCanonicalName());

	/** TODO: document this field. */
	@EJB
	private AlumniHistoryDAO alumniHistoryDAO;
	
	/** TODO: document this field. */
	@EJB
	private AlumniDAO alumniDAO;

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.ListingService#getDAO() */
	@Override
	public BaseDAO<AlumniHistory> getDAO() {
		return alumniHistoryDAO;
	}

	/**
	 * @see br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean#validate(br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObject,
	 *      br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObject)
	 */
	@Override
	protected AlumniHistory validate(AlumniHistory newEntity, AlumniHistory oldEntity) {
		// New academics must have their creation date and password code set.
		Date now = new Date(System.currentTimeMillis());
		if (oldEntity == null) {
			newEntity.setSendDate(now);
		}

		return newEntity;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean#validateDelete(br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObject) */
	@Override
	public void validateDelete(AlumniHistory entity) throws CrudException {
		// Possibly throwing a CRUD Exception to indicate validation error.
//		CrudException crudException = null;
//		String email = entity.getEmail();
//		String crudExceptionMessage = "The academic \"" + entity.getName() + "(" + email + ")\" cannot be updated due to validation errors.";
//
//		// Validates business rules.
//		// Rule 1: cannot delete an admin.
//		try {
//			Role adminRole = roleDAO.retrieveByName(Role.SYSADMIN_ROLE_NAME);
//			if (entity.getRoles().contains(adminRole)) {
//				logger.log(Level.INFO, "Deletion of academic \"{0}\" violates validation rule 1: acadmic has SysAdmin role", new Object[] { email });
//				crudException = addGlobalValidationError(crudException, crudExceptionMessage, "manageAcademics.error.deleteAdmin", email);
//			}
//		}
//		catch (MultiplePersistentObjectsFoundException | PersistentObjectNotFoundException e) {
//			logger.log(Level.SEVERE, "Problem retrieving role " + Role.SYSADMIN_ROLE_NAME + " while validating an academic deletion!", e);
//		}
//		
//		// If one of the rules was violated, throw the exception.
//		if (crudException != null) throw crudException;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean#create(br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObject) */
	@Override
	public void create(AlumniHistory entity) {
		// Performs the method as inherited (create the academic).
		super.create(entity);
		
//		try {
//			// Retrieves the current user, i.e., the admin.
//			Academic admin = academicDAO.retrieveByEmail(sessionContext.getCallerPrincipal().getName());
//			
//			// Creates the data model with the information needed to send an e-mail to the new academic.
//			Map<String, Object> dataModel = new HashMap<>();
//			dataModel.put("config", coreInformation.getCurrentConfig());
//			dataModel.put("admin", admin);
//			dataModel.put("academic", entity);
//		
//			// Then, fire an e-mail event so the e-mail gets sent.
//			mailEvent.fire(new MailEvent(entity.getEmail(), MailerTemplate.NEW_ACADEMIC_REGISTERED, dataModel));
//		}
//		catch (Exception e) {
//			logger.log(Level.SEVERE, "Could NOT send e-mail using template: " + MailerTemplate.NEW_ACADEMIC_REGISTERED, e);
//		}
	}

	@Override
	public Map<String, Alumni> retrieveAlumnis() {
		Map<String, Alumni> alumnisMap = new HashMap<String, Alumni>();
		List<Alumni> alumnis = alumniDAO.retrieveAll();
		for (Alumni alumni : alumnis) alumnisMap.put(alumni.toString(), alumni);			
		return alumnisMap;
	}
}
