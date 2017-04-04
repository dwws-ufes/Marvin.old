package br.ufes.inf.nemo.marvin.core.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import br.ufes.inf.nemo.marvin.core.domain.CourseAttendance.CourseSituation;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-04T09:17:11.296-0300")
@StaticMetamodel(CourseAttendance.class)
public class CourseAttendance_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<CourseAttendance, Date> startDate;
	public static volatile SingularAttribute<CourseAttendance, Date> endDate;
	public static volatile SingularAttribute<CourseAttendance, CourseSituation> courseSituation;
}
