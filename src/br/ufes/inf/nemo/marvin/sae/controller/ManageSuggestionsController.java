package br.ufes.inf.nemo.marvin.sae.controller;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.sae.application.ManageSuggestionsService;
import br.ufes.inf.nemo.marvin.sae.domain.Suggestion;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Named
@SessionScoped
public class ManageSuggestionsController extends CrudController<Suggestion> {
	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageSuggestionsController.class.getCanonicalName());

	/** TODO: document this field. */
	@EJB
	private ManageSuggestionsService manageSuggestionsService;

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<Suggestion> getCrudService() {
		return manageSuggestionsService;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageSuggestions.filter.byName", "name", getI18nMessage("msgsCore", "manageSuggestions.text.filter.byName")));
	}
}
