# Configure limits on RHEL8 using docker-compose

I have small VM running https://github.com/rsvoboda/gh-jira-metrics and the latest addition of `github-actions-exporter` is causing memory issues as the VM has only 2GB of RAM.

I played a bit to limit the memory, but doing so using `docker-compose.yml` is a bit tricky.
Tried different approaches but still wasn't able to see the limit in `docker stats` output.
At the end my troubles were related to several months old version of Docker and `docker-compose`.

Update of Docker and `docker-compose`:
```
curl -L https://github.com/docker/compose/releases/download/1.28.2/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose

yum --disablerepo=rhel-8-server-baseos-os-rpms,rhel-8-server-appstream-os-rpms --showduplicates list  docker-ce
yum --disablerepo=rhel-8-server-baseos-os-rpms,rhel-8-server-appstream-os-rpms install docker-ce-19.03.15-3.el8

docker-compose up -d
```

Limits defined in docker-compose.yml:
```diff
   github-actions-exporter:
     image: rostasvo/github-actions-exporter:1.5.2.Final
+    deploy:
+      resources:
+        limits:
+          memory: 200M
     expose:
       - 9999
     ports:
       - 9999:9999
```