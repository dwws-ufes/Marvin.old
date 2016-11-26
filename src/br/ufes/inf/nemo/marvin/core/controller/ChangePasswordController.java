package br.ufes.inf.nemo.marvin.core.controller;

import java.util.logging.Logger;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Named
@ConversationScoped
public class ChangePasswordController extends JSFController {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** Path to the folder where the view files (web pages) for this action are placed. */
	private static final String VIEW_PATH = "/core/changePassword/";

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ChangePasswordController.class.getCanonicalName());

	/** TODO: document this field. */
	private String passwordCode;

	/** Getter for passwordCode. */
	public String getPasswordCode() {
		return passwordCode;
	}

	/** Setter for passwordCode. */
	public void setPasswordCode(String passwordCode) {
		this.passwordCode = passwordCode;
	}
}
