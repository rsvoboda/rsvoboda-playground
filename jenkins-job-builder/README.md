# Jenkins Job Builder experiment

Jenkins Job Builder is hosted under openstack.org site, see https://docs.openstack.org/infra/jenkins-job-builder/

Source code is available on GitHub, https://github.com/openstack-infra/releases

## Installation

```bash
pip install --user jenkins-job-builder
```
This installs version 1.6.2. When you experiment with Pipeline jobs you get nasty error ending with `AttributeError: 'NoneType' object has no attribute 'name'`. I found help in Jenkins Users forum https://groups.google.com/forum/#!topic/jenkinsci-users/zObsQVDpkCE, you neeed to upgrade to version 2.0.0.0b2.

```bash
pip install --user --upgrade jenkins-job-builder==2.0.0.0b2
```

## Configuration
Details in https://docs.openstack.org/infra/jenkins-job-builder/execution.html
```bash
$ cat jenkins_jobs.ini
[job_builder]
ignore_cache=True
keep_descriptions=False
include_path=.:../scripts:~/git/
recursive=False
allow_duplicates=False

[jenkins]
user=admin
password=xxxxxxxxxxxxxxxx
url=http://127.0.0.1:8080/
```
Password in configuration file is the API token for the user specified. You can get this through the Jenkins management interface under People -> username -> Configure and then click the Show API Token button.

If you don't want to include this information into configuration file you can specify it on command line using `--user USER, -u USER` and `--password PASSWORD, -p PASSWORD`

```bash
jenkins-jobs --help
jenkins-jobs --conf jenkins_jobs.ini update foo.yml
```
## Examples
 * jjb-freestyle-all-bash.yml
 * jjb-pipeline-2-jobs.yml
 * jjb-pipeline-scm.yml
 * jjb-pipeline-script.yml
