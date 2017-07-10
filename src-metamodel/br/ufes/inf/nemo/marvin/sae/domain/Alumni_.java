package br.ufes.inf.nemo.marvin.sae.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import br.ufes.inf.nemo.marvin.core.domain.Academic;
import br.ufes.inf.nemo.marvin.core.domain.Course;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-10T14:50:00.982-0300")
@StaticMetamodel(Alumni.class)
public class Alumni_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Alumni, Course> course;
	public static volatile SingularAttribute<Alumni, Academic> academic;
	public static volatile SingularAttribute<Alumni, Education> education;
	public static volatile SingularAttribute<Alumni, AlumniHistory> alumniHistory;
	public static volatile SingularAttribute<Alumni, Date> creationDate;
	public static volatile SingularAttribute<Alumni, Date> lastUpdateDate;
}
