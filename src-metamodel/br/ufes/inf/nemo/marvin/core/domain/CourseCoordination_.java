package br.ufes.inf.nemo.marvin.core.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-06-02T15:20:49.663-0300")
@StaticMetamodel(CourseCoordination.class)
public class CourseCoordination_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<CourseCoordination, Date> startDate;
	public static volatile SingularAttribute<CourseCoordination, Date> endDate;
	public static volatile SingularAttribute<CourseCoordination, Course> course;
	public static volatile SingularAttribute<CourseCoordination, Academic> academic;
}
