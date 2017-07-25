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
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.domain.Course;
import br.ufes.inf.nemo.marvin.core.persistence.CourseAttendanceDAO;
import br.ufes.inf.nemo.marvin.core.persistence.CourseDAO;
import br.ufes.inf.nemo.marvin.sae.domain.Suggestion;
import br.ufes.inf.nemo.marvin.sae.persistence.AlumniDAO;
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
	
	/** TODO: document this field. */
	@EJB
	private CourseAttendanceDAO courseAttendanceDAO;

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
			newEntity.setSendDate(now);
		}

		return newEntity;
	}
	
	@Override
	public Map<String, Course> retrieveCourses(Academic academic) {
		Map<String, Course> coursesMap = new HashMap<String, Course>();
		List<Course> courses = courseAttendanceDAO.retriveCoursesInCourseAttendance(academic);
		for (Course course : courses) coursesMap.put(course.getName(), course);			
		return coursesMap;
	}
}
