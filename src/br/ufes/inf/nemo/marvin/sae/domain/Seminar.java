package br.ufes.inf.nemo.marvin.sae.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Seminar extends PersistentObjectSupport implements Comparable<Seminar>{

	private static final long serialVersionUID = 1L;

	
	/** The timestamp of the moment this interest subject was created. */
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date date;
	
	@Basic
	private String speakerName;
	
	@Basic
	private String location;
	
	@NotNull
	@Basic
	private String title;
	
	@NotNull
	@Basic
	private boolean confirmed;
	
	@NotNull
	@OneToOne
	private InterestSubject interestSubject;
	
	/** The timestamp of the moment this interest subject was created. */
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date creationDate;

	/** The last time the data about the interest subject was updated. */
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date lastUpdateDate;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSpeakerName() {
		return speakerName;
	}

	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public InterestSubject getInterestSubject() {
		return interestSubject;
	}

	public void setInterestSubject(InterestSubject interestSubject) {
		this.interestSubject = interestSubject;
	}

	/** Getter for creationDate. */
	public Date getCreationDate() {
		return creationDate;
	}

	/** Setter for creationDate. */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/** Getter for lastUpdateDate. */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	/** Setter for lastUpdateDate. */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	@Override
	public int compareTo(Seminar o) {
		// Compare the names
		if (title == null) return 1;
		if (o.title == null) return -1;
		int cmp = title.compareTo(o.title);
		if (cmp != 0) return cmp;

		// If it's the same name, check if it's the same entity.
		return uuid.compareTo(o.uuid);
	}
	@Override
	public String toString() {
		return title + " - " + date.getDate()+"/"+date.getMonth()+"/"+(date.getYear() + 1900);
	}
}
