package br.ufes.inf.nemo.marvin.core.application;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.controller.PersistentObjectConverterFromId;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.domain.Role;
import br.ufes.inf.nemo.marvin.core.persistence.AcademicDAO;
import br.ufes.inf.nemo.marvin.core.persistence.RoleDAO;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Stateless
@RolesAllowed("SysAdmin")
public class ManageAcademicsServiceBean extends CrudServiceBean<Academic> implements ManageAcademicsService {
	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageAcademicsServiceBean.class.getCanonicalName());

	/** TODO: document this field. */
	@EJB
	private AcademicDAO academicDAO;

	/** TODO: document this field. */
	@EJB
	private RoleDAO roleDAO;

	/** TODO: document this field. */
	private PersistentObjectConverterFromId<Role> roleConverter;

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.ListingService#getDAO() */
	@Override
	public BaseDAO<Academic> getDAO() {
		return academicDAO;
	}

	/**
	 * @see br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean#validate(br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObject,
	 *      br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObject)
	 */
	@Override
	protected Academic validate(Academic newEntity, Academic oldEntity) {
		// New academics must have their creation date set.
		Date now = new Date(System.currentTimeMillis());
		if (oldEntity == null) newEntity.setCreationDate(now);

		// All academics have their last update date set when persisted.
		newEntity.setLastUpdateDate(now);
		return newEntity;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean#validateDelete(br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObject) */
	@Override
	public void validateDelete(Academic entity) throws CrudException {
		// Possibly throwing a CRUD Exception to indicate validation error.
		CrudException crudException = null;
		String email = entity.getEmail();
		String crudExceptionMessage = "The academic \"" + entity.getName() + "(" + email + ")\" cannot be updated due to validation errors.";

		// Validates business rules.
		// Rule 1: cannot delete an admin.
		try {
			Role adminRole = roleDAO.retrieveByName(Role.SYSADMIN_ROLE_NAME);
			if (entity.getRoles().contains(adminRole)) {
				logger.log(Level.INFO, "Deletion of academic \"{0}\" violates validation rule 1: acadmic has SysAdmin role", new Object[] { email });
				crudException = addGlobalValidationError(crudException, crudExceptionMessage, "manageAcademics.error.deleteAdmin", email);				
			}
		}
		catch (MultiplePersistentObjectsFoundException | PersistentObjectNotFoundException e) {
			logger.log(Level.SEVERE, "Problem retrieving role " + Role.SYSADMIN_ROLE_NAME + " while validating an academic deletion!", e);
		}

		// If one of the rules was violated, throw the exception.
		if (crudException != null) throw crudException;		
	}

	/** @see br.ufes.inf.nemo.marvin.core.application.ManageAcademicsService#getRoleConverter() */
	@Override
	public PersistentObjectConverterFromId<Role> getRoleConverter() {
		if (roleConverter == null) roleConverter = new PersistentObjectConverterFromId<Role>(roleDAO);
		return roleConverter;
	}

	/** @see br.ufes.inf.nemo.marvin.core.application.ManageAcademicsService#findRoleByName(java.lang.String) */
	@Override
	public List<Role> findRoleByName(String name) {
		return roleDAO.findByName(name);
	}
}
