package br.ufes.inf.nemo.marvin.sae.application;

import java.util.Map;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.marvin.sae.domain.Alumni;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory.DegreeArea;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory.PracticeArea;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory.SalaryRange;
import br.ufes.inf.nemo.marvin.sae.domain.Education.EducationType;

/**
 * TODO: document this type.
 *
 * @author Gabriel Martins Miranda (gabrielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Local
public interface ManageAlumniHistoriesService extends CrudService<AlumniHistory> {
	public Map<String, Alumni> retrieveAlumnis();
}
