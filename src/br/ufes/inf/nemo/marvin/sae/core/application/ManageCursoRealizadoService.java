package br.ufes.inf.nemo.marvin.sae.core.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.sae.core.domain.CursoRealizado;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;


/**
 * Local EJB interface for the component that implements the "Manage CursoRealizado" use case.
 * 
 * This use case consists of a CRUD for the class CursoRealizado and uses the mini CRUD framework for EJB3.
 * 
 * @author Bruno Manzoli (manzoli2122@gmail.com)
 * @see br.ufes.inf.nemo.util.ejb3.application.CrudService
 */
@Local
public interface ManageCursoRealizadoService extends CrudService<CursoRealizado>{

}
