#!/bin/bash
r=$(pwd)
FILES=*.txt
for f in $FILES
do

  Rscript --vanilla /usr/bin/plot.R $r"/"$f

done
