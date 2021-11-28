# UniFi Dream Machine (UDM) hacks

Starting article:
 - https://github.com/albertzsigovits/writeups/blob/main/unifi-udm/README.md

Main links:
 - https://github.com/boostchicken/udm-utilities
 - https://github.com/ntkme/unifi-systemd
 - https://github.com/ntkme/unifi-systemd-units
 - https://github.com/topics/udm
 - https://github.com/dlk3/udm-hacks

Follow-up links:
 - https://github.com/tusc/ntopng-udm
 - https://community.ui.com/questions/ntopng-for-the-UDM-UDM-PRO/87b051ff-0003-4d5b-bbdc-48f962617ee7?page=1
 - https://github.com/kchristensen/udm-le
 - https://www.redhat.com/sysadmin/ssh-proxy-bastion-proxyjump
 - https://github.com/mtalexan/udm-instructions
 - https://github.com/cpriest/udm-patches
 - https://github.com/heXeo/ubnt-fan-speed
 - https://github.com/pbrah/eap_proxy-udmpro
 - https://github.com/wicol/unifi-dns
 - https://github.com/fabianishere/udm-kernel-tools

Applications related to UDM
 - https://github.com/Art-of-WiFi/UniFi-API-browser
 - https://github.com/NickWaterton/Unifi-websocket-interface
   - change https://github.com/NickWaterton/Unifi-websocket-interface/blob/master/unifi_client.py#L143 to True

API endpoints on the UniFi controller
 - https://ubntwiki.com/products/software/unifi-controller/api
 - https://github.com/ameyuuno/docker-unifi-led-light-switch/blob/master/scripts/unifi-led-switch.sh

Traffic Analysis and Flows Tracking
 - https://www.ntop.org/products/traffic-analysis/ntop/
 - https://github.com/tusc/ntopng-udm#installing
 - https://github.com/tusc/ntopng-udm#uninstalling + `rm -rf /mnt/data/ntopng`
 - a lot of details, all-in-one solution makes UDM and CPU fan quite busy

Works nicely with `on-boot-script` from `udm-utilities` repo, `30-ntopng.sh` example:
```bash
#!/bin/sh
CONTAINER=ntopng

if podman container exists ${CONTAINER}; then
  podman start ${CONTAINER}
else
  logger -s -t ntopng -p ERROR Container $CONTAINER not found, make sure you set the proper name
fi
```

Homebridge on UDM
 - https://github.com/boostchicken/udm-utilities/tree/master/homebridge
   - make sure `.conflist` is linked into `/etc/cni/net.d/` before running `podman` command
   - https://github.com/boostchicken/udm-utilities/blob/master/homebridge/on_boot.d/25-homebridge.sh#L15
 - https://awesomeopensource.com/projects/homebridge
 - https://awesomeopensource.com/project/naofireblade/homebridge-weather-plus
 - https://github.com/sahilchaddha/awesome-homebridge
