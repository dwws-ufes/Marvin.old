package br.ufes.inf.nemo.marvin.research.domain;

import javax.persistence.Entity;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

/**
 * TODO: document this type.
 *
 * @author Thiago Rocha Salvatore
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Entity
public class Author extends PersistentObjectSupport {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** TODO: document this field. */
	private String name;

	/** Getter for name. */
	public String getName() {
		return name;
	}

	/** Setter for name. */
	public void setName(String name) {
		this.name = name;
	}
}
