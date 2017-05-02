ConfigMap Used In WildFly
========================
You can inject Kubernetes ConfigMap values with Java EE & WildFly

This sample is based on https://blog.sebastian-daschner.com/entries/kubernetes_configmaps_javaee article.

ConfigMap values are persisted as properties files, those files are exposed in global WildFly module.
Custom CDI producer for the properties can be used your application to simplify usage.

