package br.ufes.inf.nemo.marvin.research.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-08-18T21:43:45.157-0300")
@StaticMetamodel(Article.class)
public class Article_ extends Publication_ {
	public static volatile SingularAttribute<Article, String> journal;
	public static volatile SingularAttribute<Article, String> volume;
	public static volatile SingularAttribute<Article, String> issn;
}
