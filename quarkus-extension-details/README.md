# Quarkus extension details via curl / jq magic

## REST API
There is REST API available for https://code.quarkus.io/.

`curl https://code.quarkus.io/api/extensions 2>/dev/null | jq .` gives details like this:

```json
  {
    "category": "Data",
    "default": false,
    "description": "Define your persistent model with Hibernate ORM and JPA",
    "guide": "https://quarkus.io/guides/hibernate-orm",
    "id": "io.quarkus:quarkus-hibernate-orm",
    "keywords": [
      "hibernate-orm",
      "jpa",
      "hibernate"
    ],
    "labels": [
      "hibernate-orm",
      "jpa",
      "hibernate"
    ],
    "name": "Hibernate ORM",
    "order": 13,
    "shortId": "vH0",
    "shortName": "JPA",
    "status": "stable",
    "tags": [],
    "version": "1.3.2.Final"
  }
```

## Customizing returned JSON
There is quite a lot of details, luckily `jq` tool allows to select only certain elements and map them to another JSON
To select just extension id and associated tags, run `curl https://code.quarkus.io/api/extensions 2>/dev/null | jq 'map({(.id): (.tags | join(","))}) | add'`


```json
{
  "io.quarkus:quarkus-amazon-lambda": "preview",
  "io.quarkus:quarkus-amazon-lambda-http": "preview",
  "io.quarkus:quarkus-amazon-lambda-xray": "preview",
  "io.quarkus:quarkus-azure-functions-http": "preview",
  "io.quarkus:quarkus-container-image-docker": "preview",
  "io.quarkus:quarkus-container-image-jib": "preview",
  "io.quarkus:quarkus-container-image-s2i": "preview",
  "io.quarkus:quarkus-kubernetes-client": "",
  "io.quarkus:quarkus-smallrye-metrics": "",
  "io.quarkus:quarkus-smallrye-opentracing": "",
  ...
}
```