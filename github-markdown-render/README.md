# Render your .md file to .html using curl and GitHub

```bash
jq --slurp --raw-input '{"text": "\(.)", "mode": "markdown"}' < 01.md | curl --data @- https://api.github.com/markdown > 01.html
```