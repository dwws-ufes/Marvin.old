package br.ufes.inf.nemo.marvin.sae.core.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.marvin.sae.core.domain.Seminario;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

@Stateless
public class SeminarioJPADAO extends BaseJPADAO<Seminario> implements SeminarioDAO{

	private static final long serialVersionUID = 1L;
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Class<Seminario> getDomainClass() {
		return Seminario.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
