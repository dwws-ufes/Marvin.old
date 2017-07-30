package br.ufes.inf.nemo.marvin.sae.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-30T00:13:34.424-0300")
@StaticMetamodel(Seminar.class)
public class Seminar_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Seminar, Date> date;
	public static volatile SingularAttribute<Seminar, String> speakerName;
	public static volatile SingularAttribute<Seminar, String> location;
	public static volatile SingularAttribute<Seminar, String> title;
	public static volatile SingularAttribute<Seminar, Boolean> confirmed;
	public static volatile SingularAttribute<Seminar, InterestSubject> interestSubject;
	public static volatile SingularAttribute<Seminar, Date> creationDate;
	public static volatile SingularAttribute<Seminar, Date> lastUpdateDate;
}
