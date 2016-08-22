package br.ufes.inf.nemo.marvin.research.application;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
class LattesProduction implements Comparable<LattesProduction> {
	/** TODO: document this field. */
	private String type;

	/** TODO: document this field. */
	private int year;

	/** TODO: document this field. */
	private String title;

	/** TODO: document this field. */
	private String venue;

	/** TODO: document this field. */
	private String authors;

	/** Constructor. */
	LattesProduction(String type, int year, String title, String venue, String authors) {
		this.type = type;
		this.year = year;
		this.title = title;
		this.venue = venue;
		this.authors = authors;
	}

	/** Getter for type. */
	public String getType() {
		return type;
	}

	/** Setter for type. */
	public void setType(String type) {
		this.type = type;
	}

	/** Getter for year. */
	public int getYear() {
		return year;
	}

	/** Setter for year. */
	public void setYear(int year) {
		this.year = year;
	}

	/** Getter for title. */
	public String getTitle() {
		return title;
	}

	/** Setter for title. */
	public void setTitle(String title) {
		this.title = title;
	}

	/** Getter for venue. */
	public String getVenue() {
		return venue;
	}

	/** Setter for venue. */
	public void setVenue(String venue) {
		this.venue = venue;
	}

	/** Getter for authors. */
	public String getAuthors() {
		return authors;
	}

	/** Setter for authors. */
	public void setAuthors(String authors) {
		this.authors = authors;
	}

	/** @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(LattesProduction o) {
		int cmp = year - o.year;
		if (cmp != 0) return cmp;

		return title.compareTo(o.title);
	}
}
