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

For example usage of `<<:` see `defaults.yaml` and definition of `comp-c-defaults`.

## Folders in JJB
Check [jobs/folders.yaml](jobs/folders.yaml) to see examples. Latest JJB build from master is needed.

Commits with the functionality:
 * http://git.openstack.org/cgit/openstack-infra/jenkins-job-builder/commit/?id=af9d984baa7f93ba8e846ff30a681d04117397e7
 * http://git.openstack.org/cgit/openstack-infra/jenkins-job-builder/commit/?id=8bcd0d0bd2caf9d28d4f55cba712afb20654b591


## Backlog: Views via JVB
I tried https://github.com/piyush0101/jenkins-view-builder

It works fine with security disabled and CSRF Protection disabled.
With security or CSRF Protection enabled it has troubles to work properly, tested on Jenkins 2.95.

Config used with jenkins-view-builder:
```yaml
- view:
    type: list
    name: test_jobs
    description: Sample Test jobs
    jobs:
      - test_job
      - test_job_v2
      - test_job_v3
    columns:
        - status
        - weather
        - job
        - last_success
        - last_failure
        - last_duration
        - build_button
    recurse: False
```

More about views via Jenkins HTTP API in https://github.com/rsvoboda/rsvoboda-playground/tree/master/jenkins-api#jenkins-and-security-with-csrf-protection
