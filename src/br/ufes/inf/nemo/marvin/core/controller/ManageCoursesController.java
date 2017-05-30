package br.ufes.inf.nemo.marvin.core.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.jbutler.ejb.controller.PersistentObjectConverterFromId;
import br.ufes.inf.nemo.marvin.core.application.ManageAcademicsService;
import br.ufes.inf.nemo.marvin.core.application.ManageCoursesService;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.domain.Course;
import br.ufes.inf.nemo.marvin.core.domain.Course.AcademicLevel;
import br.ufes.inf.nemo.marvin.core.domain.Role;

/**
 * TODO: document this type.
 *
 * @author Gabriel Martins Miranda (gabrielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Named
@SessionScoped
public class ManageCoursesController extends CrudController<Course> {
	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageCoursesController.class.getCanonicalName());
	
	/** TODO: document this field. */
	@EJB
	private ManageCoursesService manageCoursesService;

	public AcademicLevel[] getAcademicLevels()
	{
		return AcademicLevel.values();
	}
	
	/* public Academic getCourseCordinator()
	{
		return manageCoursesService.retrieveCourseCordinator(selectedEntity.getId());
	}*/
	
	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<Course> getCrudService() {
		return manageCoursesService;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new SimpleFilter("manageCourses.filter.byName", "name", getI18nMessage("msgsCore", "manageCourses.text.filter.byName")));
	}
}
