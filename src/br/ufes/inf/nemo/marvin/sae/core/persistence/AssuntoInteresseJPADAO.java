package br.ufes.inf.nemo.marvin.sae.core.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.marvin.sae.core.domain.AssuntoInteresse;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;



@Stateless
public class AssuntoInteresseJPADAO extends BaseJPADAO<AssuntoInteresse> implements AssuntoInteresseDAO{

	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Class<AssuntoInteresse> getDomainClass() {
		return AssuntoInteresse.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
