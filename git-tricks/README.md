# Git tricks

### Latest tags
```bash
git --git-dir $my_clone/.git describe --tags --abbrev=0
git describe --tags --abbrev=0
```

### Activity on branches
```bash
for k in `git branch -r | perl -pe 's/^..(.*?)( ->.*)?$/\1/'`; do
  echo -e `git show --pretty=format:"%Cgreen%ci %Cblue%cr%Creset" $k -- | head -n 1`\\t$k;
done | sort -r
```
### Activity on repository
```bash
git shortlog --summary --numbered --since="last month" --email
git shortlog --summary --numbered --after="2018-01-01" --email
```

### Activity on repository via git-score
https://github.com/msparks/git-score#git-score
```bash
git-score --order=-commits 12.0.0.Final..HEAD
git-score --order=-commits --repo=wildfly --repo=wildfly-core
```