# How to run macOS quarantined apps / downloaded from internet

MacOS Big Sur is apparently more restrictive when it comes to applications downloaded from internet.
"Appname.app is damaged and can’t be opened. You should move it to the Trash." is shown with just `Cancel` and `Move to Trash` options.

This command removes the attributes which prevent execution of graalvm-ce-java11-20.3.0:
```
sudo xattr -d -r com.apple.quarantine ~/Downloads/graalvm-ce-java11-20.3.0/
```

Resources:
 - https://osxdaily.com/2019/02/13/fix-app-damaged-cant-be-opened-trash-error-mac/
 - https://osxdaily.com/2010/09/12/disable-application-downloaded-from-the-internet-message-in-mac-os-x/