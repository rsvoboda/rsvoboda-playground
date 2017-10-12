IPv6 address checks
========================
Experiments with IPv6 address motivated by https://issues.jboss.org/browse/RESTEASY-1718 work.

Details about IPv6:
 * https://en.wikipedia.org/wiki/IPv6_address
 * https://tools.ietf.org/html/rfc2460
 * Several other RFCs mentioned in https://stackoverflow.com/a/17871737

Output
-------------------
 * RESTEasy check implemented in https://github.com/rsvoboda/Resteasy/commit/dbdd56b699b951a19defe7a5c04aaa3f6c5dc960
 * RESTEasy issues https://issues.jboss.org/browse/RESTEASY-1734
 * Further investigation of templating case - UriBuilder.fromUri("http://{host}:8080").build("[0:0:0:0:0:0:0:1]")
