Ansible and Vagrant
========================
Motto of Ansible by Red Hat (https://www.ansible.com/) is Automation for everyone, web page is claiming "Ansible is designed around the way people work and the way people work together."

Motto of Vagrant by HashiCorp (https://www.vagrantup.com/) is Development Environments Made Easy, web page is claiming "Vagrant provides the same, easy workflow regardless of your role as a developer, operator, or designer. It leverages a declarative configuration file which describes all your software requirements, packages, operating system configuration, users, and more."

Running the example
-------------------
```bash
vagrant up
ansible-playbook configure-ci-server.yml -i hosts
``` 

Backlog of commands
-------------------
```bash
gedit Vagrantfile
vagrant up

gedit configure-ci-server.yml
gedit hosts
ansible-playbook configure-ci-server.yml -i hosts
ls -l .vagrant/machines/default/libvirt/
ansible-playbook configure-ci-server.yml -i hosts

vagrant ssh
``` 
 