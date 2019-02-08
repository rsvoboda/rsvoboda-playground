#  Notes for load testing / stress testing / performance testing

 * https://webtide.com/lies-damned-lies-and-benchmarks-2/
 * https://wiki.eclipse.org/Jetty/Howto/High_Load
 * https://www.stephenrlang.com/2016/02/load-testing-with-siege/

## TIME_WAIT issue
https://gist.github.com/carlos8f/3473107 - The default timeout for TIME_WAIT is on macOS 15 seconds. To change it do following steps
```bash
$ sysctl net.inet.tcp.msl
net.inet.tcp.msl: 15000
$ sudo sysctl -w net.inet.tcp.msl=100
net.inet.tcp.msl: 15000 -> 100
```

## ApacheBench tool
### Run in sequence
```
ab -k -n 2000 -c 1 -s 120 http://127.0.0.1:8080/a && ab -k -n 500000 -c 50 -s 120 http://127.0.0.1:8080/a
ab -k -n 2000 -c 1 -s 120 http://127.0.0.1:8080/b && ab -k -n 500000 -c 50 -s 120 http://127.0.0.1:8080/b
ab -k -n 2000 -c 1 -s 120 http://127.0.0.1:8080/c && ab -k -n 500000 -c 50 -s 120 http://127.0.0.1:8080/c
```
### Run in parallel
```
cat myurls.txt
http://127.0.0.1:8080/a
http://127.0.0.1:8080/b
http://127.0.0.1:8080/c

cat myurls.txt | parallel "ab -k -n 500000 -c 50 -s 120 {}"
```

## Siege tool
https://github.com/JoeDog/siege
```
siege -b -q -t2M -H "Connection: Keep-Alive" -c50 http://127.0.0.1:8080/c
```
## Ports / connection monitoring
```
netstat -ap tcp | grep TIME_WAIT | wc -l
netstat -ap tcp
netstat -ap tcp | grep -i "listen"
```
```
lsof -Pn -i4 | grep LISTEN
sudo lsof -PiTCP -sTCP:LISTEN
```