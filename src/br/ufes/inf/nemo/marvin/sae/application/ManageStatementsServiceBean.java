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
import br.ufes.inf.nemo.marvin.sae.domain.Statement;
import br.ufes.inf.nemo.marvin.sae.domain.Statement.StatementStatus;
import br.ufes.inf.nemo.marvin.sae.persistence.StatementDAO;

/**
 * TODO: document this type.
 *
 * @author Gabriel Martins Miranda (gabrielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Stateless
@RolesAllowed("SysAdmin")
public class ManageStatementsServiceBean extends CrudServiceBean<Statement> implements ManageStatementsService {
	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageStatementsServiceBean.class.getCanonicalName());

	/** TODO: document this field. */
	@EJB
	private StatementDAO statementDAO;

	/** TODO: document this field. */
	@EJB
	private CourseAttendanceDAO courseAttendanceDAO;

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.ListingService#getDAO() */
	@Override
	public BaseDAO<Statement> getDAO() {
		return statementDAO;
	}

	/**
	 * @see br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean#validate(br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObject,
	 *      br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObject)
	 */
	@Override
	protected Statement validate(Statement newEntity, Statement oldEntity) {
		// New academics must have their creation date and password code set.
		Date now = new Date(System.currentTimeMillis());
		// If a statement is modified, it needs be approve again
		newEntity.setSendDate(now);
		newEntity.setStatementStatus(StatementStatus.PENDING);
		return newEntity;
	}

	/** Returns a map containing the course of a academic. Load the combobox in the front-end. */
	@Override
	public Map<String, Course> retrieveCourses(Academic academic) {
		Map<String, Course> coursesMap = new HashMap<String, Course>();
		List<Course> courses = courseAttendanceDAO.retriveCoursesByAcademic(academic);
		for (Course course : courses)
			coursesMap.put(course.getName(), course);
		return coursesMap;
	}

	/** Change the status of a statement to approved */
	@Override
	public void approve(Statement statement) {
		statement.setStatementStatus(StatementStatus.APPROVED);
		statementDAO.save(statement);
	}

	/** Change the status of a statement to disapproved */
	@Override
	public void reject(Statement statement) {
		statement.setStatementStatus(StatementStatus.DISAPPROVED);
		statementDAO.save(statement);
	}
}
