package br.ufes.inf.nemo.marvin.sae.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-30T00:13:34.413-0300")
@StaticMetamodel(InterestSubject.class)
public class InterestSubject_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<InterestSubject, String> name;
	public static volatile SingularAttribute<InterestSubject, Date> creationDate;
	public static volatile SingularAttribute<InterestSubject, Date> lastUpdateDate;
}