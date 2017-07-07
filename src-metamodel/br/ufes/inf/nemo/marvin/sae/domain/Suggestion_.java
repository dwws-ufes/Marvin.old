package br.ufes.inf.nemo.marvin.sae.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import br.ufes.inf.nemo.marvin.core.domain.Course;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-07T15:09:36.809-0300")
@StaticMetamodel(Suggestion.class)
public class Suggestion_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Suggestion, Course> course;
	public static volatile SingularAttribute<Suggestion, Date> sendDate;
	public static volatile SingularAttribute<Suggestion, String> content;
	public static volatile SingularAttribute<Suggestion, String> answer;
}
