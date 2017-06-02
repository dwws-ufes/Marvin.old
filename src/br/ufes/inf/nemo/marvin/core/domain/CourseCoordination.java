package br.ufes.inf.nemo.marvin.core.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
public class CourseCoordination extends PersistentObjectSupport{
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	/** The coordination start date. */
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	/** The coordination end date. */
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	/** The course of coordination. */
	@NotNull
	@ManyToOne
	private Course course;
	
	/** The course coordinator. */
	@NotNull
	@ManyToOne
	private Academic academic;
	
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Academic getAcademic() {
		return academic;
	}

	public void setAcademic(Academic academic) {
		this.academic = academic;
	}
	
}
