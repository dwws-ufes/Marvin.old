package br.ufes.inf.nemo.marvin.research.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

import br.ufes.inf.nemo.marvin.research.domain.ConferencePaper;
import br.ufes.inf.nemo.marvin.research.domain.JournalPaper;
import br.ufes.inf.nemo.marvin.research.domain.Publication;
import br.ufes.inf.nemo.marvin.research.domain.Venue;
import br.ufes.inf.nemo.marvin.research.domain.VenueCategory;
import br.ufes.inf.nemo.marvin.research.persistence.PublicationDAO;
import br.ufes.inf.nemo.marvin.research.persistence.VenueDAO;

@Stateless
public class MatchPublicationsVenuesServiceBean implements MatchPublicationsVenuesService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PublicationDAO publicationDAO;
	
	@EJB
	private VenueDAO venueDAO;

	@Override
	public void matchPublicationsVenues(@Observes VenuesImportEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Matching publications with venues.");
		List<Publication> publications = publicationDAO.retrieveAll();
		List<Venue> venues = venueDAO.retrieveAll();
		
		Map<String, Venue> journals = new HashMap<String, Venue>();
		Map<String, Venue> conferences = new HashMap<String, Venue>();
		
		for (Venue v : venues) {
			if (v.getCategory().equals(VenueCategory.CONFERENCE))
				conferences.put(v.getName().toLowerCase().trim(), v);
			else
				journals.put(v.getName().toLowerCase().trim(), v);
		}
		
		for (Publication publication : publications) {
			if (publication.getVenue() == null) {
				System.out.println("Publication: " + publication.getTitle());
				if (publication instanceof ConferencePaper) {
					ConferencePaper paper = (ConferencePaper) publication;
					String conferenceProceedings = paper.getBookTitle().toLowerCase().trim();
					System.out.println("Publication " + publication.getTitle() + " is conference!" );
					
					String foundConference = conferenceProceedings;
					for (String conferenceName : conferences.keySet()) {
						if (conferenceProceedings.contains(conferenceName)) {
							//Since the conference proceedings never match the actual name of the conference,
							//check whether the proceedings contain the name of the conference.
							foundConference = conferenceName;
							break;
						}
					}
					if (conferences.containsKey(foundConference)) {
						paper.setVenue(conferences.get(foundConference));
						publicationDAO.save(paper);
					}
				}
				else if (publication instanceof JournalPaper) {
					JournalPaper paper = (JournalPaper) publication;
					String journalName = paper.getJournal().toLowerCase().trim();
					System.out.println("Publication " + publication.getTitle() + " is journal!" );
					if (journals.containsKey(journalName)) {
						paper.setVenue(journals.get(journalName));
						publicationDAO.save(paper);
					}				
				}
			}
		}
	}

}
