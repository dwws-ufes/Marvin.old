package br.ufes.inf.nemo.marvin.research.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-14T22:24:00.157-0300")
@StaticMetamodel(Requirement.class)
public class Requirement_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Requirement, String> name;
	public static volatile SingularAttribute<Requirement, Date> startDate;
	public static volatile SingularAttribute<Requirement, Date> endDate;
	public static volatile SingularAttribute<Requirement, Long> totalScore;
	public static volatile SingularAttribute<Requirement, Long> journalScore;
}
