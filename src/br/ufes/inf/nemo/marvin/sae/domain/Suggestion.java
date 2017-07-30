package br.ufes.inf.nemo.marvin.sae.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;
import br.ufes.inf.nemo.marvin.core.domain.Course;

@Entity
public class Suggestion extends PersistentObjectSupport implements Comparable<Suggestion> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The course start date. */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendDate;

	/** The Course that the suggestion relates */
	@NotNull
	@OneToOne
	private Course course;

	/** The content of suggestion. */
	@NotNull
	@Lob
	@Basic
	private String content;

	/** Getter for Send Date. */
	public Date getSendDate() {
		return sendDate;
	}

	/** Setter for Send Date. */
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	/** Getter for Course. */
	public Course getCourse() {
		return course;
	}

	/** Setter for Course. */
	public void setCourse(Course course) {
		this.course = course;
	}

	/** Getter for Content. */
	public String getContent() {
		return content;
	}

	/** Setter for Content. */
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int compareTo(Suggestion s) {
		return sendDate.compareTo(s.getSendDate());
	}

	@Override
	public String toString() {
		return course.getName() + " - " + sendDate.toGMTString();
	}

}
