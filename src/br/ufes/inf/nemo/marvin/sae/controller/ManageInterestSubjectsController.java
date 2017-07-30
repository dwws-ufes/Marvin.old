package br.ufes.inf.nemo.marvin.sae.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.sae.application.ManageInterestSubjectsService;
import br.ufes.inf.nemo.marvin.sae.domain.InterestSubject;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Named
@SessionScoped
public class ManageInterestSubjectsController extends CrudController<InterestSubject> {
	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** TODO: document this field. */
	@EJB
	private ManageInterestSubjectsService manageInterestSubjectsService;

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<InterestSubject> getCrudService() {
		return manageInterestSubjectsService;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageInterestSubjects.filter.byName", "name", getI18nMessage("msgsSae", "manageInterestSubjects.text.filter.byName")));
	}
}
