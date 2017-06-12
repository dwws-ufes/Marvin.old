package br.ufes.inf.nemo.marvin.core.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

/**
 * TODO: document this type.
 *
 * @author Gabriel Martins Miranda (garielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Entity
public class CourseAttendance extends PersistentObjectSupport implements Comparable<CourseAttendance>{
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	/** The course start date. */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	/** The course end date. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	/** The academic situation in the course. */
	@NotNull
	@Enumerated(EnumType.STRING)
	private CourseSituation courseSituation;
	
	/** Getter for start date. */
	public Date getStartDate() {
		return startDate;
	}

	/** Setter for start date. */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/** Getter for end date. */
	public Date getEndDate() {
		return endDate;
	}

	/** Setter for end date. */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/** Getter for course situation. */
	public CourseSituation getCourseSituation() {
		return courseSituation;
	}
	
	/** The academic situation in the course */
	public enum CourseSituation {
		ACTIVE("Active"),
		GRADUATED("Graduated"),
		TERMINATED("Terminated");
		
		String name;

		CourseSituation(String name) {
			this.name = name;
		}	
	}

	@Override
	public int compareTo(CourseAttendance o) {
		// Check if it's the same entity.
		return uuid.compareTo(o.uuid);
	}
}
