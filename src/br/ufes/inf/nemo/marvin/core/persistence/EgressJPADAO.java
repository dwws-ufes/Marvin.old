package br.ufes.inf.nemo.marvin.core.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.marvin.core.domain.Egress;
import br.ufes.inf.nemo.marvin.people.domain.Person_;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Stateless
public class EgressJPADAO extends BaseJPADAO<Egress> implements EgressDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(EgressJPADAO.class.getCanonicalName());

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
	protected List<Order> getOrderList(CriteriaBuilder cb, Root<Egress> root) {
		// Orders by name.
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(cb.asc(root.get(Person_.name)));
		return orderList;
	}

	
}
