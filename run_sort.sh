#!/bin/bash

if [ -z "$1" ]; then
  echo "Error: Please specify a sorting algorithm (insertion/merge/quick)"
  exit 1
fi

if [ -z "$2" ]; then
  echo "Error: Please specify a filename"
  echo "Eg: run_sort.sh merge file_name"
  exit 1
fi

# read the contents of the input file into a variable
#input_data=$(cat "$2")

# run the java program with the input data as arguments depending on the selected sorting algorithm
if [ "$1" == "insertion" ]; then
  java MainClass 0 $2
elif [ "$1" == "merge" ]; then
  java MainClass 1 $2
elif [ "$1" == "quick" ]; then
  java MainClass 2 $2
else
  echo "Error: Invalid sorting algorithm (insertion/merge/quick)"
  exit 1
fi