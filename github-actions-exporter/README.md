# Building custom github-actions-exporter to disable runners metrics

tl;dr: checkout the right branch, build, push, use

Repo: https://github.com/rsvoboda/github-actions-exporter/tree/no.runners

```
docker build -t rostasvo/github-actions-exporter:1.5.2.Final .
docker login --username=rostasvo
docker push rostasvo/github-actions-exporter
```

```
docker run --env GITHUB_REFRESH=30 --env GITHUB_TOKEN=__SECRET__ --env GITHUB_REPOS=quarkusio/quarkus,quarkusio/quarkus-quickstarts,quarkusio/quarkusio.github.io,quarkusio/code.quarkus.io,quarkusio/quarkus-platform,quarkusio/quarkus-ecosystem-ci -i --rm -p 9999:9999 rostasvo/github-actions-exporter:1.5.2.Final
```

Open http://localhost:9999/metrics