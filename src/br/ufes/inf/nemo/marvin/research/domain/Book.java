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
	protected Book() {}

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

	/** @see br.ufes.inf.nemo.marvin.research.domain.Publication#toBibTeX() */
	@Override
	public String toBibTeX() {
		StringBuilder builder = new StringBuilder();

		// Produces the BibTeX entry for this type of publication.
		builder.append("@book{").append(getBibKey()).append(",\n"); // @book{bibKey,
		builder.append("\ttitle = {{").append(title).append("}},\n"); // title = {{Publication's Title}},
		builder.append("\tauthor = {").append(getAuthorList().toUpperCase()).append("},\n"); // author = {Author list},
		if (edition != null && !edition.isEmpty()) builder.append("\tedition = {").append(edition).append("},\n"); // edition
																																																								// =
																																																								// {Book
																																																								// edition},
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
