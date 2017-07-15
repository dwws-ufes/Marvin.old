package br.ufes.inf.nemo.marvin.research.exceptions;

public class QualisLevelNotRegisteredException extends Exception {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** TODO: document this field. */
	private String level;

	/** Constructor. */
	public QualisLevelNotRegisteredException(String level) {
		this.level = level;
	}

	/** Getter for researcherName. */
	public String getLevel() {
		return level;
	}

}
