package br.ufes.inf.nemo.marvin.sae.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import br.ufes.inf.nemo.marvin.core.domain.CourseAttendance;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-30T21:17:03.780-0300")
@StaticMetamodel(Alumni.class)
public class Alumni_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Alumni, CourseAttendance> courseAttendance;
	public static volatile SingularAttribute<Alumni, Date> creationDate;
}
