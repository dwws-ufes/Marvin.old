package br.ufes.inf.nemo.marvin.sae.core.control;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.marvin.sae.core.application.ManageSeminarioService;
import br.ufes.inf.nemo.marvin.sae.core.domain.Seminario;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;



@Named
@SessionScoped
public class ManageSeminarioControl extends CrudController<Seminario>{

private static final long serialVersionUID = 1L;
	
	
	@EJB
	private ManageSeminarioService manageSeminarioService;

	/*   CONSTRUTOR DA CLASSE */
	public ManageSeminarioControl(){
		 viewPath = "/sae/core/manageSeminario/";
	     bundleName = "msgsSae";
	}

	
	@Override
	protected CrudService<Seminario> getCrudService() {
		manageSeminarioService.authorize();
		return manageSeminarioService;
	}


	@Override
	protected Seminario createNewEntity() {
		return new Seminario();
	}


	@Override
	protected void initFilters() {
		
	}
}
