package br.ufes.inf.nemo.marvin.core.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.marvin.core.application.ManageAcademicService;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;

@Named
@SessionScoped
public class ManageAcademicController extends CrudController<Academic>{

	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	
	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageAcademicController.class.getCanonicalName());
	
	/** The "Manage Academic" service. */
	@EJB
	private ManageAcademicService manageAcademicService;
	
	
	
	
	/**   CONSTRUTOR DA CLASSE */
	public ManageAcademicController(){
		 viewPath = "/core/manageAcademic/";
	     bundleName = "msgsCore";
	}
	
	
	
	@Override
	protected Academic createNewEntity() {
		logger.log(Level.INFO, "INITIALIZING AN EMPTY Academic.");
		return new Academic();
	}

	@Override
	protected CrudService<Academic> getCrudService() {
		return manageAcademicService;
	}

	@Override
	protected void initFilters() {
		
	}

}
