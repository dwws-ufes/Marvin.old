package br.ufes.inf.nemo.marvin.research.domain;

import java.util.List;

import br.ufes.inf.nemo.marvin.core.domain.Academic;

public class AcademicScore {

	private List<PublicationScore> publicationsScores;
	private Academic academic;
	private int scoreJournal;
	private int scoreConference;
	private int scoreTotal;

	public List<PublicationScore> getPublicationsScores() {
		return publicationsScores;
	}

	public void setPublicationsScores(List<PublicationScore> publicationsScores) {
		this.publicationsScores = publicationsScores;
	}

	public Academic getAcademic() {
		return academic;
	}

	public void setAcademic(Academic academic) {
		this.academic = academic;
	}

	public int getScoreJournal() {
		return scoreJournal;
	}

	public void setScoreJournal(int scoreJournal) {
		this.scoreJournal = scoreJournal;
	}

	public int getScoreConference() {
		return scoreConference;
	}

	public void setScoreConference(int scoreConference) {
		this.scoreConference = scoreConference;
	}

	public int getScoreTotal() {
		return scoreTotal;
	}

	public void setScoreTotal(int scoreTotal) {
		this.scoreTotal = scoreTotal;
	}
}
