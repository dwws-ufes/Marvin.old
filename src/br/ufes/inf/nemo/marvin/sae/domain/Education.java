package br.ufes.inf.nemo.marvin.sae.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Education extends PersistentObjectSupport implements Comparable<Education>{
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	/** The state where the Education has been concluded. */
	@Basic
	@NotNull
	@Size(max = 20)
	private String state;
	
	/** The country where the Education has been concluded. */
	@Basic
	@NotNull
	@Size(max = 30)
	private String country;
	
	/** The educational instituition where the Education has been concluded. */
	@Basic
	@NotNull
	@Size(max = 100)
	private String educationalInstitution;
	
	/** The year when the Education has been concluded. */
	@Basic
	@NotNull
	private int year;
	
	/** The Education Type */
	@NotNull
	@Enumerated(EnumType.STRING)
	private EducationType educationType;
	
	/** Getter for state. */
	public String getState() {
		return state;
	}
	/** Setter for state. */
	public void setState(String state) {
		this.state = state;
	}
	/** Getter for country. */
	public String getCountry() {
		return country;
	}
	/** Setter for country. */
	public void setCountry(String country) {
		this.country = country;
	}
	/** Getter for educational institution. */
	public String getEducationalInstitution() {
		return educationalInstitution;
	}
	/** Setter for educational institution. */
	public void setEducationalInstitution(String educationalInstitution) {
		this.educationalInstitution = educationalInstitution;
	}
	/** Getter for year. */
	public int getYear() {
		return year;
	}
	/** Setter for year. */
	public void setYear(int year) {
		this.year = year;
	}
	/** Getter for Education Type. */
	public EducationType getEducationType() {
		return educationType;
	}
	/** Setter for Education Type. */
	public void setEducationType(EducationType educationType) {
		this.educationType = educationType;
	}

	@Override
	public int compareTo(Education e) {
		return educationalInstitution.compareTo(e.getEducationalInstitution());
	}
	
	public enum EducationType
	{
		HIGHER_EDUCATION("Higher Education"),
		SPECIALIZED_EDUCATION("Specialized Education"),
		MASTER_DEGREE("Master Degree"),
		PHD("PhD"),
		POST_DOCTORATE("Post-Doctorate");

		String name;

		EducationType(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}
		
		public static EducationType getByName(String name)
		{	
			switch(name){
				case "Higher Education": {return EducationType.HIGHER_EDUCATION;}
				case "Specialized Education": {return EducationType.SPECIALIZED_EDUCATION;}
				case "Master Degree": {return EducationType.MASTER_DEGREE;}
				case "PhD": {return EducationType.PHD;}
				default: {return EducationType.POST_DOCTORATE;}
			}
		}
	}
}
