#!/bin/bash

# Recursively deploys folder content.
# Attempt checksum deploy first to optimize upload time.
# Target repository is cleared before the deploy starts.

repo_url="http://10.20.30.40:8040/artifactory"
tgt_repo="my-maven-repo-zip"
user=deploy_user
pass=deploy_user_password

dir="$1"
if [ -z "$dir" ]; then echo "Please specify a directory to recursively upload from!"; exit 1; fi
if [ ! -x "`which sha1sum`" ]; then echo "You need to have the 'sha1sum' command in your path."; exit 1; fi

# Delete repo content
curl -X DELETE -u $user:$pass "${repo_url}/${tgt_repo}"

# Create repo - Notes: Requires Artifactory Pro
# https://www.jfrog.com/confluence/display/JFROG/Repository+Configuration+JSON
# curl -X PUT -H "Content-Type: application/vnd.org.jfrog.artifactory.repositories.LocalRepositoryConfiguration+json;" -d '{"key": "${tgt_repo}", "rclass": "local", "packageType": "maven"}' -u $user:$pass "${repo_url}/api/repositories/${tgt_repo}"

# Upload by checksum all files from the source dir to the target repo
find "$dir" -type f | grep -v md5 | grep -v sha1 | sort | while read f; do
    rel="$(echo "$f" | sed -e "s#$dir##" -e "s# /#/#" | sed "s#^/##")";
    sha1=$(sha1sum "$f")
    sha1="${sha1:0:40}"
    printf "\n\nUploading '$f' (cs=${sha1}) to '${repo_url}/${tgt_repo}/${rel}'"
    status=$(curl -k -u $user:$pass -X PUT -H "X-Checksum-Deploy:true" -H "X-Checksum-Sha1:$sha1" --write-out %{http_code} --silent --output /dev/null "${repo_url}/${tgt_repo}/${rel}")
    echo "status=$status"
    # No checksum found - deploy + content
    [ ${status} -eq 404 ] && {
	    curl -k -u $user:$pass -X PUT -H "X-Checksum-Sha1:$sha1" -T "$f" "${repo_url}/${tgt_repo}/${rel}"
    }
done