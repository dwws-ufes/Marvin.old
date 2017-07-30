package br.ufes.inf.nemo.marvin.sae.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;
import br.ufes.inf.nemo.marvin.core.domain.CourseAttendance;

/**
 * TODO: document this type.
 *
 * @author Gabriel Martins Miranda (garielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Entity
public class Alumni extends PersistentObjectSupport implements Comparable<Alumni> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The course of alumni. */
	@NotNull
	@OneToOne
	private CourseAttendance courseAttendance;

	/*
	 * TODO: Por enquanto esta classe nao sera utilizada
	 * @OneToOne private Education education;
	 */

	/** The timestamp of the moment this academic was created. */
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date creationDate;

	public CourseAttendance getCourseAttendance() {
		return courseAttendance;
	}

	public void setCourseAttendance(CourseAttendance courseAttendance) {
		this.courseAttendance = courseAttendance;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public int compareTo(Alumni a) {
		// Check if it's the same entity.
		return uuid.compareTo(a.getCourseAttendance().getAcademic().getName());
	}

	@Override
	public String toString() {
		return courseAttendance.getAcademic().getName() + " - " + courseAttendance.getCourse().getName() + " at " + courseAttendance.getStartYear();
	}
}
