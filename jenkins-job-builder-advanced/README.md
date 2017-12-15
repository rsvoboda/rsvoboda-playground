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
```