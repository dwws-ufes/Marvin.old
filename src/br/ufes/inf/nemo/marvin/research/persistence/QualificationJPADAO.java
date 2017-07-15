package br.ufes.inf.nemo.marvin.research.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.research.domain.Qualification;
import br.ufes.inf.nemo.marvin.research.domain.Qualification_;
import br.ufes.inf.nemo.marvin.research.domain.Venue;

@Stateless
public class QualificationJPADAO extends BaseJPADAO<Qualification> implements QualificationDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(QualificationJPADAO.class.getCanonicalName());

	/** The application's persistent context provided by the application server. */
	@PersistenceContext
	private EntityManager entityManager;

	/** @see br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO#getEntityManager() */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<Qualification> retrieveByVenue(Venue venue) {
		logger.log(Level.FINE, "Retrieving the qualifications of venue \"{0}\".", new Object[] { venue.getName() });

		// Constructs the query over the Publication class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Qualification> cq = cb.createQuery(Qualification.class);
		Root<Qualification> root = cq.from(Qualification.class);

		// Filters the query with the academic.
		cq.where(cb.equal(root.get(Qualification_.venue), venue));
		List<Qualification> result = entityManager.createQuery(cq).getResultList();
		logger.log(Level.INFO, "Retrieve qualifications of venue \"{0}\" returned {1} results.", new Object[] { venue.getName(), result.size() });
		return result;
	}

	@Override
	public List<Qualification> retrieveByYear(int year) {
		logger.log(Level.FINE, "Retrieving the qualifications of year \"{0}\".", new Object[] { year });

		// Constructs the query over the Publication class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Qualification> cq = cb.createQuery(Qualification.class);
		Root<Qualification> root = cq.from(Qualification.class);

		// Filters the query with the academic.
		cq.where(cb.equal(root.get(Qualification_.year), year));
		List<Qualification> result = entityManager.createQuery(cq).getResultList();
		logger.log(Level.INFO, "Retrieve qualifications of year \"{0}\" returned {1} results.", new Object[] { year, result.size() });
		return result;
	}

	@Override
	public Qualification retrieveByVenueAndYear(Venue venue, Integer year) {
		logger.log(Level.FINE, "Retrieving the qualification of venue \"{0}\" of year {1}...", new Object[] { venue.getName(), year });

		// Constructs the query over the Publication class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Qualification> cq = cb.createQuery(Qualification.class);
		Root<Qualification> root = cq.from(Qualification.class);

		// Filters the query with the academic and the year range.
		List<Predicate> constraints = new ArrayList<>();
		constraints.add(cb.equal(root.get(Qualification_.venue), venue));
		if (year != null) constraints.add(cb.equal(root.get(Qualification_.year), year));
		cq.where(constraints.toArray(new Predicate[] {}));
		Qualification result = entityManager.createQuery(cq).getSingleResult();
		logger.log(Level.INFO, "Retrieve qualification of venue \"{0}\" of year {1}.", new Object[] { venue.getName(), year });
		return result;
	}

	@Override
	public Qualification retrieveClosestByVenueAndYear(Venue venue, Integer refYear) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {
		logger.log(Level.FINE, "Retrieving the qualification of venue \"{0}\" closest to year {1}...", new Object[] { venue.getName(), refYear });

		// Constructs the query over the Publication class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Qualification> cq = cb.createQuery(Qualification.class);
		Root<Qualification> root = cq.from(Qualification.class);

		// Constructs the subquery that will return the year closest to the refYear
		Subquery<Integer> sq = cq.subquery(Integer.class);
		Root<Qualification> sqRoot = sq.from(Qualification.class);
		sq.select(cb.greatest(sqRoot.get(Qualification_.year)));
		sq.where(cb.le(sqRoot.get(Qualification_.year), refYear));

		// Filters the query with the academic and the result of the subquery.
		List<Predicate> constraints = new ArrayList<>();
		constraints.add(cb.equal(root.get(Qualification_.venue), venue));
		constraints.add(cb.equal(root.get(Qualification_.year), sq));
		cq.where(cb.and(constraints.toArray(new Predicate[] {})));

		List<Qualification> partialResult = entityManager.createQuery(cq).getResultList();
		if (partialResult.size() > 1) {
			// This is a workaround for a bug related to the registration of venues. It will be fixed in a future version.
			Qualification result = partialResult.get(0);
			logger.log(Level.INFO, "Retrieve qualification of venue \"{0}\" closest to year {1}.", new Object[] { venue.getName(), refYear });
			return result;
		}
		Qualification result = executeSingleResultQuery(cq);
		logger.log(Level.INFO, "Retrieve qualification of venue \"{0}\" closest to year {1}.", new Object[] { venue.getName(), refYear });
		return result;
	}
}
