package br.ufes.inf.nemo.marvin.core.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.marvin.core.application.ManageAcademicsService;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.application.filters.LikeFilter;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;

@Named
@SessionScoped
public class ManageAcademicsController extends CrudController<Academic>{

	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	
	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageAcademicsController.class.getCanonicalName());
	
	/** The "Manage Academic" service. */
	@EJB
	private ManageAcademicsService manageAcademicsService;
	
	
	
	
	/**   CONSTRUTOR DA CLASSE */
	public ManageAcademicsController(){
		 viewPath = "/core/manageAcademics/";
	     bundleName = "msgsCore";
	}
	
	
	
	@Override
	protected Academic createNewEntity() {
		logger.log(Level.INFO, "INITIALIZING AN EMPTY Academic.");
		return new Academic();
	}

	@Override
	protected CrudService<Academic> getCrudService() {
		return manageAcademicsService;
	}

	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageAcademics.filter.byName", "name", getI18nMessage(bundleName, "manageAcademics.text.filter.byName")));
	}

}
