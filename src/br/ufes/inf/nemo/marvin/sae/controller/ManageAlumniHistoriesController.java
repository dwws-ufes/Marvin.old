package br.ufes.inf.nemo.marvin.sae.controller;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.sae.application.ManageAlumniHistoriesService;
import br.ufes.inf.nemo.marvin.sae.application.ManageAlumnisService;
import br.ufes.inf.nemo.marvin.sae.domain.Alumni;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory;

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

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageAlumniHistories.filter.byName", "name", getI18nMessage("msgsCore", "manageAlumniHistories.text.filter.byName")));
	}
}