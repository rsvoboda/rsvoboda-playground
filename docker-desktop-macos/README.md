# Docker Desktop for macOS
I had and still have troubles with Docker Desktop for macOS.
Some of the issues might be related to my recent upgrade to macOS Catalina but not exclusively. 

## Docker is starting issues
Starting for minutes and no further progress, high CPU usage.

## Docker investigation
 - https://docs.docker.com/docker-for-mac/troubleshoot/
 - `screen ~/Library/Containers/com.docker.docker/Data/vms/0/tty`


## Docker cleanup
 - https://docs.docker.com/engine/reference/commandline/system_prune/
 - https://nektony.com/how-to/uninstall-docker-mac

## Docker edge
 - https://hub.docker.com/editions/community/docker-ce-desktop-mac
 - https://download.docker.com/mac/edge/Docker.dmg

## Docker is using one core for 100%
With Docker Desktop Edge I'm able to use docker, but high CPU usage is still present. 