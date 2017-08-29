Performance Co-Pilot and Vector performance monitoring framework
========================
Performance Co-Pilot (http://pcp.io/) is open source framework and toolkit for monitoring.
Vector (http://vectoross.io/) is an open source on-host performance monitoring framework which exposes hand picked high resolution system and application metrics to every engineer’s browser. 

Follow these links to get started:
 * http://pcp.io/docs/guide.html#install
 * http://vectoross.io/#getting-started
 * https://access.redhat.com/solutions/1137023
 * http://rhelblog.redhat.com/2015/12/18/getting-started-using-performance-co-pilot-and-vector-for-browser-based-metric-visualizations/
 * http://vectoross.io/docs/building-vector-source

 Notes:
 * Use `pcp --host=host_address` to check availability of pcp data
 * Add ports 44321 and 44323 to allowed ports on firewall
 * Put Vector bits in `/var/www/html` or use `python -m SimpleHTTPServer` to serve the data
 * Use `host_address:44323` as `Hostname` and `localhost` as `Hostpec` in Vector UI

 RHEL 6 steps
 ------------------------
```bash
yum install pcp 
chkconfig pmcd on
chkconfig pmlogger on
service pmcd start
service pmlogger start

yum install pcp-webapp-vector pcp-webapi
service pmwebd start
chkconfig pmwebd on

system-config-firewall 
 ```
 
 Localhost example URL: http://localhost:44323/vector/index.html#/?host=localhost:44323&hostspec=localhost&widgets=kernel.all.cpu,kernel.percpu.cpu,kernel.all.runnable,kernel.all.load,network.interface.bytes,network.tcpconn,network.interface.packets,network.tcp.retrans,mem,mem.vmstat.pgfault,kernel.all.pswitch,disk.iops,disk.bytes,disk.dev.avactive,disk.dev.latency
