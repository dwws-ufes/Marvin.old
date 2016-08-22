package br.ufes.inf.nemo.marvin.research.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-08-22T13:54:34.660-0300")
@StaticMetamodel(JournalPaper.class)
public class JournalPaper_ extends Publication_ {
	public static volatile SingularAttribute<JournalPaper, String> journal;
	public static volatile SingularAttribute<JournalPaper, String> volume;
	public static volatile SingularAttribute<JournalPaper, String> issn;
	public static volatile SingularAttribute<JournalPaper, String> number;
}
