package br.ufes.inf.nemo.marvin.core.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Egress extends Academic{
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	/** The person's RG. It is a unique number used to identify a person. */
	@Basic
	@NotNull 
	@Size(max = 20)
	@Column(unique=true)
	protected String identityCard;
	
	/** The person's birth city. */
	@Basic
	@Size(max = 30)
	protected String birthCity;
	
	/** The country where the person was born. */
	@Basic
	@Size(max = 30)
	protected String nationality;
	
	/** Getter for identityCard. */
	public String getIdentityCard() {
		return identityCard;
	}

	/** Setter for identityCard. */
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	
	/** Getter for birthCity. */
	public String getBirthCity() {
		return birthCity;
	}

	/** Setter for birthCity. */
	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	/** Getter for nationality. */
	public String getNationality() {
		return nationality;
	}

	/** Setter for nationality. */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	
}
