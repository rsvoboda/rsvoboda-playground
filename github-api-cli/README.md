# GitHub CLI and APIs

## GitHub CLI
Home of CLI is https://hub.github.com/
```bash
hub pr list -L 20 -b develop --format='%t [%H] | %U%n'
hub pr list -L 20 --format='%t [%H] | %U%n'
hub pr list -L 20

hub issue
hub issue -L20
hub issue show 3460
hub issue -L120 -l enhancement
```

## GitHub APIs
 * https://gist.github.com/jonmagic/5282384165e0f86ef105  --  Complete issue import API walkthrough with curl 
* https://developer.github.com/changes/
* https://developer.github.com/v3/
* https://developer.github.com/v3/issues/

* https://github.com/susinda/github-client/blob/master/src/main/java/org/wso2/git/client/GitRestApiExecutor.java

* https://api.github.com/repos/quarkusio/quarkus/issues?state:open
