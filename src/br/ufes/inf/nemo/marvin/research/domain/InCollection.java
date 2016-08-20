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
public class InCollection extends Publication {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** TODO: document this field. */
	private String bookTitle;

	/** Getter for bookTitle. */
	public String getBookTitle() {
		return bookTitle;
	}

	/** Setter for bookTitle. */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
}
