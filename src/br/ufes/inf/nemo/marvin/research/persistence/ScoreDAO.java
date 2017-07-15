package br.ufes.inf.nemo.marvin.research.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.research.domain.Score;
import br.ufes.inf.nemo.marvin.research.domain.ScoreSystem;

@Local
public interface ScoreDAO extends BaseDAO<Score> {
	List<Score> retrieveByScoreSystem(ScoreSystem scoreSystem);

}
