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
@DiscriminatorValue("J")
public class JournalPaper extends Publication {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** TODO: document this field. */
	private String journal;

	/** TODO: document this field. */
	private String volume;

	/** TODO: document this field. */
	private String issn;
	
	/** Constructor. */
	protected JournalPaper() { }

	/** Constructor. */
	public JournalPaper(String title, int year, String pages, String doi, String publisher, String journal, String volume, String issn) {
		super(title, year, pages, doi, publisher);
		this.journal = journal;
		this.volume = volume;
		this.issn = issn;
	}

	/** Getter for journal. */
	public String getJournal() {
		return journal;
	}

	/** Setter for journal. */
	public void setJournal(String journal) {
		this.journal = journal;
	}

	/** Getter for volume. */
	public String getVolume() {
		return volume;
	}

	/** Setter for volume. */
	public void setVolume(String volume) {
		this.volume = volume;
	}

	/** Getter for issn. */
	public String getIssn() {
		return issn;
	}

	/** Setter for issn. */
	public void setIssn(String issn) {
		this.issn = issn;
	}
}
