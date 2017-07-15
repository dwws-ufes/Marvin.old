package br.ufes.inf.nemo.marvin.research.application;

import java.io.Serializable;

import javax.ejb.Local;

@Local
public interface MatchPublicationsVenuesService extends Serializable {
	void matchPublicationsVenues(VenuesImportEvent event);
}
