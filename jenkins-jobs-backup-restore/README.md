# Jenkins jobs backup / restore / new jobs

First 3 procedures require active Kerberos ticket ++ disabled CSRF Protection.
Fourth procedure shows use-case with enabled CSRF Protection.

## Backup procedure
```bash
JOBS=(
    https://Jenkins_Server/view/ABC/view/ABC-Performance/job/abc-ws-perf-load/
    https://Jenkins_Server/view/ABC/view/ABC-Performance/job/abc-ws-perf-load-mtom/
    https://Jenkins_Server/view/ABC/view/ABC-Performance/job/abc-ws-perf-load-security/
    https://Jenkins_Server/view/ABC/view/ABC-Performance/job/abc-ws-perf-soak/
)
for JOB in "${JOBS[@]}"; do
  JOB_NAME=`echo $JOB | cut -d\/ -f 10`
  echo "$JOB ==> $JOB_NAME"
  curl -s -k --negotiate -u aaa:a $JOB/config.xml > $JOB_NAME.xml
done
```

## Restore procedure
```bash
for JOB_XML in `ls *.xml`; do
   JOB_NAME="`echo $JOB_XML | cut -d. -f1`"
   echo "$JOB_XML ==> $JOB_NAME"
   curl -s -k --negotiate -u aaa:a -H "Content-Type: text/xml" -X POST --data-binary @$JOB_XML https://Jenkins_Server/view/ABC/view/ABC-Performance/job/$JOB_NAME/config.xml
done
```

## New jobs procedure
```bash
for JOB_XML in `ls *.xml`; do
   JOB_NAME="`echo $JOB_XML | cut -d. -f1`-new-version"
   echo "$JOB_XML ==> $JOB_NAME"
   curl -s -k --negotiate -u aaa:a -H "Content-Type: text/xml" -X POST --data-binary @$JOB_XML https://Jenkins_Server/view/ABC/view/ABC-Performance/createItem?name=$JOB_NAME
done
```

## Secured Jenkins - crumb aka CSRF Protection
See https://stackoverflow.com/questions/45466090/how-to-get-the-api-token-for-jenkins to get API_TOKEN
```bash
CRUMB=$(curl -s 'https://Jenkins_Server_With_CSRF/crumbIssuer/api/xml?xpath=concat(//crumbRequestField,":",//crumb)' -u rsvoboda:API_TOKEN)

for JOB_XML in `ls *.xml`; do
   JOB_NAME="`echo $JOB_XML | cut -d. -f1`-new-version"
   echo "$JOB_XML ==> $JOB_NAME"
   curl -s -XPOST "https://Jenkins_Server_With_CSRF/createItem?name=$JOB_NAME" -u rsvoboda:API_TOKEN --data-binary @$JOB_XML -H "$CRUMB" -H "Content-Type:text/xml"
done
```
