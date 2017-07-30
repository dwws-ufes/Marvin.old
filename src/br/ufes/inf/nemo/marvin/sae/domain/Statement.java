package br.ufes.inf.nemo.marvin.sae.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;
import br.ufes.inf.nemo.marvin.core.domain.Course;

@Entity
public class Statement extends PersistentObjectSupport implements Comparable<Statement>{
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	/** The statement send date. */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendDate;
	
	/** The content of statement. */
	@NotNull
	@Lob @Basic
	private String content;
	
	/** The Statement Status */
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatementStatus statementStatus;
	
	/** The Course that the statement relates */
	@NotNull
	@OneToOne
	private Course course;
	
	/** Getter for Send Date. */
	public Date getSendDate() {
		return sendDate;
	}
	/** Setter for Send Date. */
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	/** Getter for Content. */
	public String getContent() {
		return content;
	}
	/** Setter for Content. */
	public void setContent(String content) {
		this.content = content;
	}
	/** Getter for Statement Status. */
	public StatementStatus getStatementStatus() {
		return statementStatus;
	}
	/** Setter for Statement Status. */
	public void setStatementStatus(StatementStatus statementStatus) {
		this.statementStatus = statementStatus;
	}
	/** Getter for Course. */
	public Course getCourse() {
		return course;
	}
	/** Setter for Course. */
	public void setCourse(Course course) {
		this.course = course;
	}
	@Override
	public int compareTo(Statement s) {
		return sendDate.compareTo(s.getSendDate());
	}
	
	@Override
	public String toString() {
		return course.getName() + " - " + sendDate.toGMTString();
	}
	
	public enum StatementStatus
	{
		PENDING("Pending"),
		APPROVED("Approved"),
		DISAPPROVED("Disapproved");
	
		String name;

		StatementStatus(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}	
		
		public static StatementStatus getByName(String name)
		{	
			switch(name){
				case "Pending": {return StatementStatus.PENDING;}
				case "Approved": {return StatementStatus.APPROVED;}
				default: {return StatementStatus.DISAPPROVED;}
			}
		}
	}
}
