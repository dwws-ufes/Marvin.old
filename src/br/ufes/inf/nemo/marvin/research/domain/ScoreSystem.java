package br.ufes.inf.nemo.marvin.research.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class ScoreSystem extends PersistentObjectSupport {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "scoreSystem")
	private Set<Score> scores;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	protected ScoreSystem() {}

	public ScoreSystem(Date startDate) {
		this.startDate = startDate;
	}

	public Set<Score> getScores() {
		return scores;
	}

	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
