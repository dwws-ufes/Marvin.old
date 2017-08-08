package br.ufes.inf.nemo.marvin.sae.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Seminar extends PersistentObjectSupport implements Comparable<Seminar> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The Seminar's date. */
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date date;

	/** The name of the seminar's speaker*/
	@Basic
	private String speakerName;

	/** The seminar's location */
	@Lob
	private String location;

	/** The seminar's title */
	@NotNull
	@Basic
	private String title;

	/** If the seminar is confirmed */
	@NotNull
	@Basic
	private boolean confirmed;

	/** The seminar's subject */
	@NotNull
	@OneToOne
	private InterestSubject interestSubject;

	/** The timestamp of the moment this seminar was created. */
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date creationDate;

	/** The last time the data about the seminar was updated. */
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date lastUpdateDate;

	/** Getter for date. */
	public Date getDate() {
		return date;
	}

	/** Setter for date. */
	public void setDate(Date date) {
		this.date = date;
	}

	/** Getter for speakerName. */
	public String getSpeakerName() {
		return speakerName;
	}

	/** Setter for speakerName. */
	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}

	/** Getter for location. */
	public String getLocation() {
		return location;
	}

	/** Setter for location. */
	public void setLocation(String location) {
		this.location = location;
	}

	/** Getter for title. */
	public String getTitle() {
		return title;
	}

	/** Setter for title. */
	public void setTitle(String title) {
		this.title = title;
	}

	/** Getter for confirmed. */
	public boolean isConfirmed() {
		return confirmed;
	}

	/** Setter for confirmed. */
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	/** Getter for interestSubject. */
	public InterestSubject getInterestSubject() {
		return interestSubject;
	}

	/** Setter for interestSubject. */
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

	/** Returns a string containing: Seminar's title - Formated seminar's date. */
	@Override
	public String toString() {
		return title + " - " + date.getDate() + "/" + date.getMonth() + "/" + (date.getYear() + 1900);
	}
	
	/** Returns "YES" if the seminar is confirmed and "NO" if not*/
	public String confirmedToString()
	{
		if(confirmed) return "YES";
		return "NO";
	}
}
