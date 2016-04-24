package br.ufes.inf.nemo.marvin.core.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.core.domain.Course;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;



/**
 * Local EJB interface for the component that implements the "Manage Course" use case.
 * 
 * This use case consists of a CRUD for the class Course and uses the mini CRUD framework for EJB3.
 * 
 * @author Bruno Manzoli (manzoli2122@gmail.com)
 * @see br.ufes.inf.nemo.util.ejb3.application.CrudService
 */
@Local
public interface ManageCourseService  extends CrudService<Course>{

}
