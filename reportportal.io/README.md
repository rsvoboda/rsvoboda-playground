# ReportPortal.io - test results management tool

ReportPortal.io is hosted on http://reportportal.io/ and source code is available on GitHub https://github.com/reportportal.

## Docker-compose init
Using [docker-compose.yml](docker-compose.yml) and initial steps from http://reportportal.io/download.

I didn't use `-d` in suggested docker-compose command, just `docker-compose -p reportportal up --force-recreate`.
You see all the logs this way and you can shutdown it just by pressing `Ctrl+C`

If you keep the `-d` you may appreciate `docker-compose -p reportportal down` command.

## Maven project approach
I used https://github.com/jboss-eap-qe/vdx-wildfly-testsuite for experiments.

Changes:
```bash
$ git status
On branch master
Your branch is up to date with 'origin/master'.
 
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)
 
        modified:   pom.xml
 
Untracked files:
  (use "git add <file>..." to include in what will be committed)
 
        src/test/resources/reportportal.properties
```

```diff
$ git diff
diff --git a/pom.xml b/pom.xml
index 098fc43..b32ec17 100644
--- a/pom.xml
+++ b/pom.xml
@@ -52,6 +52,14 @@
             <name>JBoss Nexus Public Repository</name>
             <url>http://repository.jboss.org/nexus/content/groups/public/</url>
         </repository>
+        <repository>
+            <snapshots>
+                <enabled>false</enabled>
+            </snapshots>
+            <id>bintray-epam-reportportal</id>
+            <name>bintray</name>
+            <url>http://dl.bintray.com/epam/reportportal</url>
+        </repository>
     </repositories>
     <dependencies>
         <dependency>
@@ -66,6 +74,11 @@
             <version>${version.junit.vintage}</version>
             <scope>test</scope>
         </dependency>
+        <dependency>
+            <groupId>com.epam.reportportal</groupId>
+            <artifactId>agent-java-junit</artifactId>
+            <version>2.6.1</version>
+        </dependency>
         <!-- creaper dependencies - used for bad xml snippets injection to xml configuration of WF10 server -->
         <dependency>
             <groupId>org.wildfly.extras.creaper</groupId>
@@ -108,6 +121,12 @@
                 <version>2.19.1</version>
                 <configuration>
                     <argLine>${user.country.and.language}</argLine>
+                    <properties>
+                        <property>
+                            <name>listener</name>
+                            <value>com.epam.reportportal.junit.ReportPortalListener</value>
+                        </property>
+                    </properties>
                     <systemPropertyVariables>
                         <user.country.and.language>${user.country.and.language}</user.country.and.language>
                     </systemPropertyVariables>
```

Content of src/test/resources/reportportal.properties can be fount in http://localhost:8080/ui/#user-profile, section CONFIGURATION EXAMPLES.

My reportportal.properties looks like this:
```
rp.endpoint = http://localhost:8080
rp.uuid = 0b6f3f20-aa6e-48d8-ade1-06a3707a8e40
rp.launch = default_TEST_EXAMPLE
rp.project = default_personal
```
When running the TS I can confirm that ReportPortal is contacted, but no valid data are imported there.

Investigation of logs revealed unpleasant errors:
```
api_1            | com.epam.ta.reportportal.exception.ReportPortalException: Test Item 'null' not found. Did you use correct Test Item ID?
api_1            | 	at com.epam.ta.reportportal.commons.validation.ErrorTypeBasedRuleValidator.verify(ErrorTypeBasedRuleValidator.java:37) ~[commons-rules-4.0.1.jar!/:na]
api_1            | 	at com.epam.ta.reportportal.core.item.FinishTestItemHandlerImpl.verifyTestItem(FinishTestItemHandlerImpl.java:177) ~[classes!/:na]
api_1            | 	at com.epam.ta.reportportal.core.item.FinishTestItemHandlerImpl.finishTestItem(FinishTestItemHandlerImpl.java:114) ~[classes!/:na]
api_1            | 	at com.epam.ta.reportportal.ws.controller.impl.TestItemController.finishTestItem(TestItemController.java:120) ~[classes!/:na]
api_1            | 	at com.epam.ta.reportportal.ws.controller.impl.TestItemController$$FastClassBySpringCGLIB$$611b338.invoke(<generated>) ~[classes!/:na]
api_1            | 	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204) ~[spring-core-4.3.13.RELEASE.jar!/:4.3.13.RELEASE]
api_1            | 	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:738) ~[spring-aop-4.3.13.RELEASE.jar!/:4.3.13.RELEASE]
```

I tried to use custom build from master of agent-java-junit too.

## Direct import approach
Launches http://localhost:8080/ui/#default_personal/launches/all allow to import data to ReportPortal.
You must have all the junit TEST-*.xml files packed in zip to be able to upload them.
You can drag and drop .zip file under 32 MB to upload it or add it from invoked file browser.

I'm more curious about ways suitable for automation, so I looked at http://localhost:8080/ui/#api for available options.
REST API `launch/import` is the way to go.

I first tried things like:
```bash
curl -u default:1q2w3e -X POST -d "@xxx.zip" --header 'Content-Type: application/zip' --header 'Accept: application/json' 'http://localhost:8080/api/v1/default_personal/launch/import'
```

This didn't work because:
 - `multipart/form-data` content type is needed :( why, really ?
 - `Authorization: bearer` header must be specified instead of username/password

Working curl command is:
```bash
curl -X POST --header 'Content-Type: multipart/form-data' --header 'Authorization: bearer 0b6f3f20-aa6e-48d8-ade1-06a3707a8e40' -F file=@xxx.zip 'http://localhost:8080/api/v1/default_personal/launch/import'
 ```

## Conclusions
 - Maven / surefire integration is not working
 - REST API is working, have some confusion from it though
 - UI is nice
 - UI doesn't support bulk changes per multiple runs
 - No Test Case management
 - Had to mark failed test in all runs manually as "bug in the code"
 - No aggregated view on selected runs / testsuites

## Cleanup
```bash
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker rmi $(docker images -q)
docker volume ls -qf dangling=true | xargs  docker volume rm
```

## ReportPortal 4.2.0 update
In short - no exception from maven run when using agent-java-junit version 2.7.2., but still no data were uploaded :( Import via curl worked fine. UI works nicely for vdx-wildfly-testsuite size.