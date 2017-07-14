package br.ufes.inf.nemo.marvin.sae.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory;
import br.ufes.inf.nemo.marvin.sae.domain.Education;

/**
 * TODO: document this type.
 *
 * @author Gabriel Martins Miranda (gabrielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Local
public interface ManageEducationsService extends CrudService<Education> {

}
