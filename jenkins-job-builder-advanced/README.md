# Jenkins Job Builder - advanced experiment

Advanced experiment with Jenkins Job Builder comparing to https://github.com/rsvoboda/rsvoboda-playground/tree/master/jenkins-job-builder 

This experiment is covering defaults and templates. Goal is to simulate large set of jobs and their management.

## Execution
Edit `jenkins_jobs.ini` and modify `password` line, API Token is available in user config, e.g. http://localhost:8080/user/admin/configure

```bash
jenkins-jobs --conf jenkins_jobs.ini update jobs/
```