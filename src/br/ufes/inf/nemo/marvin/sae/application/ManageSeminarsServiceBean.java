package br.ufes.inf.nemo.marvin.sae.application;

import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.sae.domain.Seminar;
import br.ufes.inf.nemo.marvin.sae.persistence.SeminarDAO;

/**
 * TODO: document this type.
 *
 * @author Gabriel Martins Miranda (gabrielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Stateless
@RolesAllowed("SysAdmin")
public class ManageSeminarsServiceBean extends CrudServiceBean<Seminar> implements ManageSeminarsService {
	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageSeminarsServiceBean.class.getCanonicalName());

	/** TODO: document this field. */
	@EJB
	private SeminarDAO seminarDAO;

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.ListingService#getDAO() */
	@Override
	public BaseDAO<Seminar> getDAO() {
		return seminarDAO;
	}

	/**
	 * @see br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean#validate(br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObject,
	 *      br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObject)
	 */
	@Override
	protected Seminar validate(Seminar newEntity, Seminar oldEntity) {
		// New academics must have their creation date and password code set.
		Date now = new Date(System.currentTimeMillis());
		if (oldEntity == null) {
			newEntity.setCreationDate(now);
		}

		// All academics have their last update date set when persisted.
		newEntity.setLastUpdateDate(now);
		return newEntity;
	}
}
