# Jenkins Job Builder experiment

Jenkins Job Builder is hosted under openstack.org site, see https://docs.openstack.org/infra/jenkins-job-builder/

```bash
pip install --user jenkins-job-builder
```
This installs version 1.6.2. When you experiment with Pipeline jobs you get nasty error ending with `AttributeError: 'NoneType' object has no attribute 'name'`. I found help in Jenkins Users forum https://groups.google.com/forum/#!topic/jenkinsci-users/zObsQVDpkCE, you neeed to upgrade to version 2.0.0.0b2.

```bash
pip install --user --upgrade jenkins-job-builder==2.0.0.0b2
```