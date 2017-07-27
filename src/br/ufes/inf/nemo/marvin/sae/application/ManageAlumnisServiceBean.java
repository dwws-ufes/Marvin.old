package br.ufes.inf.nemo.marvin.sae.application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.domain.CourseAttendance;
import br.ufes.inf.nemo.marvin.core.persistence.CourseAttendanceDAO;
import br.ufes.inf.nemo.marvin.sae.domain.Alumni;
import br.ufes.inf.nemo.marvin.sae.persistence.AlumniDAO;

/**
 * TODO: document this type.
 *
 * @author Gabriel Martins Miranda (gabrielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Stateless
@RolesAllowed("SysAdmin")
public class ManageAlumnisServiceBean extends CrudServiceBean<Alumni> implements ManageAlumnisService {
	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageAlumnisServiceBean.class.getCanonicalName());

	/** TODO: document this field. */
	@EJB
	private AlumniDAO alumniDAO;
	
	private CourseAttendanceDAO courseAttendanceDAO;

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.ListingService#getDAO() */
	@Override
	public BaseDAO<Alumni> getDAO() {
		return alumniDAO;
	}

	/**
	 * @see br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean#validate(br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObject,
	 *      br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObject)
	 */
	@Override
	protected Alumni validate(Alumni newEntity, Alumni oldEntity) {
		// New academics must have their creation date and password code set.
		Date now = new Date(System.currentTimeMillis());
		if (oldEntity == null) {
			newEntity.setCreationDate(now);
		}
		return newEntity;
	}
	public List<Alumni> list(Academic currentUser) {
		List<CourseAttendance> courseAttendances;
		Alumni alumni;
		List<Alumni> alumnis = new ArrayList<Alumni>();
		courseAttendances = courseAttendanceDAO.retriveCourseAttendances(currentUser);
		for (CourseAttendance courseAttendance : courseAttendances) {
			try {
				alumni = alumniDAO.retriveAlumni(courseAttendance);
				alumnis.add(alumni);
			} catch (PersistentObjectNotFoundException | MultiplePersistentObjectsFoundException e) {
				e.printStackTrace();
			}
		}
		alumnis.sort(new Comparator<Alumni>() {
			@Override
			public int compare(Alumni o1, Alumni o2) {
				return o1.getCourseAttendance().getAcademic().getName().compareTo(o2.getCourseAttendance().getAcademic().getName());
			}
		});
		return alumnis;
	}
}
