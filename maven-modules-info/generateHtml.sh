#!/bin/bash
# 
# Generates html report based on csv generated by listModulesInfo.sh script
#   Usage example: ./generateHtml.sh ~/Downloads/modules.csv ~/Downloads/modules.html
#

input=${1:-"modules.csv"}
output=${2:-"modules.html"}

function checkValue {
  if [ $1 -gt 0 ]; then
    echo "<td style=\"background-color:green\"> $1"
    ## echo "<td>$1"
  else
    echo "<td style=\"background-color:red\"> $1"
  fi
}  

echo "<html><head><title>Maven project modules</title> \
<style> table {border-collapse: collapse;} td.module {text-align: left;}\
table, td, th {border: 1px solid black; padding: 12px; text-align: center;}</style></head><body><table>" > $output
echo "<body><table>" >> $output
i=1
while IFS=',' read -r f1 f2 f3 f4 f5
do 
  test $i -eq 1 && ((i=i+1)) && echo "<tr><th>$f1</th><th>$f2</th><th>$f3</th><th>$f4</th><th>$f5</th></tr>" >> $output && continue
  echo "<tr><td class=\"module\">$f1</td><td>$f2</td>$(checkValue $f3)</td><td>$f4</td>$(checkValue $f5)</td></tr>" >> $output 
done < "$input"
echo "</table></body></html>" >> $output