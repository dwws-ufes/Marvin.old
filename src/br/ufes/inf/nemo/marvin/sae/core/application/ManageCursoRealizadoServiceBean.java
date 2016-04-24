package br.ufes.inf.nemo.marvin.sae.core.application;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.marvin.sae.core.domain.CursoRealizado;
import br.ufes.inf.nemo.marvin.sae.core.persistence.CursoRealizadoDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;


/**
 * Stateless session bean implementing the "Manage CursoRealizado" use case component. See the implemented interface
 * documentation for details.
 * 
 * @author Bruno Manzoli (manzoli2122@gmail.com)
 * @see sae.core.application.ManageAdministradorService
 */
@Stateless
//@DeclareRoles({"Admin", "egresso", "guest"})
//@RolesAllowed({ "Admin" })
public class ManageCursoRealizadoServiceBean extends CrudServiceBean<CursoRealizado> implements ManageCursoRealizadoService{

	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	
	/** The DAO for CursoRealizado objects. */
	@EJB    	
	private CursoRealizadoDAO cursoRealizadoDAO;
	
	
	
	
	
	/** @see br.ufes.inf.nemo.util.ejb3.application.CrudService#getDAO() */
	@Override
	public BaseDAO<CursoRealizado> getDAO() {
		return cursoRealizadoDAO;
	}

	
	
	
	/** @see br.ufes.inf.nemo.util.ejb3.application.CrudService#authorize() */
	@Override
	public void authorize() {
		super.authorize();
	}
	
	
	
	
	/** @see sae.core.application.CrudServiceBean#createNewEntity() */
	@Override
	protected CursoRealizado createNewEntity() {
		return new CursoRealizado();
	}
	


}
