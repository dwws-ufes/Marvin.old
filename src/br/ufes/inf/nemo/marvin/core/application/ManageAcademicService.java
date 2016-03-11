package br.ufes.inf.nemo.marvin.core.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;

@Local
public interface ManageAcademicService extends CrudService<Academic>{

}
