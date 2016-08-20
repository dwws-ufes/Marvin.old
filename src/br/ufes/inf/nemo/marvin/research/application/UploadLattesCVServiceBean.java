package br.ufes.inf.nemo.marvin.research.application;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.persistence.AcademicDAO;
import br.ufes.inf.nemo.marvin.research.domain.Publication;
import br.ufes.inf.nemo.marvin.research.exceptions.LattesIdNotRegisteredException;
import br.ufes.inf.nemo.marvin.research.exceptions.LattesParseException;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Stateless
@RolesAllowed({ "SysAdmin", "Professor" })
public class UploadLattesCVServiceBean implements UploadLattesCVService {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(UploadLattesCVServiceBean.class.getCanonicalName());

	/** The DAO for Academic objects. */
	@EJB
	private AcademicDAO academicDAO;

	/** @see br.ufes.inf.nemo.marvin.research.application.UploadLattesCVService#uploadLattesCV(java.io.InputStream) */
	@Override
	public Set<Publication> uploadLattesCV(InputStream inputStream) throws LattesIdNotRegisteredException, LattesParseException {
		Set<Publication> publications = new HashSet<>();

		// Parses the Lattes CV.
		LattesParser parser = new LattesParser(inputStream);
		parser.parse();

		// Retrieves some basic information.
		Long lattesId = parser.getLattesId();
		String researcherName = parser.getResearcherName();

		try {
			// Checks if the curriculum's ID matches an ID registered in the system.
			Academic owner = academicDAO.retrieveByLattesId(lattesId);
			logger.log(Level.INFO, "Found academic with Lattes ID {0}: {1} ({2}).", new Object[] { lattesId, owner.getName(), owner.getEmail() });
		}
		catch (PersistentObjectNotFoundException e) {
			// If there is no academic with the given Lattes ID, throw an exception from the domain.
			logger.log(Level.WARNING, "No academics found with Lattes ID {0} (name: {1}). Cannot assign production to anyone in the system.", new Object[] { lattesId, researcherName });
			throw new LattesIdNotRegisteredException(lattesId, researcherName);
		}
		catch (MultiplePersistentObjectsFoundException e) {
			// This is a bug. Log and throw a runtime exception.
			logger.log(Level.SEVERE, "Multiple academics found with the same Lattes ID: " + lattesId, e);
			throw new EJBException(e);
		}

		// Returns the set of publications extracted from the CV.
		return publications;
	}

	/**
	 * @see br.ufes.inf.nemo.marvin.research.application.UploadLattesCVService#assignPublicationsToAcademic(java.util.Set,
	 *      br.ufes.inf.nemo.marvin.core.domain.Academic)
	 */
	@Override
	public void assignPublicationsToAcademic(Set<Publication> publications, Academic owner) {
		// TODO Auto-generated method stub

	}
}
