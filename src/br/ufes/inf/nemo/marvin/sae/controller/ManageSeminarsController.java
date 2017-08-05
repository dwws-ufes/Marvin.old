package br.ufes.inf.nemo.marvin.sae.controller;

import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.sae.application.ManageInterestSubjectsService;
import br.ufes.inf.nemo.marvin.sae.application.ManageSeminarsService;
import br.ufes.inf.nemo.marvin.sae.domain.InterestSubject;
import br.ufes.inf.nemo.marvin.sae.domain.Seminar;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Named
@SessionScoped
public class ManageSeminarsController extends CrudController<Seminar> {
	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** TODO: document this field. */
	@EJB
	private ManageSeminarsService manageSeminarsService;
	
	@EJB
	private ManageInterestSubjectsService manageInterestSubjectsService;

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<Seminar> getCrudService() {
		return manageSeminarsService;
	}
	
	private String interestSubject;
	private Map<String, InterestSubject> interestSubjects;
	
	public void onLoadForm() {
		interestSubject = null;
		if(selectedEntity.getInterestSubject() != null) interestSubject = selectedEntity.getInterestSubject().getName();
		interestSubjects = manageInterestSubjectsService.retriveMap();
	}
	
	public void onInterestSubjectChange() {
		selectedEntity.setInterestSubject(interestSubjects.get(interestSubject));
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageSeminars.filter.byName", "name", getI18nMessage("msgsSae", "manageSeminars.text.filter.byName")));
	}

	public String getInterestSubject() {
		return interestSubject;
	}

	public void setInterestSubject(String interestSubject) {
		this.interestSubject = interestSubject;
	}

	public Map<String, InterestSubject> getInterestSubjects() {
		return interestSubjects;
	}

	public void setInterestSubjects(Map<String, InterestSubject> interestSubjects) {
		this.interestSubjects = interestSubjects;
	}
}
