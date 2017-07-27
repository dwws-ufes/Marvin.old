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
import br.ufes.inf.nemo.marvin.sae.application.ManageInterestSubjectsService;
import br.ufes.inf.nemo.marvin.sae.application.ManageSeminarsService;
import br.ufes.inf.nemo.marvin.sae.application.ManageSuggestionsService;
import br.ufes.inf.nemo.marvin.sae.domain.InterestSubject;
import br.ufes.inf.nemo.marvin.sae.domain.Seminar;
import br.ufes.inf.nemo.marvin.sae.domain.Suggestion;

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

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<Seminar> getCrudService() {
		return manageSeminarsService;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageSeminars.filter.byName", "name", getI18nMessage("msgsSae", "manageSeminars.text.filter.byName")));
	}
}
