package br.ufes.inf.nemo.marvin.sae.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory.DegreeArea;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory.PracticeArea;
import br.ufes.inf.nemo.marvin.sae.domain.AlumniHistory.SalaryRange;
import br.ufes.inf.nemo.marvin.sae.domain.Education.EducationType;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-10T12:01:13.972-0300")
@StaticMetamodel(AlumniHistory.class)
public class AlumniHistory_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<AlumniHistory, Date> sendDate;
	public static volatile SingularAttribute<AlumniHistory, Boolean> livesES;
	public static volatile SingularAttribute<AlumniHistory, PracticeArea> praticeArea;
	public static volatile SingularAttribute<AlumniHistory, DegreeArea> degreeArea;
	public static volatile SingularAttribute<AlumniHistory, SalaryRange> salaryRange;
	public static volatile SingularAttribute<AlumniHistory, EducationType> educationType;
}
