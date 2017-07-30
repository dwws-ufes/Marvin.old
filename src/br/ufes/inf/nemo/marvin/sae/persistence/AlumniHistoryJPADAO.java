package br.ufes.inf.nemo.marvin.sae.persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.marvin.core.persistence.AcademicJPADAO;
import br.ufes.inf.nemo.marvin.sae.domain.Alumni;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory_;

/**
 * Stateless session bean implementing a DAO for objects of the Alumni History domain class using JPA2.
 * 
 * Using a mini CRUD framework for EJB3, basic DAO operation implementations are inherited from the superclass, whereas
 * operations that are specific to the managed domain class (if any is defined in the implementing DAO interface) have
 * to be implemented in this class.
 * 
 * @author Gabriel Martins Miranda (garielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Stateless
public class AlumniHistoryJPADAO extends BaseJPADAO<AlumniHistory> implements AlumniHistoryDAO {
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

	public boolean alumniWithHistory(Alumni alumni) {
		logger.log(Level.FINE, "Retrieving the alumnis with history");
		// Constructs the query over the Course Attendance class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AlumniHistory> cq = cb.createQuery(AlumniHistory.class);
		Root<AlumniHistory> root = cq.from(AlumniHistory.class);

		// Filters the query with the academic.
		cq.where(cb.equal(root.get(AlumniHistory_.alumni), alumni));
		List<AlumniHistory> result = entityManager.createQuery(cq).getResultList();
		if (result.size() > 0) return true;
		return false;
	}
}
