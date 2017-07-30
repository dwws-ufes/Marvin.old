package br.ufes.inf.nemo.marvin.sae.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class InterestSubject extends PersistentObjectSupport implements Comparable<InterestSubject> {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Basic
	private String name;

	/** The timestamp of the moment this interest subject was created. */
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date creationDate;

	/** The last time the data about the interest subject was updated. */
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date lastUpdateDate;

	/** Getter for name. */
	public String getName() {
		return name;
	}

	/** Setter for name. */
	public void setName(String name) {
		this.name = name;
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
	public int compareTo(InterestSubject o) {
		// Compare the names
		if (name == null) return 1;
		if (o.name == null) return -1;
		int cmp = name.compareTo(o.name);
		if (cmp != 0) return cmp;

		// If it's the same name, check if it's the same entity.
		return uuid.compareTo(o.uuid);
	}

	@Override
	public String toString() {
		return name;
	}
}
