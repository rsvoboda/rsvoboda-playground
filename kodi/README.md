# Kodi/LibreELEC tips and trics

## Run add-on on Kodi startup

autoexec.py:
```python
import xbmc

xbmc.executebuiltin('XBMC.RunAddon(plugin.video.stream-cinema-2-release)')
```

Place this file into into `.kodi/userdata/` and reboot Kodi.
Tried with `LibreELEC (official): 9.2.4 (RPi2.arm)`, Kodi version 18.8

Userdata approach is deprecated and was removed in https://github.com/xbmc/xbmc/pull/18356.
Future releases of Kodi will use https://kodi.wiki/view/Autoexec_Service approach.

## Modify the video cache
 - https://kodi.wiki/view/Advancedsettings.xml
 - https://kodi.wiki/view/HOW-TO:Modify_the_video_cache

### LibreELEC config for RPi5
/storage/.kodi/userdata/advancedsettings.xml
```xml
<advancedsettings>
  <cache>
    <buffermode>1</buffermode>
    <memorysize>1073741824</memorysize>
    <readfactor>20</readfactor>
  </cache>
</advancedsettings>
```

### Kodi config for macOS
Create file `~/Library/Application\ Support/Kodi/userdata/advancedsettings.xml` with content:
```xml
<advancedsettings>
  <cache>
    <buffermode>1</buffermode>
    <memorysize>1073741824</memorysize>
    <readfactor>20</readfactor>
  </cache>
</advancedsettings>
```
