package br.ufes.inf.nemo.marvin.sae.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.domain.Course;

/**
 * TODO: document this type.
 *
 * @author Gabriel Martins Miranda (garielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Entity
public class Alumni extends PersistentObjectSupport implements Comparable<Alumni>{
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	/** The alumni age. */
	@NotNull
	private short age;  
	
	/** The course of alumni. */
	@NotNull
	@OneToOne
	private Course course;
	
	/** The user of alumni. */
	@NotNull
	@OneToOne
	private Academic academic;
	
	/** The education of alumni. */
	@NotNull
	@OneToOne
	private Education education;
	
	/** The history of alumni */
	@NotNull
	@OneToOne
	private AlumniHistory alumniHistory;

	/** Getter for course. */
	public Course getCourse() {
		return course;
	}
	/** Setter for course. */
	public void setCourse(Course course) {
		this.course = course;
	}

	/** Getter for academic. */
	public Academic getAcademic() {
		return academic;
	}
	/** Setter for academic. */
	public void setAcademic(Academic academic) {
		this.academic = academic;
	}
	/** Getter for age. */
	public short getAge() {
		return age;
	}
	/** Setter for age. */
	public void setAge(short age) {
		this.age = age;
	}
	/** Getter for education. */
	public Education getEducation() {
		return education;
	}
	/** Setter for education. */
	public void setEducation(Education education) {
		this.education = education;
	}
	/** Getter for Alumni History. */
	public AlumniHistory getAlumniHistory() {
		return alumniHistory;
	}
	/** Setter for Alumni History. */
	public void setAlumniHistory(AlumniHistory alumniHistory) {
		this.alumniHistory = alumniHistory;
	}
	@Override
	public int compareTo(Alumni a) {
		// Check if it's the same entity.
		return uuid.compareTo(a.getAcademic().getName());
	}
	
	@Override
	public String toString() {
		return academic.getName();
	}
}
