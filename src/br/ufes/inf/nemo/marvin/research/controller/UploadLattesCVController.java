package br.ufes.inf.nemo.marvin.research.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.inf.nemo.marvin.research.application.UploadLattesCVService;
import br.ufes.inf.nemo.marvin.research.exceptions.LattesIdNotRegisteredException;
import br.ufes.inf.nemo.marvin.research.exceptions.LattesParseException;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Named
@ConversationScoped
public class UploadLattesCVController extends JSFController {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(UploadLattesCVController.class.getCanonicalName());

	/** TODO: document this field. */
	@Inject
	private Conversation conversation;
	
	/** TODO: document this field. */
	@EJB
	private UploadLattesCVService uploadLattesCVService;
	
	/** TODO: document this field. */
	private UploadedFile file;

	/** Getter for file. */
	public UploadedFile getFile() {
		return file;
	}

	/** Setter for file. */
	public void setFile(UploadedFile file) {
		this.file = file;
	}

	/**
	 * TODO: document this method.
	 */
	public String upload() {
		// Manages the conversation. 
		if (conversation.isTransient()) conversation.begin();
		
		try {
			// Performs the upload.
			uploadLattesCVService.uploadLattesCV(file.getInputstream());
		}
		catch (LattesIdNotRegisteredException e) {
			logger.log(Level.INFO, "Lattes ID in the uploaded CV is not registered in the system");
			addGlobalI18nMessage("msgsResearch", FacesMessage.SEVERITY_WARN, "uploadLattesCV.error.lattesIdNotRegistered.summary", new Object[] {}, "uploadLattesCV.error.lattesIdNotRegistered.detail", new Object[] { e.getLattesId(), e.getResearcherName() });
			return null;
		}
		catch (IOException | LattesParseException e) {
			logger.log(Level.INFO, "Lattes parser error.");
			addGlobalI18nMessage("msgsResearch", FacesMessage.SEVERITY_ERROR, "uploadLattesCV.error.lattesParseError.summary", "uploadLattesCV.error.lattesIdNotRegistered.detail");
			return null;
		}
		
		return null;
	}
}
