# Documentation - checks for links / images / anchors
I was looking at https://github.com/keycloak/keycloak-documentation#building-keycloak-documentation and noticed nice feature they have implemented.

When the documentation is generated there are checka for its validity - includes, links, images, anchors
 * https://github.com/keycloak/keycloak-documentation/tree/master/tests/src/test/java/org/keycloak/documentation/test
 * https://github.com/keycloak/keycloak-documentation/blob/master/tests/src/test/java/org/keycloak/documentation/test/AbstractDocsTest.java

I believe similar checks should be implemented any documentation.

## Migration PoC
Goal of the experiment is to check https://access.redhat.com/documentation/en-us/red_hat_jboss_enterprise_application_platform/7.0/html-single/7.0.0_release_notes/ content

Steps you need to do:
1) Add mapping
```patch
$ git diff
diff --git a/tests/src/test/resources/guide-url-fragments-community b/tests/src/test/resources/guide-url-fragments-community
index a5c8418..8688840 100644
--- a/tests/src/test/resources/guide-url-fragments-community
+++ b/tests/src/test/resources/guide-url-fragments-community
@@ -5,4 +5,5 @@ securing_apps=securing_apps
 server_admin=server_admin
 server_development=server_development
 server_installation=server_installation
-upgrading=upgrading
\ No newline at end of file
+upgrading=upgrading
+7.0.0_release_notes=7.0.0_release_notes

```

2) Add test
```java
cat src/test/java/org/keycloak/documentation/test/RN700Test.java
package org.keycloak.documentation.test;

public class RN700Test extends AbstractDocsTest {

    @Override
    public String getGuideDirName() {
        return "7.0.0_release_notes";
    }

}

```

3) Run test which specifies `guideBaseUrl`

```bash
mvn test -Dtest=RN700Test -DguideBaseUrl=https://access.redhat.com/docuentation/en-us/red_hat_jboss_enterprise_application_platform/7.0/html-single/
```