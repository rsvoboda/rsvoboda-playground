#!/bin/sh
mvn clean package && docker build -t cz.rsvoboda/jfreesvg .
docker rm -f jfreesvg || true && docker run -d -p 8080:8080 -p 4848:4848 --name jfreesvg cz.rsvoboda/jfreesvg 
