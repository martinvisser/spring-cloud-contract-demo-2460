# Description
Repo to demonstrate the issue with Spring Cloud Contract plugin when using version `4.3.3`.

Updated to the latest Spring Cloud Contract `4.3.3`, and now I can not use Spring Cloud Contract plugin during the first maven build. Subsequent builds do work. No issues when downgrading back to `4.3.1` for first and subsequent runs.

Initially the build starts fine downloading the needed dependencies for the plugin, but without any additional output the build fails. The subsequent run does download the additional dependencies.

Reproducible by running `mvn clean test` twice. The first run fails, the second run succeeds. \
Removing the downloaded jars in `$HOME/.m2/repository/org/springframework/cloud/spring-cloud-contract-*` and running `mvn clean test` again reproduces the issue.

The first mvn clean test run fails with the following stack trace:
```
class org.apache.http.impl.client.BasicAuthCache cannot be cast to class org.apache.http.client.AuthCache (org.apache.http.impl.client.BasicAuthCache is in unnamed module of loader org.codehaus.plexus.classworlds.realm.ClassRealm @34c45dca; org.apache.http.client.AuthCache is in unnamed module of loader org.codehaus.plexus.classworlds.realm.ClassRealm @55c57422)
java.lang.ClassCastException: class org.apache.http.impl.client.BasicAuthCache cannot be cast to class org.apache.http.client.AuthCache (org.apache.http.impl.client.BasicAuthCache is in unnamed module of loader org.codehaus.plexus.classworlds.realm.ClassRealm @34c45dca; org.apache.http.client.AuthCache is in unnamed module of loader org.codehaus.plexus.classworlds.realm.ClassRealm @55c57422)
    at org.eclipse.aether.transport.http.HttpTransporter.<init> (HttpTransporter.java:372)
    at org.eclipse.aether.transport.http.HttpTransporterFactory.newInstance (HttpTransporterFactory.java:95)
```
