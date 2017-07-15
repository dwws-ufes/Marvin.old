package br.ufes.inf.nemo.marvin.research.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Qualis extends PersistentObjectSupport {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@Basic
	@NotNull
	private String level;

	/** Default Constructor */
	protected Qualis() {}

	/** Constructor */
	public Qualis(String level) {
		this.level = level;
	}

	/** Getter for level. */
	public String getLevel() {
		return level;
	}

	/** Setter for level. */
	public void setLevel(String level) {
		this.level = level;
	}
}
