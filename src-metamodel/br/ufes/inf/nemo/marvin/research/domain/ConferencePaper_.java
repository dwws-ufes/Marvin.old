package br.ufes.inf.nemo.marvin.research.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-30T21:17:03.745-0300")
@StaticMetamodel(ConferencePaper.class)
public class ConferencePaper_ extends Publication_ {
	public static volatile SingularAttribute<ConferencePaper, String> bookTitle;
	public static volatile SingularAttribute<ConferencePaper, String> eventName;
	public static volatile SingularAttribute<ConferencePaper, Boolean> fullPaper;
}
