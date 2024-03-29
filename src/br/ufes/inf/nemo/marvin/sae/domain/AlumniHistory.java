package br.ufes.inf.nemo.marvin.sae.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class AlumniHistory extends PersistentObjectSupport implements Comparable<AlumniHistory> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The history sent date. */
	@NotNull
	@OneToOne
	private Alumni alumni;

	/** The history sent date. */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendDate;

	/** If the alumni, currently lives in the ES. */
	@NotNull
	@Basic
	private boolean livesES;

	/** The practice area. */
	@NotNull
	@Enumerated(EnumType.STRING)
	private PracticeArea practiceArea;

	/** The degree area. */
	@NotNull
	@Enumerated(EnumType.STRING)
	private DegreeArea degreeArea;

	/** The salary range. */
	@NotNull
	@Enumerated(EnumType.STRING)
	private SalaryRange salaryRange;

	/** The education type. */
	@NotNull
	@Enumerated(EnumType.STRING)
	private EducationType educationType;

	/** Getter for Send Date. */
	public Date getSendDate() {
		return sendDate;
	}

	/** Setter for Send Date. */
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	/** Getter for Lives in ES. */
	public boolean isLivesES() {
		return livesES;
	}

	/** Setter for Lives in ES. */
	public void setLivesES(boolean livesES) {
		this.livesES = livesES;
	}

	/** Getter for Practice Area. */
	public PracticeArea getPracticeArea() {
		return practiceArea;
	}

	/** Setter for Practice Area. */
	public void setPracticeArea(PracticeArea practiceArea) {
		this.practiceArea = practiceArea;
	}

	/** Getter for Degree Area. */
	public DegreeArea getDegreeArea() {
		return degreeArea;
	}

	/** Setter for Degree Area. */
	public void setDegreeArea(DegreeArea degreeArea) {
		this.degreeArea = degreeArea;
	}

	/** Getter for Salary Range. */
	public SalaryRange getSalaryRange() {
		return salaryRange;
	}

	/** Setter for Salary Range. */
	public void setSalaryRange(SalaryRange salaryRange) {
		this.salaryRange = salaryRange;
	}

	/** Getter for Education Type. */
	public EducationType getEducationType() {
		return educationType;
	}

	/** Setter for Education Type. */
	public void setEducationType(EducationType educationType) {
		this.educationType = educationType;
	}

	/** Getter for Alumni. */
	public Alumni getAlumni() {
		return alumni;
	}

	/** Setter for Alumni. */
	public void setAlumni(Alumni alumni) {
		this.alumni = alumni;
	}

	@Override
	public int compareTo(AlumniHistory ah) {
		return sendDate.compareTo(ah.getSendDate());
	}

	/** Enum for Pratice Area. */
	public enum PracticeArea {
		ENTREPRENEUR("Entrepreneur"), PUBLIC_EMPLOYEE("Public Employee"), PRIVATE_EMPLOYEE("Private Employee"), PROFESSOR("Professor"), RESEARCHER("Researcher");

		String name;

		PracticeArea(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	/** Enum for Degree Area. */
	public enum DegreeArea {
		DEGREE_AREA("Works in degree area"), RELATED_AREA("Works in a releted area"), NON_RELATED_AREA("Works in a non-related area");

		String name;

		DegreeArea(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	/** Enum for Salary Range. */
	public enum SalaryRange {
		UNTIL_3_SM("Until 3 minimum salaries"), FROM_3_TO_5_SM("From 3 to 5 minimum salaries"), FROM_5_TO_10_SM("From 5 to 10 minimum salaries"), FROM_10_TO_15_SM("From 10 to 15 minimum salaries"), FROM_15_TO_20_SM("From 15 to 20 minimum salaries"), MORE_THAN_20_SM("More that 20 minimum salaries");

		String name;

		SalaryRange(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	/** Enum for Education Type. */
	public enum EducationType {
		HIGHER_EDUCATION("Higher Education"), SPECIALIZED_EDUCATION("Specialized Education"), MASTER_DEGREE("Master Degree"), PHD("PhD"), POST_DOCTORATE("Post-Doctorate");

		String name;

		EducationType(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	@Override
	public String toString() {
		return "History of " + alumni.toString();
	}
}
