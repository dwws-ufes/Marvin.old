package br.ufes.inf.nemo.marvin.people.domain;


/**
 * Domain enum that represents people's gender .
 * 
 * 
 * @author Bruno Manzoli (manzoli2122@gmail.com)
 */
public enum Gender {
	Female("Female"),
	Male("Male");
	
	
	private final String label;

	private Gender(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
    }

}