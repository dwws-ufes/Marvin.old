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
@DiscriminatorValue("C")
public class BookChapter extends Publication {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** TODO: document this field. */
	private String bookTitle;
	
	/** Constructor. */
	protected BookChapter() { }

	/** Constructor. */
	public BookChapter(String title, int year, String pages, String doi, String publisher, String bookTitle) {
		super(title, year, pages, doi, publisher);
		if (bookTitle.length() > 254) bookTitle = bookTitle.substring(0, 254);
		this.bookTitle = bookTitle;
	}

	/** Getter for bookTitle. */
	public String getBookTitle() {
		return bookTitle;
	}

	/** Setter for bookTitle. */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
}
