package br.ufes.inf.nemo.marvin.sae.controller;

import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.domain.Course;
import br.ufes.inf.nemo.marvin.sae.application.ManageAlumniHistoriesService;
import br.ufes.inf.nemo.marvin.sae.domain.Alumni;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory.DegreeArea;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory.PracticeArea;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory.SalaryRange;
import br.ufes.inf.nemo.marvin.sae.domain.Education.EducationType;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Named
@SessionScoped
public class ManageAlumniHistoriesController extends CrudController<AlumniHistory> {
	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageAlumniHistoriesController.class.getCanonicalName());

	/** TODO: document this field. */
	@EJB
	private ManageAlumniHistoriesService manageAlumniHistoriesService;

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<AlumniHistory> getCrudService() {
		return manageAlumniHistoriesService;
	}
	
	private String alumni;
	private Map<String, Alumni> alumnis;
	
	public void onLoadForm()
	{
		alumni = null;
		alumnis = manageAlumniHistoriesService.retrieveAlumnis();
	}
	

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageAlumniHistories.filter.byName", "name", getI18nMessage("msgsSae", "manageAlumniHistories.text.filter.byName")));
	}
	
	public void onAlumniChange() {
		selectedEntity.setAlumni(alumnis.get(alumni));
    }
	
	public PracticeArea[] getPracticeAreas() {
		return PracticeArea.values();
	}

	public DegreeArea[] getDegreeAreas() {
		return DegreeArea.values();
	}

	public SalaryRange[] getSalaryRanges() {
		return SalaryRange.values();
	}

	public EducationType[] getEducationTypes() {
		return EducationType.values();
	}


	public String getAlumni() {
		return alumni;
	}

	public void setAlumni(String alumni) {
		this.alumni = alumni;
	}

	public Map<String, Alumni> getAlumnis() {
		return alumnis;
	}

	public void setAlumnis(Map<String, Alumni> alumnis) {
		this.alumnis = alumnis;
	}
	
}
