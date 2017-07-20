package br.ufes.inf.nemo.marvin.sae.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import br.ufes.inf.nemo.marvin.core.domain.Course;
import br.ufes.inf.nemo.marvin.sae.domain.Statement.StatementStatus;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-20T12:35:50.876-0300")
@StaticMetamodel(Statement.class)
public class Statement_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Statement, Date> sendDate;
	public static volatile SingularAttribute<Statement, String> content;
	public static volatile SingularAttribute<Statement, StatementStatus> statementStatus;
	public static volatile SingularAttribute<Statement, Course> course;
}
