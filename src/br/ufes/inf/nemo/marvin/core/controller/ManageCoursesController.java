package br.ufes.inf.nemo.marvin.core.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.jbutler.ejb.controller.PersistentObjectConverterFromId;
import br.ufes.inf.nemo.marvin.core.application.ManageAcademicsService;
import br.ufes.inf.nemo.marvin.core.application.ManageCourseCoordinationsService;
import br.ufes.inf.nemo.marvin.core.application.ManageCoursesService;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.domain.Course;
import br.ufes.inf.nemo.marvin.core.domain.Course.AcademicLevel;
import br.ufes.inf.nemo.marvin.core.domain.CourseCoordination;
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
	
	@EJB
	private ManageAcademicsService manageAcademicsService;
	
	@EJB
	private ManageCourseCoordinationsService manageCourseCoordinationsService;
	
	private String courseCoordinator;
	private Map<String, Academic> courseCoordinators;
	
	public void onLoad()
	{
		courseCoordinator = null;
		courseCoordinators = new HashMap<String, Academic>();
		List<Academic> academics = manageAcademicsService.retrieveAcademicbyRole("Professor");
		for (Academic academic : academics) {
			courseCoordinators.put(academic.getName(), academic);
		}
	}
	
	/*TODO Alterar a funcao save para salvar tbm coordenacao
	@Override
	public String save() {
		String sReturn = super.save();
		if(courseCoordinator!=null)
		{
			CourseCoordination c = new CourseCoordination();
			c.setAcademic(courseCoordinators.get(courseCoordinator));
			c.setCourse(selectedEntity);
			manageCourseCoordinationsService.create(c);
		}
		return sReturn;
	}*/
	
	public String getCourseCoordinator() {
		return courseCoordinator;
	}

	public void setCourseCoordinator(String courseCoordinator) {
		this.courseCoordinator = courseCoordinator;
	}

	public Map<String, Academic> getCourseCoordinators() {
		return courseCoordinators;
	}

	public void setCourseCoordinators(Map<String, Academic> courseCoordinators) {
		this.courseCoordinators = courseCoordinators;
	}

	public AcademicLevel[] getAcademicLevels()
	{
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
