package br.ufes.inf.nemo.marvin.research.application;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufes.inf.nemo.marvin.research.domain.Venue;
import br.ufes.inf.nemo.marvin.research.domain.VenueCategory;
import br.ufes.inf.nemo.marvin.research.exceptions.CSVParseException;

public class CSVParser {
	/** The logger. */
	private static final Logger logger = Logger.getLogger(CSVParser.class.getCanonicalName());
	
	/** CSV Contents */
	private Map<Venue,String> venuesMap;
		
	public Map<Venue,String> getVenuesMap() {
		return venuesMap;
	}
	
	public CSVParser(InputStream csvFile, VenueCategory category ) throws CSVParseException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile))) {
        	String csvSplitBy = ";";
			String line = reader.readLine();
			if (line != null) {
				//Discards the header first
				line = reader.readLine();
				String[] values = line.split(csvSplitBy);
				venuesMap = new HashMap<Venue,String>();
				while (line != null) {
					
					values = line.split(csvSplitBy);
					if (values.length == 4 && category.equals(VenueCategory.CONFERENCE)) {
						Venue v = new Venue(values[1].trim());
						v.setAcronym(values[0].trim());
						venuesMap.put(v, values[3].trim());
						line = reader.readLine();
					}
					else if(values.length == 3 && category.equals(VenueCategory.JOURNAL)){
							Venue v = new Venue(values[1].trim());
							v.setIssn(values[0].trim());
							venuesMap.put(v, values[2].trim());
							line = reader.readLine();	
						
					} else throw new Exception();
						
				}
			} else throw new Exception();

        } catch (Exception e) {
			// In case there's any exception that prevents us from reading the CSV file, wraps it in an exception the controller can
			// handle.
			logger.log(Level.SEVERE, "Exception while trying to read the contents of the CSV file that holds the Venues' qualifications.", e);
			throw new CSVParseException(e);
        }
	}

}
