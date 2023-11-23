
```shell
ssh-keygen -t rsa -b 4096 -f kiyotakeshi-github

export GIT_SSH_COMMAND="ssh -i ./kiyotakeshi-github -F /dev/null"

direnv allow
```
