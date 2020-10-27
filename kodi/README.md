# Run add-on on Kodi startup

autoexec.py:
```python
import xbmc

xbmc.executebuiltin('XBMC.RunAddon(plugin.video.stream-cinema-2-release)')
```

Place this file into into `.kodi/userdata/` and reboot Kodi.
Tried with `LibreELEC (official): 9.2.4 (RPi2.arm)`, Kodi version 18.8

Userdata approach is deprecated and was removed in https://github.com/xbmc/xbmc/pull/18356.
Future releases of Kodi will use https://kodi.wiki/view/Autoexec_Service approach.