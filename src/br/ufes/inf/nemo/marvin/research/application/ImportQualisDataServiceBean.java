package br.ufes.inf.nemo.marvin.research.application;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.research.domain.Qualification;
import br.ufes.inf.nemo.marvin.research.domain.Qualis;
import br.ufes.inf.nemo.marvin.research.domain.Venue;
import br.ufes.inf.nemo.marvin.research.domain.VenueCategory;
import br.ufes.inf.nemo.marvin.research.exceptions.CSVParseException;
import br.ufes.inf.nemo.marvin.research.exceptions.QualisLevelNotRegisteredException;
import br.ufes.inf.nemo.marvin.research.persistence.QualificationDAO;
import br.ufes.inf.nemo.marvin.research.persistence.QualisDAO;
import br.ufes.inf.nemo.marvin.research.persistence.VenueDAO;

@Stateless
@RolesAllowed({"SysAdmin"})
public class ImportQualisDataServiceBean implements ImportQualisDataService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ImportQualisDataServiceBean.class.getCanonicalName());

	@EJB
	private VenueDAO venueDAO;
	
	@EJB
	private QualisDAO qualisDAO;
	
	@EJB
	private QualificationDAO qualificationDAO;

	
	/**Calls Venue-Publication Matching*/
	@Inject
	private Event<VenuesImportEvent> venuesImportEvent; 

	@Override
	public List<QualifiedVenue> importQualisData(InputStream inputStream, VenueCategory category)
			throws CSVParseException, QualisLevelNotRegisteredException {
		
		CSVParser csvParser = new CSVParser(inputStream, category);
		List<QualifiedVenue> qualifiedVenues = verifyParsedData(csvParser.getVenuesMap(), category);
		return qualifiedVenues;
	}

	private List<QualifiedVenue> verifyParsedData(Map<Venue, String> parsedData, VenueCategory category)
			throws QualisLevelNotRegisteredException {
		//Creates a new set that holds the name and reference to all registered venues
		Map<String,Venue> venues = new HashMap<String,Venue>();
		for (Venue v : venueDAO.retrieveByCategory(category)) {
			venues.put(v.getName().toLowerCase(), v);
		}
		
		//Creates a new set that holds the information of the Venue objects and their respective Qualis
		List<QualifiedVenue> qualifiedVenues = new LinkedList<QualifiedVenue>();
		for (Venue parsedVenue : parsedData.keySet()) {
			// Retrieves the Qualis level
			String level = parsedData.get(parsedVenue);			
			try {
				Qualis qualis = qualisDAO.retrieveByLevel(level);
				String parsedVenueName = parsedVenue.getName().toLowerCase();
				if (venues.containsKey(parsedVenueName)) {
					// Venue already exists in system
					Venue persistentVenue = venues.get(parsedVenueName);
					qualifiedVenues.add(new QualifiedVenue(persistentVenue, qualis));
				} else {
					parsedVenue.setCategory(category);
					qualifiedVenues.add(new QualifiedVenue(parsedVenue, qualis));
				}
			} catch (PersistentObjectNotFoundException e) {
				// If there is no Qualis with the given level, throw an
				// exception from the domain.
				logger.log(Level.WARNING,
						"No qualis found with level ",
						level);
				throw new QualisLevelNotRegisteredException(level);
			} catch (MultiplePersistentObjectsFoundException e) {
				// This is a bug. Log and throw a runtime exception.
				logger.log(Level.SEVERE, "Multiple qualis found with the same level: " + level, e);
				throw new EJBException(e);
			}
		}
		Collections.sort(qualifiedVenues);
		return qualifiedVenues;
	}

	@Override
	public void assignQualificationsToVenues(List<QualifiedVenue> qualifiedVenues, int year) {
		
		Map<Venue, Qualification> venueQualifications = new HashMap<Venue, Qualification>();
		for (Qualification q : qualificationDAO.retrieveByYear(year)) {
			venueQualifications.put(q.getVenue(), q);
		}
		
		for (QualifiedVenue qv : qualifiedVenues) {
			Venue venue = qv.getVenue();
			Qualis qualis = qv.getQualis();
			Qualification qualification = null;
			if (venue.isPersistent() && venueQualifications.containsKey(venue)) {
				qualification = venueQualifications.get(venue);
				if (qualification != null)
					qualification.setQualis(qualis);
				else
					qualification = new Qualification(year, qualis, venue);
			}
			else {
				//TODO: verify if a persistent venue should be saved, even if it wasn't altered here
				venueDAO.save(venue);				
				qualification = new Qualification(year, qualis, venue);
			}
			qualificationDAO.save(qualification);
			System.out.println("Saved venue: " + venue.getName());
		}
		
		venuesImportEvent.fire(new VenuesImportEvent()); 
	}

}
