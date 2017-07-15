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
@DiscriminatorValue("P")
public class ConferencePaper extends Publication {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** TODO: document this field. */
	private String bookTitle;

	/** Constructor. */
	protected ConferencePaper() {}

	/** Constructor. */
	public ConferencePaper(String title, int year, String pages, String doi, String publisher, String bookTitle) {
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

	/** @see br.ufes.inf.nemo.marvin.research.domain.Publication#toBibTeX() */
	@Override
	public String toBibTeX() {
		StringBuilder builder = new StringBuilder();

		// Produces the BibTeX entry for this type of publication.
		builder.append("@inproceedings{").append(getBibKey()).append(",\n"); // @inproceedings{bibKey,
		builder.append("\ttitle = {{").append(title).append("}},\n"); // title = {{Publication's Title}},
		builder.append("\tauthor = {").append(getAuthorList().toUpperCase()).append("},\n"); // author = {Author list},
		builder.append("\tbooktitle = {{").append(bookTitle).append("}},\n"); // booktitle = {{Conference proceedings
																																					// title}},
		if (pages != null && !pages.isEmpty()) builder.append("\tpages = {").append(pages).append("},\n"); // pages = {Start
																																																				// Page--End
																																																				// Page},
		if (doi != null && !doi.isEmpty()) builder.append("\tdoi = {").append(doi).append("},\n"); // doi = {Digital Object
																																																// Identifier},
		if (publisher != null && !publisher.isEmpty()) builder.append("\tpublisher = {{").append(publisher).append("}},\n"); // publisher
																																																													// =
																																																													// {{Publisher's
																																																													// name}},
		builder.append("\tyear = {").append(getYear()).append("}\n"); // year = {Publication year}
		builder.append("}\n"); // }

		return builder.toString();
	}
}
