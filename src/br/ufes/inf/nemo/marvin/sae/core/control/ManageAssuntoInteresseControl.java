package br.ufes.inf.nemo.marvin.sae.core.control;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import br.ufes.inf.nemo.marvin.sae.core.application.ManageAssuntoInteresseSevice;
import br.ufes.inf.nemo.marvin.sae.core.domain.AssuntoInteresse;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;



/**
 * Controller class responsible for mediating the communication between user interface and application service for the
 * use case "Manage Assunto_Interesse".
 * 
 * This use case is a CRUD and, thus, the controller also uses the mini CRUD framework for EJB3.
 * 
 * @author Bruno Manzoli (manzoli2122@gmail.com)
 */
@Named
@SessionScoped
public class ManageAssuntoInteresseControl extends CrudController<AssuntoInteresse>{

	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	
	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageAssuntoInteresseControl.class.getCanonicalName());
	
	
	/** The "Manage Assunto_Interesse" service. */
	@EJB
	private ManageAssuntoInteresseSevice manageAssuntoInteresseService;
	
	
	
	
	
	
	
	/**   CONSTRUTOR DA CLASSE  */
	public ManageAssuntoInteresseControl(){
		 viewPath = "/sae/core/manageAssuntoInteresse/";
	     bundleName = "msgsSae";
	}
	
	
	
	/** @see br.ufes.inf.nemo.util.ejb3.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<AssuntoInteresse> getCrudService() {
		manageAssuntoInteresseService.authorize();
		return manageAssuntoInteresseService;
	}

	
	
	
	/** @see br.ufes.inf.nemo.util.ejb3.controller.CrudController#createNewEntity() */
	@Override
	protected AssuntoInteresse createNewEntity() {
		logger.log(Level.FINER, "INITIALIZING AN EMPTY Assunto_Interesse ......");
		return new AssuntoInteresse();
	}

	
	
	
	/** @see br.ufes.inf.nemo.util.ejb3.controller.CrudController#initFilters() */
	@Override
	protected void initFilters() {
	}
	
	
	
	
	
	/** @see br.ufes.inf.nemo.util.ejb3.controller.CrudController#save() */
	@Override
	public String save() {
		try{
			return super.save();
		}
		catch(Exception e){
			selectedEntity.setId(null);
			addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_ERROR, getBundlePrefix() + ".error.save" , summarizeSelectedEntity()  );
			return null;
		}
	}
	
	
	
	
	/** @see br.ufes.inf.nemo.util.ejb3.controller.CrudController#delete() */
	@Override
	public String delete() {
		try{
			return super.delete();
		}
		catch(Exception e){
			addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_ERROR, getBundlePrefix() + ".error.delete", summarizeSelectedEntity());
			cancelDeletion();
	        return null;
		}
	}
	
	
	
}
