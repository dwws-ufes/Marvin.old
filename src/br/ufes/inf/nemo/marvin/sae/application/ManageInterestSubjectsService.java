package br.ufes.inf.nemo.marvin.sae.application;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.marvin.sae.domain.InterestSubject;

/**
 * TODO: document this type.
 *
 * @author Gabriel Martins Miranda (gabrielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Local
public interface ManageInterestSubjectsService extends CrudService<InterestSubject> {
	public Map<String, InterestSubject> retriveMap();
}
