package br.ufes.inf.nemo.marvin.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.application.ManageCourseCoordinationsService;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.domain.Course;
import br.ufes.inf.nemo.marvin.core.domain.CourseCoordination;

/**
 * TODO: document this type.
 *
 * @author Gabriel Martins Miranda (gabrielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Named
@SessionScoped
public class ManageCourseCoordinationsController extends CrudController<CourseCoordination> {
	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageCourseCoordinationsController.class.getCanonicalName());

	/** TODO: document this field. */

	@EJB
	private ManageCourseCoordinationsService manageCourseCoordinationsService;

	private String academic;
	private Map<String, Academic> academics;
	private String course;
	private Map<String, Course> courses;

	public void onLoad() {
		course = null;
		academic = null;
		courses = manageCourseCoordinationsService.retrieveCourses(false);
		academics = manageCourseCoordinationsService.retrieveAcademics(false);
	}

	public void onAcademicChange() {
		selectedEntity.setAcademic(academics.get(academic));
	}

	public void onCourseChange() {
		selectedEntity.setCourse(courses.get(course));
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<CourseCoordination> getCrudService() {
		return manageCourseCoordinationsService;
	}

	public String getAcademic() {
		return academic;
	}

	public void setAcademic(String academic) {
		this.academic = academic;
	}

	public Map<String, Academic> getAcademics() {
		return academics;
	}

	public void setAcademics(Map<String, Academic> academics) {
		this.academics = academics;
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

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new SimpleFilter("manageCourseCoordinations.filter.byName", "name", getI18nMessage("msgsCore", "manageCourseCoordinations.text.filter.byName")));
	}

	@Override
	public void delete() {
		logger.log(Level.INFO, "Disable entity...");
		List<Object> notDeleted = new ArrayList<Object>();

		// Disables the entities that are in the trash can. Validates each exclusion, but don't stop in case of errors.
		for (CourseCoordination entity : trashCan)
			if (entity.getEndDate() == null) manageCourseCoordinationsService.disable(entity);

		// Writes the status message (only if at least one entity was deleted successfully). Empties it afterwards.
		trashCan.removeAll(notDeleted);
		if (!trashCan.isEmpty()) {
			addGlobalI18nMessage(getBundleName(), FacesMessage.SEVERITY_INFO, getBundlePrefix() + ".text.disableSucceeded", trashCan.size());
			trashCan.clear();
		}

		// Clears the selection.
		selectedEntity = null;
	}
}
