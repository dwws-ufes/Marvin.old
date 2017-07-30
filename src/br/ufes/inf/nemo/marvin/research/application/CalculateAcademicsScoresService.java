package br.ufes.inf.nemo.marvin.research.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.research.domain.AcademicScore;
import br.ufes.inf.nemo.marvin.research.exceptions.ScoreSystemNotRegisteredException;

@Local
public interface CalculateAcademicsScoresService extends Serializable {

	List<AcademicScore> calculateAcademicsScore(List<Academic> academics, int startYear, int endYear) throws ScoreSystemNotRegisteredException;

}
