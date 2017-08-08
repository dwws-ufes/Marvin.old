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

	/** The timestamp of the moment this alumni was created. */
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date creationDate;

	/** Getter for Course Attendance. */
	public CourseAttendance getCourseAttendance() {
		return courseAttendance;
	}

	/** Setter for Course Attendance. */
	public void setCourseAttendance(CourseAttendance courseAttendance) {
		this.courseAttendance = courseAttendance;
	}

	/** Getter for Creation Date. */
	public Date getCreationDate() {
		return creationDate;
	}

	/** Setter for Creation Date. */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public int compareTo(Alumni a) {
		// Check if it's the same entity.
		return uuid.compareTo(a.getCourseAttendance().getAcademic().getName());
	}

	/** Returns a string encompassing the academic name, the course name and the course's start date. */
	@Override
	public String toString() {
		return courseAttendance.getAcademic().getName() + " - " + courseAttendance.getCourse().getName() + " at " + courseAttendance.getStartYear();
	}
}
