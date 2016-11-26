package br.ufes.inf.nemo.marvin.core.application;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.TextUtils;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.exceptions.InvalidPasswordCodeException;
import br.ufes.inf.nemo.marvin.core.exceptions.OperationFailedException;
import br.ufes.inf.nemo.marvin.core.persistence.AcademicDAO;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Stateless
public class ChangePasswordServiceBean implements ChangePasswordService {
	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ChangePasswordServiceBean.class.getCanonicalName());

	/** TODO: document this field. */
	@EJB
	private AcademicDAO academicDAO;

	/** @see br.ufes.inf.nemo.marvin.core.application.ManageAcademicsService#retrieveAcademicByPasswordCode(java.lang.String) */
	@Override
	public Academic retrieveAcademicByPasswordCode(String passwordCode) throws InvalidPasswordCodeException {
		// Retrieves the academic given her password code. 
		try {
			logger.log(Level.INFO, "Academic with password code {0} wants to set/change her password.", new Object[] { passwordCode });
			return academicDAO.retrieveByPasswordCode(passwordCode);
		}
		
		// In case the password code fails to retrieve a single academic, report it as invalid.
		catch (PersistentObjectNotFoundException | MultiplePersistentObjectsFoundException e) {
			logger.log(Level.WARNING, "Unable to retrieve an academic with password code: " + passwordCode, e);
			throw new InvalidPasswordCodeException(e, passwordCode);
		}
	}

	/** @see br.ufes.inf.nemo.marvin.core.application.ChangePasswordService#changePassword(java.lang.String, java.lang.String) */
	@Override
	public void changePassword(String passwordCode, String password) throws InvalidPasswordCodeException, OperationFailedException {
		try {
			// Retrieves the academic given her password code. 
			logger.log(Level.INFO, "Setting a new password for academic with password code: {0}", new Object[] { passwordCode });
			Academic academic = academicDAO.retrieveByPasswordCode(passwordCode);
			
			// Sets the new password, removes the password code.
			academic.setPassword(TextUtils.produceBase64EncodedMd5Hash(password));
			academic.setPasswordCode(null);
			
			// Saves the academic.
			academicDAO.save(academic);
		}
		
		// In case the password code fails to retrieve a single academic, report it as invalid.
		catch (PersistentObjectNotFoundException | MultiplePersistentObjectsFoundException e) {
			logger.log(Level.WARNING, "Unable to retrieve an academic with password code: " + passwordCode, e);
			throw new InvalidPasswordCodeException(e, passwordCode);
		}
		
		// In case the password cannot be encoded.
		catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// Logs and rethrows the exception for the controller to display the error to the user.
			logger.log(Level.SEVERE, "Could not find MD5 algorithm for password encription!", e);
			throw new OperationFailedException(e);
		}
	}
}
