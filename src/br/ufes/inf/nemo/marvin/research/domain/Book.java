package br.ufes.inf.nemo.marvin.research.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * TODO: document this type.
 *
 * @author Thiago Rocha Salvatore
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Entity
@DiscriminatorValue("B")
public class Book extends Publication {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** TODO: document this field. */
	private String edition;
	
	/** Constructor. */
	protected Book() { }

	/** Constructor. */
	public Book(String title, int year, String pages, String doi, String publisher, String edition) {
		super(title, year, pages, doi, publisher);
		this.edition = edition;
	}

	/** Getter for edition. */
	public String getEdition() {
		return edition;
	}

	/** Setter for edition. */
	public void setEdition(String edition) {
		this.edition = edition;
	}
}
