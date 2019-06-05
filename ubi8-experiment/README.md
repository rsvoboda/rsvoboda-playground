# UBI 8 experiment
Universal Base Images (UBI) details can be found for example in https://developers.redhat.com/blog/2019/05/31/working-with-red-hat-enterprise-linux-universal-base-images-ubi/

UBI8 images details as of 2019-06-05
```
docker images | grep ubi
registry.access.redhat.com/ubi8/ubi           latest               4a0518848c7a        5 weeks ago         208MB
registry.access.redhat.com/ubi8/ubi-minimal   latest               3bfa511b67f8        5 weeks ago         90MB
```

```
docker run -i -t registry.access.redhat.com/ubi8/ubi bash
dnf search fooBarBaz
```

```
docker run -i -t registry.access.redhat.com/ubi8/ubi-minimal bash

dnf search fooBarBaz
bash: dnf: command not found

microdnf --nodocs -y install zlib-devel && microdnf clean all
```
`microdnf` doesn't support search so I had second terminal with full UBI8 image running and did search using classical `dnf`.
