package br.ufes.inf.nemo.marvin.sae.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Education extends PersistentObjectSupport implements Comparable<Education>{
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	/** The year when the Education has been concluded. */
	@Basic
	@NotNull
	private Integer year;
	
	@OneToOne
	@NotNull
	private EducationInstituition educationInstituition;
	
	/** The Education Type */
	@NotNull
	@Enumerated(EnumType.STRING)
	private EducationType educationType;

	/** Getter for year. */
	public Integer getYear() {
		return year;
	}
	/** Setter for year. */
	public void setYear(Integer year) {
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
		return year.compareTo(e.getYear());
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
