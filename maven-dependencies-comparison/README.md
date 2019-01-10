# Maven dependencies comparison
A lot of components are used in WildFly and it makes sense to have versions of dependencies aligned in component projects. It's not easy and convenient to compare versions manually.
This experiment is about helping with this challenge.

## Bash way
Get the list of dependencies and make 
```bash
mvn dependency:list -DoutputFile=$PWD/dep-list -DappendOutput=true
cat dep-list | sort | grep '\:.*\:' | cut -d: -f1-4 | sed "s/ //g" | uniq > dep-list-normalized
```

Pretty print the list based on first 2 tokens of groupId (split by `.`).
```bash
PREV_SHORT_GROUP=""
while read line; do
  GROUP=`echo "$line" | cut -d: -f1`
  SHORT_GROUP=`echo "$GROUP" | cut -d\. -f1-2`

  if [ "$SHORT_GROUP" != "$PREV_SHORT_GROUP" ]; then
    echo ""
  fi
  echo "$line"  ## debug line - echo "$line : $GROUP : $SHORT_GROUP"

  PREV_SHORT_GROUP=$SHORT_GROUP
done <dep-list-normalized > dep-list-pretty

```

Once you have `dep-list-pretty` files generated you can use any diff tool you prefer - e.g. `meld dep-list-pretty ../Resteasy/dep-list-pretty` or IDE diff tool.

