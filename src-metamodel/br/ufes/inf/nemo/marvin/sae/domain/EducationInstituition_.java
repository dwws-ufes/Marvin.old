package br.ufes.inf.nemo.marvin.sae.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-10T15:26:59.345-0300")
@StaticMetamodel(EducationInstituition.class)
public class EducationInstituition_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<EducationInstituition, String> state;
	public static volatile SingularAttribute<EducationInstituition, String> country;
	public static volatile SingularAttribute<EducationInstituition, String> name;
	public static volatile SingularAttribute<EducationInstituition, String> institutionAcronym;
}
