package br.ufes.inf.nemo.marvin.research.application;

import br.ufes.inf.nemo.marvin.research.domain.Qualis;
import br.ufes.inf.nemo.marvin.research.domain.Venue;

public class QualifiedVenue implements Comparable<QualifiedVenue> {

	private Venue venue;
	private Qualis qualis;
	
	public QualifiedVenue(Venue venue, Qualis qualis) {
		this.setQualis(qualis);
		this.setVenue(venue);
	}

	public Qualis getQualis() {
		// TODO Auto-generated method stub
		return qualis;
	}

	public void setQualis(Qualis qualis) {
		this.qualis = qualis;
	}
	
	public Venue getVenue() {
		// TODO Auto-generated method stub
		return venue;
	}


	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	@Override
	public int compareTo(QualifiedVenue o) {
		int cmp = 0;
		// Compares the venues
		cmp = venue.compareTo(o.venue);
		return cmp;
	}

}
