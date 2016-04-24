package br.ufes.inf.nemo.marvin.sae.core.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.sae.core.domain.CursoRealizado;
import br.ufes.inf.nemo.marvin.sae.core.domain.Egresso;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface CursoRealizadoDAO extends BaseDAO<CursoRealizado>{

	List<CursoRealizado> retrieveMyCursos(Egresso egresso);

}
