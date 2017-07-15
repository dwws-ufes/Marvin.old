package br.ufes.inf.nemo.marvin.research.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Qualification extends PersistentObjectSupport {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@NotNull
	private Qualis qualis;

	@ManyToOne
	@NotNull
	private Venue venue;

	@Basic
	@Min(0)
	@NotNull
	private int year;

	protected Qualification() {}

	public Qualification(int year, Qualis qualis, Venue venue) {
		this.qualis = qualis;
		this.venue = venue;
		this.year = year;
	}

	/** Getter for qualis. */
	public Qualis getQualis() {
		return qualis;
	}

	/** Setter for qualis. */
	public void setQualis(Qualis qualis) {
		this.qualis = qualis;
	}

	/** Getter for year. */
	public int getYear() {
		return year;
	}

	/** Setter for year. */
	public void setYear(int year) {
		this.year = year;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

}
