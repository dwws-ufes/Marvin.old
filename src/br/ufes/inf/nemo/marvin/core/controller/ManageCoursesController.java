package br.ufes.inf.nemo.marvin.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.application.ManageCoursesService;
import br.ufes.inf.nemo.marvin.core.domain.Course;
import br.ufes.inf.nemo.marvin.core.domain.Course.AcademicLevel;

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

	/** TODO: document this field. */
	@EJB
	private ManageCoursesService manageCoursesService;

	public AcademicLevel[] getAcademicLevels() {
		return AcademicLevel.values();
	}

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
