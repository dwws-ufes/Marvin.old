package br.ufes.inf.nemo.marvin.core.application;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.inject.Named;

import br.ufes.inf.nemo.marvin.core.domain.MarvinConfiguration;
import br.ufes.inf.nemo.marvin.core.persistence.MarvinConfigurationDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.PersistentObjectNotFoundException;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Singleton
@Named("coreInfo")
public class CoreInformation implements Serializable {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(CoreInformation.class.getCanonicalName());
	
	/** TODO: document this field. */
	private static final String DEFAULT_DECORATOR_NAME = "default";
	
	/** TODO: document this field. */
	private static final String NO_MENU_DECORATOR_NAME = "nomenu";
	
	/** The DAO for MarvinConfiguration objects. */
	@EJB
	private MarvinConfigurationDAO marvinConfigurationDAO;

	/** TODO: document this field. */
	private MarvinConfiguration currentConfig;

	/** Indicates if the system is properly installed. */
	private Boolean systemInstalled;

	/** Indicates the decorator being used in the administration area. */
	private String decorator = DEFAULT_DECORATOR_NAME;

	/** Getter for currentConfig. */
	public MarvinConfiguration getCurrentConfig() {
		return currentConfig;
	}

	/** Getter for systemInstalled. */
	public Boolean getSystemInstalled() {
		// At first use, check if the system is installed.
		if (systemInstalled == null) {
			logger.log(Level.FINER, "Checking if the system has been installed...");
			
			// If the system is configured, it's installed.
			try {
				currentConfig = marvinConfigurationDAO.retrieveCurrentConfiguration();
				systemInstalled = true;
			}
			catch (PersistentObjectNotFoundException e) {
				systemInstalled = false;
			}
		}
		
		return systemInstalled;
	}

	/** Getter for decorator. */
	public String getDecorator() {
		if (! getSystemInstalled()) return NO_MENU_DECORATOR_NAME;
		return decorator;
	}
}
