package br.ufes.inf.nemo.marvin.research.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-09-13T10:38:38.421-0300")
@StaticMetamodel(BibGenConfiguration.class)
public class BibGenConfiguration_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<BibGenConfiguration, String> name;
	public static volatile SetAttribute<BibGenConfiguration, BibGenResearcher> researchers;
	public static volatile SingularAttribute<BibGenConfiguration, Integer> startYear;
	public static volatile SingularAttribute<BibGenConfiguration, Integer> endYear;
}
