# Simple HTTP and HTTPS Server
I often need simple way to serve content of current directory via HTTP.

From time to time I also need to use HTTPS, usually just for some experiments.

I ended up using Python which can be a bit shocking for Java guy.

## HTTP
As simple as:
```bash
python -m SimpleHTTPServer
```
In few milliseconds you will see `Serving HTTP on 0.0.0.0 port 8000 ...` in the console.

Custom port can be specified too:
```bash
python -m SimpleHTTPServer 8888
```

## HTTPS
This is a bit more complicated, you need python script to have https server.

https-server.py content:
```python
#! /usr/bin/env python

import BaseHTTPServer, SimpleHTTPServer
import ssl

httpd = BaseHTTPServer.HTTPServer(('localhost', 4443),
        SimpleHTTPServer.SimpleHTTPRequestHandler)

httpd.socket = ssl.wrap_socket (httpd.socket,
        keyfile="key.pem", certfile='cert.pem', server_side=True)

httpd.serve_forever()
```

```bash
openssl req -x509 -newkey rsa:2048 -keyout key.pem -out cert.pem -days 365
chmod u+x https-server.py
./https-server.py
```

## Python 3 and Twisted
See https://anvileight.com/blog/2016/03/20/simple-http-server-with-python/