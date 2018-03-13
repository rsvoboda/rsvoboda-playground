# Git tricks

### Latest tags
```bash
git --git-dir $my_clone/.git describe --tags --abbrev=0
git describe --tags --abbrev=0
```

### Activity on branches
```bash
for k in `git branch -r | perl -pe 's/^..(.*?)( ->.*)?$/\1/'`; do echo -e `git show --pretty=format:"%Cgreen%ci %Cblue%cr%Creset" $k -- | head -n 1`\\t$k; done | sort -r
```