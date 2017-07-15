package br.ufes.inf.nemo.marvin.research.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-14T22:24:00.160-0300")
@StaticMetamodel(ScoreSystem.class)
public class ScoreSystem_ extends PersistentObjectSupport_ {
	public static volatile SetAttribute<ScoreSystem, Score> scores;
	public static volatile SingularAttribute<ScoreSystem, Date> startDate;
	public static volatile SingularAttribute<ScoreSystem, Date> endDate;
}
