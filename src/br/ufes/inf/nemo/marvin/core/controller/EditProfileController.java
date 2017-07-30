package br.ufes.inf.nemo.marvin.core.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.inf.nemo.marvin.core.domain.Academic;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Named
@SessionScoped
public class EditProfileController extends JSFController {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ChangePasswordController.class.getCanonicalName());

	/** Information on the current visitor. */
	@Inject
	private SessionController sessionController;

	/** TODO: document this field. */
	private Academic academic;

	/**
	 * TODO: document this method.
	 */
	public void checkAuthenticatedUser() {
		logger.log(Level.FINEST, "Checking the authenticated user...");

		// Obtains the authenticated user.
		academic = sessionController.getCurrentUser();
	}

	/** Getter for academic. */
	public Academic getAcademic() {
		return academic;
	}
}
