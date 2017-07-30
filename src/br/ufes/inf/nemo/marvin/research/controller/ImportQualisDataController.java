package br.ufes.inf.nemo.marvin.research.controller;

import java.io.IOException;
import java.util.List;
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
import br.ufes.inf.nemo.marvin.research.application.ImportQualisDataService;
import br.ufes.inf.nemo.marvin.research.application.QualifiedVenue;
import br.ufes.inf.nemo.marvin.research.domain.VenueCategory;
import br.ufes.inf.nemo.marvin.research.exceptions.CSVParseException;
import br.ufes.inf.nemo.marvin.research.exceptions.QualisLevelNotRegisteredException;

@Named
@ConversationScoped
public class ImportQualisDataController extends JSFController {

	/** Serial id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ImportQualisDataController.class.getCanonicalName());

	/** Path to the folder where the view files (web pages) for this action are placed. */
	private static final String VIEW_PATH = "/research/importQualisData/";

	/** TODO: document this field. */
	@Inject
	private Conversation conversation;

	/** TODO: document this field. */
	@EJB
	private ImportQualisDataService importQualisDataService;

	/** TODO: document this field. */
	private UploadedFile file;

	/** TODO: document this field. */
	private VenueCategory category;

	/** TODO: document this field. */
	private int year;

	/** TODO: document this field. */
	private List<QualifiedVenue> qualifiedVenues;

	public List<QualifiedVenue> getQualifiedVenues() {
		return qualifiedVenues;
	}

	/**
	 * @return the file
	 */
	public UploadedFile getFile() {
		return file;
	}

	/**
	 * @param file
	 *          the file to set
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public int getYear() {
		return year;
	}

	public boolean isRenderAcronym() {
		return category.equals(VenueCategory.CONFERENCE);
	}

	public VenueCategory[] getCategories() {
		return VenueCategory.values();
	}

	public void setCategory(VenueCategory category) {
		this.category = category;
	}

	public VenueCategory getCategory() {
		return category;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Inject
	public void init() {
		category = VenueCategory.CONFERENCE;
	}

	public String upload() {
		if (conversation.isTransient()) conversation.begin();

		try {
			qualifiedVenues = importQualisDataService.importQualisData(file.getInputstream(), category);
		}
		catch (QualisLevelNotRegisteredException e) {
			logger.log(Level.INFO, "Qualis level in the CSV file is not registered in the system.");
			addGlobalI18nMessage("msgsResearch", FacesMessage.SEVERITY_WARN, "importQualisData.error.qualisLevelNotRegisteredError.summary", new Object[] {}, "importQualisData.error.qualisLevelNotRegisteredError.detail", new Object[] { e.getLevel() });
			return null;
		}
		catch (IOException | CSVParseException e) {
			logger.log(Level.INFO, "CSV parser error.");
			addGlobalI18nMessage("msgsResearch", FacesMessage.SEVERITY_ERROR, "importQualisData.error.CSVParseError.summary", "importQualisData.error.CSVParseError.detail");
			return null;
		}
		return VIEW_PATH + "list.xhtml?faces-redirect=true";
	}

	public String cancel() {
		// Ends the conversation and redirects back to the beginning.
		if (!conversation.isTransient()) conversation.end();
		return VIEW_PATH + "index.xhtml?faces-redirect=true";
	}

	public String confirm() {
		// Assign the qualifications to the Venues
		importQualisDataService.assignQualificationsToVenues(qualifiedVenues, year);

		// Ends the conversation and renders a result page.
		if (!conversation.isTransient()) conversation.end();
		return VIEW_PATH + "success.xhtml";
	}

}
