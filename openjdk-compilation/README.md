# OpenJDK 8 compilation

Experiment based on Aleksey Shipilëv‏'s tweet - https://twitter.com/shipilev/status/933633023735664640

Some modifications were needed to make it successfully to the end.

```bash
wget https://builds.shipilev.net/workspaces/jdk8u-jdk8u.tar.xz -O - | tar xJf -
cd jdk8u-jdk8u
hg up
sh common/bin/hgforest.sh up

sudo dnf install libXtst-devel libXt-devel libXrender-devel cups-devel freetype-devel alsa-lib-devel ccache

chmod a+x ./configure
## ./configure --with-cacerts-file=/etc/ssl/certs/java/cacerts
##  ^^^ fails due to warnings 
## CFLAGS="-Wno-error=deprecated-declarations" ./configure --with-cacerts-file=/etc/ssl/certs/java/cacerts --with-extra-cflags
##  ^^^ fails on make: yes: file or directory not found, --with-extra-cflags without parameters is translated to yes
## CFLAGS="-Wno-error=deprecated-declarations" ./configure --with-cacerts-file=/etc/ssl/certs/java/cacerts --with-extra-cflags="-Wno-error=deprecated-declarations"
##  ^^^ verification fails due to non-existing /etc/ssl/certs/java/cacerts

CFLAGS="-Wno-error=deprecated-declarations" ./configure --with-cacerts-file=/opt/jdk1.8.0_152/jre/lib/security/cacerts --with-extra-cflags="-Wno-error=deprecated-declarations"

make images
```
```bash
$ build/linux-x86_64-normal-server-release/images/j2sdk-image/bin/java -version
openjdk version "1.8.0-internal"
OpenJDK Runtime Environment (build 1.8.0-internal-rsvoboda_2017_11_24_10_12-b00)
OpenJDK 64-Bit Server VM (build 25.71-b00, mixed mode)
```