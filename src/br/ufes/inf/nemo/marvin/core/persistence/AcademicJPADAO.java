package br.ufes.inf.nemo.marvin.core.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.domain.Academic_;
import br.ufes.inf.nemo.marvin.core.domain.Role;
import br.ufes.inf.nemo.marvin.people.domain.Person_;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Stateless
public class AcademicJPADAO extends BaseJPADAO<Academic> implements AcademicDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(AcademicJPADAO.class.getCanonicalName());

	/** The application's persistent context provided by the application server. */
	@PersistenceContext
	private EntityManager entityManager;

	/** @see br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO#getEntityManager() */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @see br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO#getOrderList(javax.persistence.criteria.CriteriaBuilder,
	 *      javax.persistence.criteria.Root)
	 */
	@Override
	protected List<Order> getOrderList(CriteriaBuilder cb, Root<Academic> root) {
		// Orders by name.
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(cb.asc(root.get(Person_.name)));
		return orderList;
	}

	/** @see br.ufes.inf.nemo.marvin.core.persistence.AcademicDAO#retrieveByEmail(java.lang.String) */
	@Override
	public Academic retrieveByEmail(String email) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {
		logger.log(Level.FINE, "Retrieving the academic whose e-mail is \"{0}\"...", email);

		// Constructs the query over the Academic class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Academic> cq = cb.createQuery(Academic.class);
		Root<Academic> root = cq.from(Academic.class);

		// Filters the query with the email.
		cq.where(cb.equal(root.get(Academic_.email), email));
		Academic result = executeSingleResultQuery(cq, email);
		logger.log(Level.INFO, "Retrieve academic by the email \"{0}\" returned \"{1}\"", new Object[] { email, result });
		return result;
	}

	/** @see br.ufes.inf.nemo.marvin.core.persistence.AcademicDAO#retrieveByLattesId(java.lang.Long) */
	@Override
	public Academic retrieveByLattesId(Long lattesId) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {
		logger.log(Level.FINE, "Retrieving the academic whose Lattes ID is \"{0}\"...", lattesId);

		// Constructs the query over the Academic class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Academic> cq = cb.createQuery(Academic.class);
		Root<Academic> root = cq.from(Academic.class);

		// Filters the query with the Lattes ID.
		cq.where(cb.equal(root.get(Academic_.lattesId), lattesId));
		Academic result = executeSingleResultQuery(cq, lattesId);
		logger.log(Level.INFO, "Retrieve academic by the Lattes ID \"{0}\" returned \"{1}\"", new Object[] { lattesId, result });
		return result;
	}

	/** @see br.ufes.inf.nemo.marvin.core.persistence.AcademicDAO#retrieveResearchers() */
	@Override
	public List<Academic> retrieveResearchers() {
		logger.log(Level.FINE, "Retrieving all researchers in the system");

		// Constructs the query over the Academic class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Academic> cq = cb.createQuery(Academic.class);
		Root<Academic> root = cq.from(Academic.class);

		// Filters academics that have Lattes ID set.
		// FIXME: also check if the academic has either the professor or the student role.
		cq.where(cb.isNotNull(root.get(Academic_.lattesId)));
		List<Academic> result = entityManager.createQuery(cq).getResultList();
		logger.log(Level.INFO, "Retrieving researchers returned {0} results", result.size());
		return result;
	}

	/** @see br.ufes.inf.nemo.marvin.core.persistence.AcademicDAO#retrieveByEmail(java.lang.String) */
	@Override
	public Academic retrieveByPasswordCode(String passwordCode) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {
		logger.log(Level.FINE, "Retrieving the academic whose password code is \"{0}\"...", passwordCode);

		// Constructs the query over the Academic class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Academic> cq = cb.createQuery(Academic.class);
		Root<Academic> root = cq.from(Academic.class);

		// Filters the query with the email.
		cq.where(cb.equal(root.get(Academic_.passwordCode), passwordCode));
		Academic result = executeSingleResultQuery(cq, passwordCode);
		logger.log(Level.INFO, "Retrieve academic by the password code \"{0}\" returned \"{1}\"", new Object[] { passwordCode, result });
		return result;
	}

	/** Retrieve all Academics with a specific role*/
	@Override
	public List<Academic> retrieveByRole(Role role) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {
		logger.log(Level.FINE, "Retrieving the academic whose role is \"{0}\"...", role);
		Query query = entityManager.createQuery("SELECT a FROM Academic a WHERE :role MEMBER OF a.roles ORDER BY a.name");
		query.setParameter("role", role);
		return (List<Academic>) query.getResultList();
	}

}
