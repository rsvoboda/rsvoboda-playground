# Jenkins Job Builder - advanced experiment

Advanced experiment with Jenkins Job Builder comparing to https://github.com/rsvoboda/rsvoboda-playground/tree/master/jenkins-job-builder 

This experiment is covering defaults and templates. Goal is to simulate large set of jobs and their management.

## Execution
Edit `jenkins_jobs.ini` and modify `password` line, API Token is available in user config, e.g. http://localhost:8080/user/admin/configure

```bash
## expects recursive=True in jenkins_jobs.ini
jenkins-jobs --conf jenkins_jobs.ini update jobs/
```

```bash
jenkins-jobs --conf jenkins_jobs.ini update --recursive jobs/
```

## Jobs Cleanup
Kind of brute force cleanup
```bash
jenkins-jobs --conf jenkins_jobs.ini update --delete-old  jobs/
```
Selective cleanup
```bash
jenkins-jobs --conf jenkins_jobs.ini delete test_job_v2
jenkins-jobs --conf jenkins_jobs.ini delete -j test_job test_job_v2 test_job_v3 test-comp-a test-comp-b
```

## JJB early 2018 update
JJB version 2.0.0.0b3 was released on January 15, 2018. Builds from master are not needed anymore.
```bash
pip install --user --upgrade jenkins-job-builder==2.0.0.0b3
## or
pip install --upgrade jenkins-job-builder==2.0.0.0b3
```

Since Jan 12, 2018 JJB is available in version 2.0.0
See https://github.com/openstack-infra/jenkins-job-builder/releases
```bash
pip install --user --upgrade jenkins-job-builder==2.0.0
## or
pip install --upgrade jenkins-job-builder==2.0.0
```

### Jenkins LTS 2.60.3 summary
folder.yaml is not working with default plugin set.
```
Caused by: java.lang.ClassCastException: jenkins.model.BuildDiscarderProperty cannot be cast to com.cloudbees.hudson.plugins.folder.AbstractFolderProperty
```
Additional investigation in progress

### Jenkins LTS 2.89.2 summary
Same as for 2.60.3

## List of Jenkins Plugins
You can use `get-plugins-info` command, but it's not yet available in tagged version of JJB.

You need to build the tool from master, follow https://github.com/openstack-infra/jenkins-job-builder/#developers instructions

```bash
which jenkins-jobs 
~/git/jenkins-job-builder/.venv/bin/jenkins-jobs

jenkins-jobs --conf jenkins_jobs.ini get-plugins-info -o plugins_info.yaml
INFO:jenkins_jobs.cli.subcommand.get_plugins_info:Generated plugins_info.yaml file
```

## Views in JJB
See [jobs/views.yaml](jobs/views.yaml) for list view example. Latest JJB build from master is needed.
Reasonable default columns for list views are available now http://git.openstack.org/cgit/openstack-infra/jenkins-job-builder/commit/?id=83592a094bfd8889e50d29019f5f7f12e225deb3

GitHub search for view examples: https://github.com/search?p=1&q=view-type%3A+list&type=Code&utf8=%E2%9C%93

## Merge of snippets in YAML aka Aliases and `<<:`
Merge Key Language-Independent Type for YAML version 1.1 is defined in http://yaml.org/type/merge.html

For example usage of `<<:` see [jobs/defaults.yaml#L13](jobs/defaults.yaml#L13) and definition of `comp-c-defaults`.

## Folders in JJB
Check [jobs/folders.yaml](jobs/folders.yaml) to see examples. Latest JJB build from master is needed.

Commits with the functionality:
 * http://git.openstack.org/cgit/openstack-infra/jenkins-job-builder/commit/?id=af9d984baa7f93ba8e846ff30a681d04117397e7
 * http://git.openstack.org/cgit/openstack-infra/jenkins-job-builder/commit/?id=8bcd0d0bd2caf9d28d4f55cba712afb20654b591


## Views and folders via Jenkins HTTP API
More about views and folders via Jenkins HTTP API in https://github.com/rsvoboda/rsvoboda-playground/tree/master/jenkins-api
