package br.ufes.inf.nemo.marvin.core.application;

import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.controller.PersistentObjectConverterFromId;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
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
