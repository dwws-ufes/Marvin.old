package br.ufes.inf.nemo.marvin.research.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Requirement extends PersistentObjectSupport {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@Basic
	private String name;

	@Temporal(TemporalType.DATE)
	@NotNull
	private Date startDate;

	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Basic
	@NotNull
	private Long totalScore;

	@Basic
	@NotNull
	private Long journalScore;

	/** Getter for name. */
	public String getName() {
		return name;
	}

	/** Setter for name. */
	public void setName(String name) {
		this.name = name;
	}

	/** Getter for startDate. */
	public Date getStartDate() {
		return startDate;
	}

	/** Setter for startDate. */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/** Getter for endDate. */
	public Date getEndDate() {
		return endDate;
	}

	/** Setter for endDate. */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/** Getter for totalScore. */
	public Long getTotalScore() {
		return totalScore;
	}

	/** Setter for totalScore. */
	public void setTotalScore(Long totalScore) {
		this.totalScore = totalScore;
	}

	/** Getter for journalScore. */
	public Long getJournalScore() {
		return journalScore;
	}

	/** Setter for journalScore. */
	public void setJournalScore(Long journalScore) {
		this.journalScore = journalScore;
	}

}
