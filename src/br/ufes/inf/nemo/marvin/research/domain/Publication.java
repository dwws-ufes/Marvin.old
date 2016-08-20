package br.ufes.inf.nemo.marvin.research.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Publication extends PersistentObjectSupport {
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
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Author> authors = new ArrayList<>();

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
	public List<Author> getAuthors() {
		return authors;
	}

	/** Setter for authors. */
	public void setAuthors(List<Author> authors) {
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
}
