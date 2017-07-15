package br.ufes.inf.nemo.marvin.research.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Score extends PersistentObjectSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@NotNull
	private Qualis qualis;

	@ManyToOne
	@NotNull
	private ScoreSystem scoreSystem;

	@Basic
	@Min(0)
	@NotNull
	private int scoreConference;

	@Basic
	@Min(0)
	@NotNull
	private int scoreJournal;

	protected Score() {}

	public Score(Qualis qualis) {
		this.qualis = qualis;
	}

	public Qualis getQualis() {
		return qualis;
	}

	public void setQualis(Qualis qualis) {
		this.qualis = qualis;
	}

	public ScoreSystem getScoreSystem() {
		return scoreSystem;
	}

	public void setScoreSystem(ScoreSystem scoreSystem) {
		this.scoreSystem = scoreSystem;
	}

	public int getScoreConference() {
		return scoreConference;
	}

	public void setScoreConference(int scoreConference) {
		this.scoreConference = scoreConference;
	}

	public int getScoreJournal() {
		return scoreJournal;
	}

	public void setScoreJournal(int scoreJournal) {
		this.scoreJournal = scoreJournal;
	}

}
