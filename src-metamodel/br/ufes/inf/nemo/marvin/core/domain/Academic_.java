package br.ufes.inf.nemo.marvin.core.domain;

import br.ufes.inf.nemo.marvin.people.domain.Person_;
import br.ufes.inf.nemo.marvin.people.domain.Telephone;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-06-29T15:32:39.826-0300")
@StaticMetamodel(Academic.class)
public class Academic_ extends Person_ {
	public static volatile SingularAttribute<Academic, String> shortName;
	public static volatile SingularAttribute<Academic, String> email;
	public static volatile SingularAttribute<Academic, String> password;
	public static volatile SingularAttribute<Academic, Long> lattesId;
	public static volatile SingularAttribute<Academic, String> passwordCode;
	public static volatile SetAttribute<Academic, Telephone> telephones;
	public static volatile SetAttribute<Academic, Role> roles;
	public static volatile SingularAttribute<Academic, Date> creationDate;
	public static volatile SingularAttribute<Academic, Date> lastUpdateDate;
	public static volatile SingularAttribute<Academic, Date> lastLoginDate;
	public static volatile SetAttribute<Academic, AcademicRole> academicRoles;
}
