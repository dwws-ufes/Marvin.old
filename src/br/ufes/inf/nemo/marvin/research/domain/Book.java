package br.ufes.inf.nemo.marvin.research.domain;

import javax.persistence.Entity;

/**
 * TODO: document this type.
 *
 * @author Thiago Rocha Salvatore
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Entity
public class Book extends Publication {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** TODO: document this field. */
	private String edition;

	/** Getter for edition. */
	public String getEdition() {
		return edition;
	}

	/** Setter for edition. */
	public void setEdition(String edition) {
		this.edition = edition;
	}
}
