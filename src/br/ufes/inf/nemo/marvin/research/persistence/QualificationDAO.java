package br.ufes.inf.nemo.marvin.research.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.research.domain.Qualification;
import br.ufes.inf.nemo.marvin.research.domain.Venue;

@Local
public interface QualificationDAO extends BaseDAO<Qualification> {
	List<Qualification> retrieveByVenue(Venue venue);

	List<Qualification> retrieveByYear(int year);

	Qualification retrieveClosestByVenueAndYear(Venue venue, Integer refYear) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException;

	Qualification retrieveByVenueAndYear(Venue venue, Integer year);
}
