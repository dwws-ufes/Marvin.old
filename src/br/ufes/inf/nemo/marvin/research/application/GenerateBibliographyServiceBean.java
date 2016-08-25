package br.ufes.inf.nemo.marvin.research.application;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.research.domain.BibGenConfiguration;
import br.ufes.inf.nemo.marvin.research.domain.BibGenResearcher;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Stateless
@RolesAllowed({ "SysAdmin", "Professor" })
public class GenerateBibliographyServiceBean implements GenerateBibliographyService {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(GenerateBibliographyServiceBean.class.getCanonicalName());

	/** @see br.ufes.inf.nemo.marvin.research.application.GenerateBibliographyService#generateBibliography(br.ufes.inf.nemo.marvin.research.domain.BibGenConfiguration) */
	@Override
	public StringBuilder generateBibliography(BibGenConfiguration config) {
		StringBuilder builder = new StringBuilder();

		// Goes through all the researchers.
		for (BibGenResearcher researcherCfg : config.getResearchers()) {
			// FIXME: implement this.
			Academic researcher = researcherCfg.getResearcher();
			logger.log(Level.INFO, "Processing the bibliography of \"{0}\" ({1}), start: {2}, end: {3}", new Object[] { researcher.getName(), researcher.getEmail(), researcherCfg.getStartYear(), researcherCfg.getEndYear() });
			builder.append(researcherCfg.getResearcher().getName() + " from " + researcherCfg.getStartYear() + " to " + researcherCfg.getEndYear() + "\n");
		}

		// Returns the string builder with the entire bibliography.
		return builder;
	}
}
