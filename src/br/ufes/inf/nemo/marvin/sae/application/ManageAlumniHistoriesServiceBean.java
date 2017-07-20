package br.ufes.inf.nemo.marvin.sae.application;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.sae.domain.Alumni;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory;
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

	@Override
	public Map<String, Alumni> retrieveAlumnis() {
		Map<String, Alumni> alumnisMap = new HashMap<String, Alumni>();
		List<Alumni> alumnis = alumniDAO.retrieveAll();
		for (Alumni alumni : alumnis) alumnisMap.put(alumni.toString(), alumni);			
		return alumnisMap;
	}
}
