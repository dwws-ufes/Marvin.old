package br.ufes.inf.nemo.marvin.research.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import br.ufes.inf.nemo.jbutler.ResourceUtil;
import br.ufes.inf.nemo.marvin.research.exceptions.LattesParseException;

public class LattesParser {
	/** The logger. */
	private static final Logger logger = Logger.getLogger(LattesParser.class.getCanonicalName());

	/** Name of the configuration file with all the settings for this script. */
	private static final String CONFIG_FILE_NAME = "lattes-parser.properties";

	/** Properties object that holds all the configuration. */
	private static Properties CONFIG;
	
	/** Contents of the XML file that holds the CV information. */
	private StringBuilder xmlContents = new StringBuilder();

	/** Set of Lattes publications read from the CV. */
	private SortedSet<LattesProduction> entries = new TreeSet<>();
	
	/** The curriculum ID. */
	private Long lattesId;
	
	/** The researcher's name. */
	private String researcherName;

	/** Constructor. */
	public LattesParser(InputStream inputStream) throws LattesParseException {
		try {
			// Lazily loads the configurations of the Lattes parser.
			if (CONFIG == null) {
				logger.log(Level.INFO, "Loading Lattes parser configuration from file: {0}", CONFIG_FILE_NAME);
				CONFIG = new Properties();
				String parserConfigFileName = getClass().getPackage().getName().replaceAll("\\.", "/") + "/" + CONFIG_FILE_NAME;
				File parserConfigFile = ResourceUtil.getResourceAsFile(parserConfigFileName);
				logger.log(Level.FINE, "Lattes parser configuration file located at: {0}", parserConfigFile.getAbsolutePath());
				CONFIG.load(new FileInputStream(parserConfigFile));
			}
	
			// Reads the contents of the input stream (i.e., the XML file) into a string buffer ready for Jsoup.
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, CONFIG.getProperty("encoding")))) {
				String line = reader.readLine();
				while (line != null) {
					xmlContents.append(line).append('\n');
					line = reader.readLine();
				}
			}
			logger.log(Level.INFO, "Read {0} characters from the given CV's input stream.", xmlContents.length());
		}
		catch (Exception e) {
			// In case there's any exception that prevents us from reading the CV, wraps it in an exception the controller can handle.
			logger.log(Level.SEVERE, "Exception while trying to read the contents of the XML file that holds the Lattes CV.", e);
			throw new LattesParseException(e);
		}
	}

	/** Getter for lattesId. */
	public Long getLattesId() {
		return lattesId;
	}

	/** Getter for researcherName. */
	public String getResearcherName() {
		return researcherName;
	}

	/**
	 * TODO: document this method.
	 * 
	 * @param doc
	 */
	public void parse() throws LattesParseException {
		// Parses the XML file with the Lattes CV using Jsoup.
		Document doc = Jsoup.parse(xmlContents.toString(), "", Parser.xmlParser());

		// Extracts the ID of the CV and the name of the researcher.
		Element curriculum = doc.select(CONFIG.getProperty("jsoupSelectorCurriculum")).first();
		lattesId = Long.parseLong(curriculum.attr(CONFIG.getProperty("curriculumId")));
		Element generalData = doc.select(CONFIG.getProperty("jsoupSelectorGeneralData")).first();
		researcherName = generalData.attr(CONFIG.getProperty("generalDataName"));
		logger.log(Level.FINE, "Jsoup successfully parsed the CV # {0}, of {1}", new Object[] {lattesId, researcherName});

		// Navigates to the nodes that contains the production (supports multiple, although we expect a single one).
		Elements bibliographyLists = doc.select(CONFIG.getProperty("jsoupSelectorBibliographic"));

		// Goes through all the bibliography.
		for (Element elem : bibliographyLists) {
			// Extracts conference, journal, books & chapters, magazine and others.
			extractEntries(elem, "Events");
			extractEntries(elem, "Journals");
			extractEntries(elem, "Books");
			extractEntries(elem, "Chapters");
			extractEntries(elem, "Magazines");
			extractEntries(elem, "Others");
		}
	}

	/**
	 * TODO: document this method.
	 * 
	 * @param researcher
	 * @param element
	 * @param name
	 */
	private void extractEntries(Element element, String name) {
		// Gets the structural information from the configuration.
		String selector = CONFIG.getProperty("jsoupSelectorBibliographic" + name);
		String selectorGeneral = CONFIG.getProperty("jsoupSelectorBibliographic" + name + "General");
		String selectorDetails = CONFIG.getProperty("jsoupSelectorBibliographic" + name + "Details");
		String selectorAuthors = CONFIG.getProperty("jsoupSelectorBibliographic" + name + "Authors");
		String baseType = CONFIG.getProperty("bibliographic" + name + "BaseType");
		String attrType = CONFIG.getProperty("bibliographic" + name + "Type");
		String attrYear = CONFIG.getProperty("bibliographic" + name + "Year");
		String attrTitle = CONFIG.getProperty("bibliographic" + name + "Title");
		String[] attrVenue = CONFIG.getProperty("bibliographic" + name + "Venue").split("\\s*\\|\\s*");
		String attrAuthors = CONFIG.getProperty("bibliographic" + name + "Authors");

		// Goes through all entries.
		Elements elems = element.select(selector);
		for (Element elem : elems) {
			Element general = elem.select(selectorGeneral).first();

			// Extracts the information needed.
			int year = parseYear(general.attr(attrYear));
			String type = baseType + " / " + general.attr(attrType);
			String title = general.attr(attrTitle);

			// Venue can be split into more than one attribute.
			StringBuilder venues = new StringBuilder();
			for (String attr : attrVenue)
				venues.append(elem.select(selectorDetails).first().attr(attr)).append(" / ");
			venues.deleteCharAt(venues.length() - 1);
			venues.deleteCharAt(venues.length() - 1);
			venues.deleteCharAt(venues.length() - 1);

			// Authors can be multiple.
			StringBuilder authors = new StringBuilder();
			for (Element author : elem.select(selectorAuthors))
				authors.append(author.attr(attrAuthors)).append(", ");
			authors.deleteCharAt(authors.length() - 1);
			authors.deleteCharAt(authors.length() - 1);

			// Creates a production entry and adds to the set.
			entries.add(new LattesProduction(type, year, title, venues.toString(), authors.toString()));
		}
	}

	/**
	 * TODO: document this method.
	 * 
	 * @param yearData
	 * @return
	 */
	private int parseYear(String yearData) {
		if (yearData.matches("\\d{4}")) return Integer.parseInt(yearData);
		String[] data = yearData.split(" ");
		for (int i = 0; i < data.length; i++)
			if (data[i].matches("\\d{4}")) return Integer.parseInt(data[i]);
		return 0;
	}
}

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
