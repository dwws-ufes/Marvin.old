package br.ufes.inf.nemo.marvin.research.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.inf.nemo.jbutler.ejb.controller.PersistentObjectConverterFromId;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.persistence.AcademicDAO;
import br.ufes.inf.nemo.marvin.research.application.CalculateAcademicsScoresService;
import br.ufes.inf.nemo.marvin.research.application.ListResearchersService;
import br.ufes.inf.nemo.marvin.research.domain.AcademicScore;
import br.ufes.inf.nemo.marvin.research.domain.PublicationScore;
import br.ufes.inf.nemo.marvin.research.exceptions.ScoreSystemNotRegisteredException;

@Named
@ConversationScoped
public class CalculateAcademicsScoresController extends JSFController {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	/** The start year to select which publications will have their scores calculated. */
	private int startYear;
	
	/** The end year to select which publications will have their scores calculated. */
	private int endYear;
	
	/** The researchers currently stored in the system. */
	private List<Academic> researchers;

	/** The selected researchers that will have their scores calculated. */
	private List<Academic> selectedResearchers;
	
	/** The selected academic score to display more information. */
	private AcademicScore selectedAcademicScore;
	
	/** The logger. */
	private static final Logger logger = Logger.getLogger(CalculateAcademicsScoresController.class.getCanonicalName());
	
	/** Path to the folder where the view files (web pages) for this action are placed. */
	private static final String VIEW_PATH = "/research/calculateAcademicsScores/";

	/** TODO: document this field. */
	@Inject
	private Conversation conversation;
	
	/** TODO: document this field. */
	private PersistentObjectConverterFromId<Academic> academicConverter;
	
	/** TODO: document this field. */
	private List<AcademicScore> academicScores;
	
	/** TODO: document this field. */
	private List<PublicationScore> publicationScores;
	
	/** TODO: document this field. */
	@EJB
	private ListResearchersService listResearchersService;
	
	@EJB
	private CalculateAcademicsScoresService calculateAcademicsScoresService;

	/** Getter for reseachers. */
	public List<Academic> getResearchers() {
		return researchers;
	}
	
	/** Setter for reseachers. */
	public void setResearchers(List<Academic> researchers) {
		this.researchers = researchers;
	}
	
	/** Getter for selected researchers. */
	public List<Academic> getSelectedResearchers() {
		return selectedResearchers;
	}

	/** Setter for selected researchers. */
	public void setSelectedResearchers(List<Academic> selectedResearchers) {
		this.selectedResearchers = selectedResearchers;
	}
	
	/** Getter for selected academic score. */
	public AcademicScore getSelectedAcademicScore() {
		return selectedAcademicScore;
	}

	/** Setter for selected academic score. */
	public void setSelectedAcademicScore(AcademicScore selectedAcademicScore) {
		this.selectedAcademicScore = selectedAcademicScore;
	}

	/** Getter for academic converter. */
	public PersistentObjectConverterFromId<Academic> getAcademicConverter() {
		return academicConverter;
	}
	
	/** Getter for publication scores. */
	public List<PublicationScore> getPublicationScores() {
		return publicationScores;
	}
	
	/** Setter for publication scores. */
	public void setPublicationScores(List<PublicationScore> publicationScores) {
		this.publicationScores = publicationScores;
	}
	
	/** Getter for start year. */
	public int getStartYear() {
		return startYear;
	}
	
	/** Setter for start year. */
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	
	/** Setter for end year. */
	public int getEndYear() {
		return endYear;
	}
	
	/** Setter for end year. */
	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	
	/** Getter for academic scores. */
	public List<AcademicScore> getAcademicScores() {
		return academicScores;
	}
	
	@Inject
	public void init(AcademicDAO academicDAO) {
		logger.log(Level.FINE, "Initializing CalculateAcademicsScoresController: loading researchers and creating the academic converter...");
		researchers = listResearchersService.listResearchers();
		academicConverter = new PersistentObjectConverterFromId<>(academicDAO);

		// Begin the conversation.
		if (conversation.isTransient()) conversation.begin();
	}
	
	public String back() {
		logger.log(Level.FINE, "Going back to the configuration screen...");
		return VIEW_PATH + "index.xhtml";
	}
	
	public String startOver() {
		logger.log(Level.FINE, "Ending the conversation and starting over...");
		if (!conversation.isTransient()) conversation.end();
		return VIEW_PATH + "index.xhtml?faces-redirect=true";
	}
	
	public String calculate() {
		logger.log(Level.FINE, "Calculating the scores for the selected academics...");
		try {
			academicScores = calculateAcademicsScoresService.calculateAcademicsScore(selectedResearchers, startYear, endYear);
		} catch (ScoreSystemNotRegisteredException e) {
			logger.log(Level.INFO, "No active score system available.");
			addGlobalI18nMessage("msgsResearch", FacesMessage.SEVERITY_ERROR, "calculateAcademicsScores.error.noActiveScoreSystemError.summary", "calculateAcademicsScores.error.noActiveScoreSystemError.detail");
			return null;
		}

		return VIEW_PATH + "scores.xhtml";
	}
	
	public void onRowSelect(SelectEvent event) {
		selectedAcademicScore = (AcademicScore) event.getObject();
		setPublicationScores(selectedAcademicScore.getPublicationsScores());
	}
	
}
