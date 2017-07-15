package br.ufes.inf.nemo.marvin.research.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.research.domain.Score;

@Local
public interface CreateNewScoreSystemService extends Serializable {
	List<Score> constructScoresFromQualis();
	void createNewScoreSystem(List<Score> scores);
}
