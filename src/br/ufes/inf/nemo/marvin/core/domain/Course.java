package br.ufes.inf.nemo.marvin.core.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;


/**
 * TODO: document this type.
 *
 * @author Gabriel Martins Miranda (garielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Entity
public class Course extends PersistentObjectSupport{

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	/** The course' name. */
	@Basic
	@NotNull
	@Size(max = 100)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private AcademicLevel academicLevel;
	
	/** Getter for name. */
	public String getName() {
		return name;
	}

	/** Setter for name. */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * TODO: document this type.
	 *
	 * @author Gabriel Martins Miranda (garielmartinsmiranda@gmail.com)
	 * @version 1.0
	 */
	public enum AcademicLevel {
		Undergraduate, Graduate;
		
		@Override
		public String toString() {
			if(this.equals(AcademicLevel.Undergraduate)) return "Undergraduate";
			else return "Graduate";
		}
	}
}
