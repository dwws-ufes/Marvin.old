package br.ufes.inf.nemo.marvin.sae.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	
	/** The course of alumni. */
	@NotNull
	@OneToOne
	private Course course;
	
	/** The user of alumni. */
	@NotNull
	@OneToOne
	private Academic academic;
	
	/** The education of alumni. */
	@OneToOne
	private Education education;
	
	/** The history of alumni */
	@OneToOne
	private AlumniHistory alumniHistory;
	
	/** The timestamp of the moment this academic was created. */
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date creationDate;

	/** The last time the data about the user was updated. */
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date lastUpdateDate;

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
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
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
