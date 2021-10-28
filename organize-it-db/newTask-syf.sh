#!/usr/bin/env bash

# NODE = PROJECT = TASK = SUBTASK

#Exit codes
readonly INVALID_ARGUMENT=128

# Parse options
VERBOSE='false'
NAME=null



echo ':p:nd'
while getopts ':p:nd' flag; do
	echo -e "flag $flag ${flag} $OPTARG \n"
done

getopts 'p:nd' flag; echo $OPTARG

echo 'p:nd'
while getopts 'p:nd' flag; do
	echo "flag $flag ${flag} $OPTARG"
done

exit;

while getopts 'p:nd' flag; do
#	echo flag $flag ${flag} 
#	echo 'flag $flag ${flag}'

echo "flag $flag ${flag} $OPTARG"



#	if [[ $flag =~ ^-.* ]]; then
#		echo "Flag \"${flag}\" was empty. Node creation failed.";
#		echo flag $flag ${flag}
# 		exit; 
#	fi
#echo 'iteration'
#  case $flag in
#    p) echo p case fired;  ;;
#    n) echo n case fired; ;;
#		:) echo 'necessery arg wasnt set'; ;;
#		?) echo '?' ;;
#		'?') echo '???' ;;
#  esac
done
readonly PARENT_PATH
readonly NODE_NAME 
#echo "Will it be printed if PARENT_PATH NOT SPECIFIED? --> ERROR"
echo 'Done'


