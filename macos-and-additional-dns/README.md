# macOS and additional DNS definition for certain namespace
We have an internal DNS server for few machines in local network.
So lets add it to my macOS for that specific domain resolving.

## UI way
https://support.apple.com/guide/mac-help/specify-a-dns-server-on-mac-mchlp2720/10.15/mac/10.15
This didn't work for me well
 - setting is network specific - e.g. Wi-Fi, Thunderbolt ethernet, Thunderbolt bridge, ...
 - `dig` command didn't find the IP for the hostname
   - might be user error of course, might not (see below)

Anyway I like the second way more.

## Command line way
https://medium.com/@jamieeduncan/i-recently-moved-to-a-macbook-for-my-primary-work-laptop-7c704dbaff59 is the best and the simplest description of the command line way. 

```
echo "nameserver 10.0.0.1" > /etc/resolver/quarkus
scutil --dns
```

But wait, `dig console-openshift-console.apps.ocp-xyz.dynamic.quarkus` returns NXDOMAIN! As written in the article `dig` and `nslookup` on OSX don’t use the OS resolver configuration, this blocked me for a while before finding that medium.com article.

Solution is to use `dscacheutil -q host -a name console-openshift-console.apps.ocp-xyz.dynamic.quarkus` or browser.