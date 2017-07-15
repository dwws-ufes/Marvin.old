package br.ufes.inf.nemo.marvin.research.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-14T22:24:00.153-0300")
@StaticMetamodel(Qualification.class)
public class Qualification_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Qualification, Qualis> qualis;
	public static volatile SingularAttribute<Qualification, Venue> venue;
	public static volatile SingularAttribute<Qualification, Integer> year;
}
