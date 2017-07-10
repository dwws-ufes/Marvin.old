package br.ufes.inf.nemo.marvin.sae.application;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.PersistentObjectConverterFromId;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.domain.AcademicRole;
import br.ufes.inf.nemo.marvin.core.domain.Role;
import br.ufes.inf.nemo.marvin.sae.domain.Alumni;

/**
 * TODO: document this type.
 *
 * @author Gabriel Martins Miranda (gabrielmartinsmiranda@gmail.com)
 * @version 1.0
 */
@Local
public interface ManageAlumnisService extends CrudService<Alumni> {

}
