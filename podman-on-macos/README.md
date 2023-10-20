# Podman on macOS

 - https://podman-desktop.io/docs/installation/macos-install
 - https://podman-desktop.io/docs/migrating-from-docker/using-podman-mac-helper
 - https://podman-desktop.io/docs/migrating-from-docker/using-the-docker_host-environment-variable - 
 - https://quarkus.io/guides/podman#after-installation
 - https://medium.com/@jeesmon/podman-experiments-on-mac-efe62b68095c
 - https://java.testcontainers.org/features/configuration/#customizing-docker-host-detection

## Important parts of my setup

```
cat ~/.zshrc
...
export DOCKER_HOST=unix:///Users/rsvoboda/.local/share/containers/podman/machine/qemu/podman.sock
export TESTCONTAINERS_DOCKER_SOCKET_OVERRIDE=/var/run/docker.sock
...
```

```
cat ~/bin/docker
exec podman "$@"%
```

```
podman machine inspect podman-machine-rs
...
          "Resources": {
               "CPUs": 6,
               "DiskSize": 98,
               "Memory": 7809
          },
```