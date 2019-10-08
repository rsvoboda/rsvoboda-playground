# JFreeSVG

# Skeleton
```
mvn archetype:generate -DarchetypeGroupId=com.airhacks -DarchetypeArtifactId=javaee8-essentials-archetype -DarchetypeVersion=0.0.4
```

# Build
```
mvn clean package && docker build -t cz.rsvoboda/jfreesvg .
```

# Run
```
docker rm -f jfreesvg || true && docker run -d -p 8080:8080 -p 4848:4848 --name jfreesvg cz.rsvoboda/jfreesvg 
```

# Try
```
curl localhost:8080/jfreesvg/resources/ping
```