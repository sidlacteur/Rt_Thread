#!/bin/bash
r=$(pwd)
FILES=*.txt
for f in $FILES
do

  Rscript --vanilla /usr/bin/Yassine1.R $r"/"$f

done
