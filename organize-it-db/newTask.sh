#!/usr/bin/env bash

# NODE = PROJECT = TASK = SUBTASK

#Exit codes
readonly INVALID_ARGUMENT=128

# Parse options
VERBOSE='false'
NAME=null
NODE_TYPE=TASK
DESCRIPTION=""
PARENT_PATH=~/Desktop

while getopts "n:p:d:t:" arg; do
  case $arg in
    n) NAME=$OPTARG;;
		t) NODE_TYPE=PROJECT;;
		d) DESCRIPTION=$OPTARG;;
		p) PARENT_PATH=$OPTARG;;
  esac
done

if [[ -e $PARENT_PATH ]]; then 
echo 'new task added'
NEW_TASK_FILE=$PARENT_PATH/$(ls $PARENT_PATH | wc -l | xargs)

echo NEW PATh $NEW_TASK_FILE

cat << EOF >> $NEW_TASK_FILE
note: ${NAME}
isProject: ${NODE_TYPE}
description: ${DESCRIPTION}
EOF

fi

