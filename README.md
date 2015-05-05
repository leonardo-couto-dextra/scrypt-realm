## Scrypt Tomcat Security Realm

Security realm for Tomcat built on top of the JDBCRealm that uses Scrypt key derivation function to protect user's passwords.

It uses <a href="https://github.com/wg/scrypt">https://github.com/wg/scrypt</a> Scrypt java implementation. See the link for more details on Scrypt.

See also <a href="https://tomcat.apache.org/tomcat-7.0-doc/realm-howto.html">https://tomcat.apache.org/tomcat-7.0-doc/realm-howto.html</a> subsection JDBCRealm on how to set up a JDBCRealm. You can use the same configuration for this realm.

Tested on Tomcat 7.X.

##How to use on your project:

The jar is available in our maven repository below

```xml
  <repositories>
    <repository>
      <id>dextra-public-release</id>
      <url>https://dextranet.dextra.com.br/nexus/content/repositories/dextra-public-release/</url>
    </repository>
  </repositories>

  <dependency>
    <groupId>br.com.dextra.security</groupId>
    <artifactId>scrypt-realm</artifactId>
    <version>0.1.3</version>
  </dependency>
```

You will also need the Scrypt jar

```xml
  <dependency>
    <groupId>com.lambdaworks</groupId>
    <artifactId>scrypt</artifactId>
    <version>1.4.0</version>
  </dependency>
```

+ put both jars on $CATALINA_HOME/lib

+ Add the Realm definition on your server.xml, in the example below we are using HSQLDB in memory database.

```xml
  <Realm className="br.com.dextra.security.SCryptRealm"
       driverName="org.hsqldb.jdbcDriver"
       connectionURL="jdbc:hsqldb:mem:."
       userTable="user" userNameCol="email" userCredCol="password"
       userRoleTable="role" roleNameCol="name"
       resourceName="scryptrealm" />
```

+ Update your web.xml file to secure your application

```xml
  <security-role>
    <role-name>AUTH</role-name>
  </security-role>

  <security-constraint>
    <web-resource-collection>
      <web-resource-name>Authenticated Pages</web-resource-name>
      <url-pattern>/*</url-pattern>
    </web-resource-collection>
    <auth-constraint>
      <role-name>AUTH</role-name>
    </auth-constraint>
  </security-constraint>

  <login-config>
    <auth-method>FORM</auth-method>
    <realm-name>scryptrealm</realm-name>
    <form-login-config>
      <form-login-page>/login.html</form-login-page>
      <form-error-page>/error.html</form-error-page>
    </form-login-config>
  </login-config>
```
