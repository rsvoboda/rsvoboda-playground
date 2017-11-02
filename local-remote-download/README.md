# How to download local or remote bits the same way

## WGET
```bash
wget file:///home/rsvoboda/git/japit/target/japit.jar
file:///home/rsvoboda/git/japit/target/japit.jar: Unsupported scheme ‘file’.
```

## CURL
curl is our hero, URL can start with `http(s)://` or `file://` when using `curl ${URL} > downloaded_file` to get bits downloaded properly.
```bash
$ curl file:///home/rsvoboda/git/japit/target/japit.jar > aaa.jar
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100  809k  100  809k    0     0   336M      0 --:--:-- --:--:-- --:--:--  336M

$ java -jar aaa.jar 
Option "-cmd (--command)" is required
 -cmd (--command) [COMPARE | LIST] : Command to run

  Example: java -jar japit.jar  -cmd (--command) [COMPARE | LIST] [command arguments]
```
