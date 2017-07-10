package br.ufes.inf.nemo.marvin.sae.application;

import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.sae.domain.Suggestion;
import br.ufes.inf.nemo.marvin.sae.persistence.SuggestionDAO;

/**
 * TODO: document this type.
 *
 * @author Gabriel Martins Miranda (gabrielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Stateless
@RolesAllowed("SysAdmin")
public class ManageSuggestionsServiceBean extends CrudServiceBean<Suggestion> implements ManageSuggestionsService {
	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageSuggestionsServiceBean.class.getCanonicalName());

	/** TODO: document this field. */
	@EJB
	private SuggestionDAO suggestionDAO;

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.ListingService#getDAO() */
	@Override
	public BaseDAO<Suggestion> getDAO() {
		return suggestionDAO;
	}

	/**
	 * @see br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean#validate(br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObject,
	 *      br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObject)
	 */
	@Override
	protected Suggestion validate(Suggestion newEntity, Suggestion oldEntity) {
		// New academics must have their creation date and password code set.
		Date now = new Date(System.currentTimeMillis());
		if (oldEntity == null) {
			//newEntity.setCreationDate(now);
		}

		// All academics have their last update date set when persisted.
		//newEntity.setLastUpdateDate(now);
		return newEntity;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean#validateDelete(br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObject) */
	@Override
	public void validateDelete(Suggestion entity) throws CrudException {
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
	public void create(Suggestion entity) {
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
}
