package br.ufes.inf.nemo.marvin.core.domain;

import java.util.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.ufes.inf.nemo.marvin.people.domain.Person_;
import br.ufes.inf.nemo.marvin.people.domain.Telephone;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@StaticMetamodel(Academic.class)
public class Academic_ extends Person_ {
	public static volatile SingularAttribute<Academic, String> shortName;
	public static volatile SingularAttribute<Academic, String> email;
	public static volatile SingularAttribute<Academic, String> password;
	public static volatile SetAttribute<Academic, Telephone> telephones;
	public static volatile SingularAttribute<Academic, Date> lastUpdateDate;
	public static volatile SingularAttribute<Academic, Date> lastLoginDate;
}
