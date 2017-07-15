package br.ufes.inf.nemo.marvin.sae.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import br.ufes.inf.nemo.marvin.sae.domain.Education.EducationType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-14T17:56:26.040-0300")
@StaticMetamodel(Education.class)
public class Education_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Education, Integer> year;
	public static volatile SingularAttribute<Education, EducationInstituition> educationInstituition;
	public static volatile SingularAttribute<Education, EducationType> educationType;
}
