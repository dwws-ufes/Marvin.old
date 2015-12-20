package br.ufes.inf.nemo.marvin.core.domain;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;

/**
 * Meta-model for the MarvinConfiguration domain class, which allows DAOs to perform programmatic queries involving this
 * class using JPA2's Criteria API.
 * 
 * @author Vitor E. Silva Souza (vitorsouza@gmail.com)
 * @see br.org.feees.sigme.core.domain.Attendance
 */
@StaticMetamodel(MarvinConfiguration.class)
public class MarvinConfiguration_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<MarvinConfiguration, Date> creationDate;
	public static volatile SingularAttribute<MarvinConfiguration, String> institutionAcronym;
}
