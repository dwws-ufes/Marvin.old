# Marvin

A Web-based Information System that aggregates useful tools for managing teaching and research tasks in an university.

Many students from the [Department of Informatics](http://informatica.ufes.br) of the [Federal University of Espírito Santo](http://www.internacional.ufes.br/en) develop tools as part of their undergraduate final project. Marvin is an attempt to integrate these tools in a way they can be actually used by people.


## How to deploy

1. Install [Eclipse Neon (version 4.6.x)](http://www.eclipse.org/); 

2. Install [WildFly 10](http://wildfly.org) and create a Server configuration within Eclipse;

3. Install [MySQL](http://www.mysql.com/products/community/) and create a schema called `marvin` and a user called `marvin` with password `marvin` and full access to the homonymous database;

4. Configure [the MySQL JDBC driver](http://dev.mysql.com/downloads/connector/j/) in WildFly;

5. Configure the datasource in WildFly's `standalone.xml` file:

```XML
<datasource jta="true" jndi-name="java:jboss/datasources/Marvin" pool-name="MarvinPool" enabled="true" use-java-context="true">
    <connection-url>jdbc:mysql://localhost:3306/marvin</connection-url>
    <driver>mysql</driver>
    <security>
        <user-name>marvin</user-name>
        <password>marvin</password>
    </security>
</datasource>
``` 

6. Configure the security domain in WildFly's `standalone.xml` file:

```XML
<security-domain name="marvin">
    <authentication>
        <login-module code="Database" flag="required">
            <module-option name="dsJndiName" value="java:jboss/datasources/Marvin"/>
            <module-option name="principalsQuery" value="select password from Academic where email=?"/>
            <module-option name="rolesQuery" value="select r.name, 'Roles' from Role r inner join Academic_Role ar on r.id = ar.roles_id inner join Academic a on ar.Academic_id = a.id where email=?"/>
            <module-option name="hashAlgorithm" value="MD5"/>
            <module-option name="hashEncoding" value="base64"/>
            <module-option name="hashUserPassword" value="true"/>
        </login-module>
    </authentication>
</security-domain>
```

Note: if you need detailed instructions on how to set up Eclipse, WildFly and MySQL, please refer to this [tutorial at JButler's wiki](https://github.com/dwws-ufes/jbutler/wiki/Tutorial%3A-a-Java-EE-Web-Profile-application-with-JButler%2C-part-1).
