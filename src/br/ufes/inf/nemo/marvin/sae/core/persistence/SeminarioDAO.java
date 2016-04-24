package br.ufes.inf.nemo.marvin.sae.core.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.sae.core.domain.Seminario;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface SeminarioDAO extends BaseDAO<Seminario>{

}
