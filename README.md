# Marvin

A Web-based Information System that aggregates useful tools for managing teaching and research tasks in an university.

Many students from the [Department of Informatics](http://informatica.ufes.br) of the [Federal University of Espírito Santo](http://www.internacional.ufes.br/en) develop tools as part of their undergraduate final project. Marvin is an attempt to integrate these tools in a way they can be actually used by people.


## How to deploy

Instructions on how to deploy from scratch are listed below. If you need detailed instructions on how to set up Eclipse, WildFly and MySQL, please refer to this [tutorial at JButler's wiki](https://github.com/dwws-ufes/jbutler/wiki/Tutorial%3A-a-Java-EE-Web-Profile-application-with-JButler%2C-part-1).

1. Install [Eclipse Neon (version 4.6)](http://www.eclipse.org/);

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

7. In Eclipse, use _File_ > _Import_ > _Git_ > _Projects from Git_ to import the Eclipse project existing in this repository;

8. You might have to adjust the server settings in the imported project: right-click the _Marvin_ project and select _Properties_. In the _Server_ section, select the _WildFly 10.x_ server. In the _Targeted Runtimes_ section, select the _WildFly 10.x Runtime_;

9. At the end, it is possible that Maven is not able to retrieve PrimeFaces dependencies because of an exception like: `sun.security.validator.ValidatorException: PKIX path building failed`. If that happens, read below.


## Maven, PrimeFaces and SSL Certificates

It seems that recently Maven decided to check certificates on repositories that use SSL (HTTPS). PrimeFaces' repository is the case, so if you don't have the required PrimeFaces JARs in your local repository and run `mvn dependency:resolve` (or any other Maven command that triggers dependency resolution) you might eventually see the message `sun.security.validator.ValidatorException: PKIX path building failed`. In Eclipse, the error messages will just say that some JAR resource is unavailable, without further explanation.

That being said, one possible solution is to tell Maven to stop worrying about security. It doesn't seem like a good idea to me, but if you want to try you can check [this post at StackOverflow](http://stackoverflow.com/questions/21252800/how-to-tell-maven-to-disregard-ssl-errors-and-trusting-all-certs).

The problem is that the other option is to add PrimeFaces' certificate to your JVM, which is more complicated than it should be. Here's what you have to do:

1. Save the certificate as a file in your computer. In [this post at StackOverflow](https://superuser.com/questions/97201/how-to-save-a-remote-server-ssl-certificate-locally-as-a-file), there's [an answer](https://superuser.com/a/641396/672373) that tells you how to do it on a Linux/MacOS terminal and [another answer](https://superuser.com/a/97203/672373) that tells your how to do it in Firefox. I followed the first option:

```
openssl s_client -showcerts -connect repository.primefaces.org:443 </dev/null 2>/dev/null | openssl x509 -outform PEM > ~/Downloads/primefaces.pem
```

2. Obtain the location of the `cacerts` file of the default Java installation in your system. This file is where you add certificates you trust in the next step. Again, there's an [answer at StackOverflow](http://stackoverflow.com/a/11937940/361343) that explains how to do this in Linux and MacOS. On Windows, check `C:\Program Files\Java` directory for Java installations and check which one is being used by default (you can use `java -version` at the Command Prompt);

3. [MAC-OS] Once you know where `cacerts` is, you use the `keytool` program that comes with Java to add the certificate you downloaded from the PrimeFaces repository to the list of truted certificates. [This blog post](https://blog.alwold.com/2011/06/30/how-to-trust-a-certificate-in-java-on-mac-os-x/) (finally, not StackOverflow this time!) explains how to do it in MacOS, but it should be similar in other Operating Systems. Or you can just search for a solution in your OS... Here's what I did:

```
cd /Library/Java/JavaVirtualMachines/jdk1.8.0.jdk/Contents/Home/jre/lib/security
sudo cp cacerts cacerts.orig
sudo keytool -importcert -file ~/Downloads/primefaces.pem -keystore cacerts
```
4. [WINDOWS] On Windows, the `cacerts` file is in `jre\lib\security`. First, you need put the primefaces certificate file, that you downloaded from firefox, in `jre\lib\security` folder. Second, run the Command Prompt (cmd) in administrator mode and import the certificate via the following command:

```
cd C:\Program Files\Java\jre[Version]\lib\security
keytool -keystore cacerts -importcert -alias primefaces.org -file www.primefaces.org.crt
keytool -list -keystore cacerts -- Run the following command to ensure the CA certificate has been successfully imported
```

When you run the `keytool` (last command above), it will ask you for the keystore password and you should enter the default password that comes with the JVM: `changeit`. Then it will print a lot of information and ask if you want to trust the certificate. You should answer `yes`.

Now, try `mvn dependency:resolve` again and it should work this time.
