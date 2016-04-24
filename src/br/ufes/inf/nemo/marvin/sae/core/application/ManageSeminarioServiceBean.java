package br.ufes.inf.nemo.marvin.sae.core.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.marvin.sae.core.domain.Seminario;
import br.ufes.inf.nemo.marvin.sae.core.persistence.SeminarioDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Stateless
public class ManageSeminarioServiceBean  extends CrudServiceBean<Seminario> implements ManageSeminarioService {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private SeminarioDAO seminarioDAO;
	
	@Override
	public BaseDAO<Seminario> getDAO() {
		return seminarioDAO;
	}
	
	@Override
	public void authorize() {
		super.authorize();
	}

	@Override
	protected Seminario createNewEntity() {
		return new Seminario();
	}

}
