package br.ufes.inf.nemo.marvin.core.persistence;


import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.marvin.core.domain.Course;
import br.ufes.inf.nemo.marvin.core.domain.Course_;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;




@Stateless
public class CourseJPADAO extends BaseJPADAO<Course> implements CourseDAO {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName="Marvin")
	private EntityManager entityManager;
	
	
	@Override
	public Class<Course> getDomainClass() {
		return Course.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	
	@Override
	protected List<Order> getOrderList(CriteriaBuilder cb, Root<Course> root) {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(cb.asc(root.get(Course_.name)));
		return orderList;
	}
	
	
	
}
