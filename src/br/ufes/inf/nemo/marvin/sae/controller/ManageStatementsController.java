package br.ufes.inf.nemo.marvin.sae.controller;

import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.controller.SessionController;
import br.ufes.inf.nemo.marvin.core.domain.Course;
import br.ufes.inf.nemo.marvin.sae.application.ManageStatementsService;
import br.ufes.inf.nemo.marvin.sae.domain.Statement;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Named
@SessionScoped
public class ManageStatementsController extends CrudController<Statement> {
	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** TODO: document this field. */
	@EJB
	private ManageStatementsService manageStatementsService;

	private String course;
	private Map<String, Course> courses;

	@Inject
	private SessionController sessionController;

	public void onLoadForm() {
		course = null;
		courses = manageStatementsService.retrieveCourses(sessionController.getCurrentUser());
	}

	public void onCourseChange() {
		selectedEntity.setCourse(courses.get(course));
	}

	public void approve() {
		manageStatementsService.approve(selectedEntity);
	}

	public void reject() {
		manageStatementsService.reject(selectedEntity);
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<Statement> getCrudService() {
		return manageStatementsService;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageStatements.filter.byName", "name", getI18nMessage("msgsSae", "manageStatements.text.filter.byName")));
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Map<String, Course> getCourses() {
		return courses;
	}

	public void setCourses(Map<String, Course> courses) {
		this.courses = courses;
	}
}
