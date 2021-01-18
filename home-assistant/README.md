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
