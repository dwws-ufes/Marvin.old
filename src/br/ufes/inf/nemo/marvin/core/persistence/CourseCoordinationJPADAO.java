package br.ufes.inf.nemo.marvin.core.persistence;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.domain.AcademicRole;
import br.ufes.inf.nemo.marvin.core.domain.AcademicRole_;
import br.ufes.inf.nemo.marvin.core.domain.Academic_;
import br.ufes.inf.nemo.marvin.core.domain.CourseCoordination;
import br.ufes.inf.nemo.marvin.core.domain.CourseCoordination_;

/**
 * Stateless session bean implementing a DAO for objects of the Role domain class using JPA2.
 * 
 * Using a mini CRUD framework for EJB3, basic DAO operation implementations are inherited from the superclass, whereas
 * operations that are specific to the managed domain class (if any is defined in the implementing DAO interface) have
 * to be implemented in this class.
 * 
 * @author Vitor E. Silva Souza (vitorsouza@gmail.com)
 */
@Stateless
public class CourseCoordinationJPADAO extends BaseJPADAO<CourseCoordination> implements CourseCoordinationDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(CourseCoordinationJPADAO.class.getCanonicalName());

	/** The application's persistent context provided by the application server. */
	@PersistenceContext
	private EntityManager entityManager;

	/** @see br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO#getEntityManager() */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Academic retrieveCourseCordinator(Long idCourse) {
		logger.log(Level.FINE, "Retrieving the courseCoordinator of the course, whose id is \"{0}\"...", idCourse);

		// Constructs the query over the Academic class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseCoordination> cq = cb.createQuery(CourseCoordination.class);
		Root<CourseCoordination> root = cq.from(CourseCoordination.class);

		// Filters the query with the email.
		//cq.where(cb.equal(root.get(CourseCoordination_.course.get), email));
		//Academic result = executeSingleResultQuery(cq, email);
		//logger.log(Level.INFO, "Retrieve academic by the email \"{0}\" returned \"{1}\"", new Object[] { email, result });
		//return result;
		return null;
	}
}
