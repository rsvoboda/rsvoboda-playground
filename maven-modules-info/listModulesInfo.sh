#!/bin/bash
# 
# Lists info about maven project modules

dir=${1:-"."}
SUM_SOURCES=0
SUM_TESTS=0

echo "POM location,Sources,Source Files Count,Tests,Test Files Count"
for i in `find $dir | grep pom.xml$ | sort`; do
  DIR=`dirname $i`
  
  if [ -d "$DIR/src" ]; then
    LINE="$DIR"
    if [ -d "$DIR/src/main/java" ]; then
      COUNT=`find "$DIR/src/main/java" | grep java$ | wc -l | sed "s/ //g"`
      (( SUM_SOURCES+=COUNT ))
      LINE="$LINE,Y,$COUNT"
    else
      LINE="$LINE,N,0"
    fi
    if [ -d "$DIR/src/test/java" ]; then
      COUNT=`find "$DIR/src/test/java" | grep java$ | wc -l | sed "s/ //g"`
      (( SUM_TESTS+=COUNT ))
      LINE="$LINE,Y,$COUNT"
    else
      LINE="$LINE,N,0"
    fi
    echo "$LINE"
  else
    ## ignore
    echo "$DIR,N,0,N,0" > /dev/null
  fi
done

echo "SUM,,$SUM_SOURCES,,$SUM_TESTS"