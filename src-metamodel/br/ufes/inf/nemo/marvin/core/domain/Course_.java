package br.ufes.inf.nemo.marvin.core.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import br.ufes.inf.nemo.marvin.core.domain.Course.AcademicLevel;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-03T15:23:17.922-0300")
@StaticMetamodel(Course.class)
public class Course_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Course, String> name;
	public static volatile SingularAttribute<Course, Long> code;
	public static volatile SingularAttribute<Course, AcademicLevel> academicLevel;
	public static volatile SingularAttribute<Course, Date> creationDate;
	public static volatile SingularAttribute<Course, Date> lastUpdateDate;
}
