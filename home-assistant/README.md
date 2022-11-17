# Home Assistant Notes

## SSH and command line tools
 - Install SSH addon - http://HA_SERVER:8123/hassio/addon/core_ssh/info
 - Access the server - ssh root@HA_SERVER # no password by default needed
 - use `ha` tool - https://www.home-assistant.io/hassio/commandline/

## Limit writes to SD card
SD card is not as durable as a normal disk and it is wise to limit IO operations.
Something similar to ramlog for systemd based systems can help to keep SD card in better shape.

For RPi there is Log2Ram which can be used on other systems too (with small adjustments).

Log2Ram installation: https://github.com/azlux/log2ram#install

## BT disconnect issue
Using HA built-in BT integration https://www.home-assistant.io/integrations/bluetooth with Xiaomi BLE https://www.home-assistant.io/integrations/xiaomi_ble to display values from Xiaomi Bluetooth temperature and humidity sensor model LYWSDCGQ/01ZM.

Temperature and humidity values are shown just for short time, one needs to reload the Bluetooth integration. Searching brought me to https://github.com/home-assistant/core/issues/76186#issuecomment-1204954485. The issue is with Bluez and RPi4 onboard BT.

Using Home Assistant OS 9.3 with HA Core 2022.11.3 and the issue is still present even though the GH issue is closed.

### Workaround
```bash
cat /root/bt.sh

#!/bin/bash
bluetoothctl power off
sleep 1
bluetoothctl power on
sleep 1
bluetoothctl scan on
```

Run `crontab -e` and add `*/5 * * * * /root/bt.sh` line.
