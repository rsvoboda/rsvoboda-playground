# Research to expose Docker capabilities via OpenShift

## Problem definition
In our test suites we are using Docker to run services like databases. Some environments do not provide Docker or Podman capabilities, e.g Windows executors in GitHub Actions. It's often connected with unavailable nested virtualization support for Windows hosts.

## Solution idea
Having dedicated hosts with just Docker running can be the obvious solution, but it is static and needs active care/maintenance. OpenShift cluster is a possible candidate for a more dynamic approach. Ideally, it could act as a provider of Docker services that could be consumed from Windows runners. 

## Research
There are several hits when searching for keywords like `expose docker host from OpenShift`, but they do not really handle my use-case to export Docker(-ish) capabilities externally.

https://cloud.redhat.com/blog/running-testcontainers-in-openshift-pipelines-with-docker-in-docker describes the usage of Docker-in-Docker approach using sidecar in Tekton pipeline definition. The article describes the usage of Docker capabilities inside the cluster.

Simpler Tekton pipeline definition with Docker-in-Docker sidecar can be found in https://github.com/testcontainers/testcontainers-java/issues/1135#issuecomment-857933300 discussion.

https://timmhirsens.de/posts/2019/07/testcontainers_on_jenkins_with_kubernetes/ is another example of the Docker-in-Docker approach, this time in connection with Jenkins and the Kubernetes Plugin.

https://thecattlecrew.net/2020/03/02/testcontainers-unleash-your-unit-tests-using-docker-3-3/ describes a solution for Jenkins + Docker usage inside OpenShift. This article describes security-related challenges and the ways to overcome them, including setting proper group ID, appropriate Security Context Constraint, build of custom selinux-dockersock to access docker.sock under Fedora and RHEL. The setup seems to be one machine bound and can be sensitive to future OpenShift updates and changes.

https://github.com/testcontainers/testcontainers-java/issues/1135 is quite interesting but long reading. Some posts especially close to the bottom can be interesting for further research, e.g. https://github.com/testcontainers/testcontainers-java/issues/1135#issuecomment-770386271

PoC for Kubernetes support in Testcontainers library - https://github.com/masinger/testcontainers-java/blob/master/docs/features/kubernetes.md 

Docker in Docker image - https://hub.docker.com/_/docker

