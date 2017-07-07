package br.ufes.inf.nemo.marvin.sae.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import br.ufes.inf.nemo.marvin.sae.domain.Education.EducationType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-07T15:19:10.603-0300")
@StaticMetamodel(Education.class)
public class Education_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Education, String> state;
	public static volatile SingularAttribute<Education, String> country;
	public static volatile SingularAttribute<Education, String> educationalInstitution;
	public static volatile SingularAttribute<Education, Integer> year;
	public static volatile SingularAttribute<Education, EducationType> educationType;
}
