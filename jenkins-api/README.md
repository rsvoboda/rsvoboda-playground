# Jenkins HTTP API experiment

Some links:
 * https://support.cloudbees.com/hc/en-us/articles/219257077-CSRF-Protection-Explained
 * https://support.cloudbees.com/hc/en-us/articles/220857567-How-to-create-a-job-using-the-REST-API-and-cURL-
 * https://stackoverflow.com/a/35207947/6266477
 * https://issues.jenkins-ci.org/browse/JENKINS-8927?focusedCommentId=194588&page=com.atlassian.jira.plugin.system.issuetabpanels%3Acomment-tabpanel#comment-194588
 * $JENKINS_SERVER/api/

## Jenkins and security with CSRF protection
```bash
USER=admin
APITOKEN=8861882a9bfae61392edf23c402359cc  ## API Token section of http://localhost:8080/user/admin/configure
SERVER=http://localhost:8080
CRUMB=$(curl --user $USER:$APITOKEN \
    $SERVER/crumbIssuer/api/xml?xpath=concat\(//crumbRequestField,%22:%22,//crumb\))
echo $CRUMB
```

## Jenkins and views
```bash
## update view
curl --user $USER:$APITOKEN -H "$CRUMB" -X POST --data-binary @sample-view.xml -H "Content-Type:text/xml" $SERVER/view/sds/config.xml

## add / remove job to/from view view
curl --user $USER:$APITOKEN -H "$CRUMB" -X POST $SERVER/view/sds/addJobToView?name=test_job_v3
curl --user $USER:$APITOKEN -H "$CRUMB" -X POST $SERVER/view/sds/removeJobFromView?name=test_job_v3

## create view
curl --user $USER:$APITOKEN -H "$CRUMB" -X POST -d @sample-view.xml -H "Content-Type: text/xml" $SERVER/createView?name=MyView
```

sample-view.xml:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<hudson.model.ListView>
  <name>sds</name>
  <description>foooooo</description>
  <filterExecutors>false</filterExecutors>
  <filterQueue>false</filterQueue>
  <properties class="hudson.model.View$PropertyList"/>
  <jobNames>
    <comparator class="hudson.util.CaseInsensitiveComparator"/>
    <string>test_job</string>
    <string>test_job_v2</string>
  </jobNames>
  <jobFilters/>
  <columns>
    <hudson.views.StatusColumn/>
    <hudson.views.WeatherColumn/>
    <hudson.views.JobColumn/>
    <hudson.views.LastSuccessColumn/>
    <hudson.views.LastFailureColumn/>
    <hudson.views.LastDurationColumn/>
    <hudson.views.BuildButtonColumn/>
    <hudson.plugins.favorite.column.FavoriteColumn plugin="favorite@2.3.1"/>
  </columns>
  <recurse>false</recurse>
</hudson.model.ListView>
```

## Jenkins and folders
``` bash
## check if job / folder is available
curl --user $USER:$APITOKEN -H "$CRUMB" -X GET $SERVER/checkJobName?value=a-class-folder
curl --user $USER:$APITOKEN -H "$CRUMB" -X GET $SERVER/checkJobName?value=test_job_v3

## create folder
curl --user $USER:$APITOKEN -H "$CRUMB" -X POST "$SERVER/createItem?name=rs-folder-experiment&mode=com.cloudbees.hudson.plugins.folder.Folder&from=&json=%7B%22name%22%3A%22rs-folder-experiment%22%2C%22mode%22%3A%22com.cloudbees.hudson.plugins.folder.Folder%22%2C%22from%22%3A%22%22%2C%22Submit%22%3A%22OK%22%7D&Submit=OK" -H "Content-Type:application/x-www-form-urlencoded"

## create job in folder
curl --user $USER:$APITOKEN -H "$CRUMB" $SERVER/job/test_job/config.xml > sample-job.xml
curl --user $USER:$APITOKEN -H "$CRUMB" -X POST --data-binary @sample-job.xml -H "Content-Type:text/xml" $SERVER/job/rs-folder-experiment/createItem?name=test_job_in_folder
```