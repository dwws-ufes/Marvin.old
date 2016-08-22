package br.ufes.inf.nemo.marvin.research.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;
import br.ufes.inf.nemo.marvin.core.domain.Academic;

/**
 * TODO: document this type.
 *
 * @author Thiago Rocha Salvatore
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "class", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("0")
public abstract class Publication extends PersistentObjectSupport implements Comparable<Publication> {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** TODO: document this field. */
	private String title;

	/** TODO: document this field. */
	private int year;

	/** TODO: document this field. */
	private String pages;

	/** TODO: document this field. */
	private String doi;

	/** TODO: document this field. */
	private String publisher;

	/** TODO: document this field. */
	@ManyToOne
	@NotNull
	private Academic owner;

	/** TODO: document this field. */
	@ElementCollection
	private List<String> authors = new ArrayList<>();

	/** Constructor. */
	protected Publication() {}

	/** Constructor. */
	public Publication(String title, int year, String pages, String doi, String publisher) {
		this.title = title;
		this.year = year;
		this.pages = pages;
		this.doi = doi;
		this.publisher = publisher;
	}

	/** Getter for title. */
	public String getTitle() {
		return title;
	}

	/** Setter for title. */
	public void setTitle(String title) {
		this.title = title;
	}

	/** Getter for year. */
	public int getYear() {
		return year;
	}

	/** Setter for year. */
	public void setYear(int year) {
		this.year = year;
	}

	/** Getter for authors. */
	public List<String> getAuthors() {
		return authors;
	}

	/** Setter for authors. */
	protected void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	/** Getter for owner. */
	public Academic getOwner() {
		return owner;
	}

	/** Setter for owner. */
	public void setOwner(Academic owner) {
		this.owner = owner;
	}

	/** Getter for pages. */
	public String getPages() {
		return pages;
	}

	/** Setter for pages. */
	public void setPages(String pages) {
		this.pages = pages;
	}

	/** Getter for doi. */
	public String getDoi() {
		return doi;
	}

	/** Setter for doi. */
	public void setDoi(String doi) {
		this.doi = doi;
	}

	/** Getter for publisher. */
	public String getPublisher() {
		return publisher;
	}

	/** Setter for publisher. */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * TODO: document this method.
	 * 
	 * @param author
	 */
	public void addAuthor(String author) {
		authors.add(author);
	}

	/** @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(Publication o) {
		int cmp = 0;

		// Compare first by owner.
		if (owner != null) cmp = owner.compareTo(o.owner);
		if (cmp != 0) return cmp;

		// Then compare by year.
		cmp = year - o.year;
		if (cmp != 0) return cmp;

		// If still a draw, compare by title.
		cmp = title.compareTo(o.title);
		if (cmp != 0) return cmp;

		// Lastly, compare by persistence id.
		return super.compareTo(o);
	}
}
