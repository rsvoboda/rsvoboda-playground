# Running more commands via SSH

```bash
ssh my_awesome_machine << EOF
  uname -a
  uptime
  df -h
  free
  ps aux
EOF
```