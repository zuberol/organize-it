#!/usr/bin/env bash

# NODE = PROJECT = TASK = SUBTASK

#Exit codes
readonly INVALID_ARGUMENT=128
readonly SOMETHING_WENT_WRONG=1

# Parse options
NAME=null
DESCRIPTION=null
TASK_PARENT_PATH=null
[[ ! -d $ORGANIZE_IT_PROJECTS ]] && echo 'INVALID_ARGUMENT - projects dir not found' && exit $INVALID_ARGUMENT;
while getopts ":n:p:d:" arg; do
	[[ $OPTARG =~ ^- ]] && echo "Argument \"${arg}\" can't start with: \"-\". Skipping..." && exit $INVALID_ARGUMENT;
  case $arg in
    n) NAME=$OPTARG; LEGAL_CHARS=${NAME//[^a-zA-Z0-9]/_}; TASK_FILE_CAPTION=${LEGAL_CHARS:0:50}; ;;
		d) DESCRIPTION=$OPTARG;;
		p) TASK_PARENT_PATH=${ORGANIZE_IT_PROJECTS}/${OPTARG/#\//};;
		?) echo "Argument was empty. Skipping..." && exit $INVALID_ARGUMENT; ;;
	esac
done
[[ $NAME == null || $DESCRIPTION == null || $TASK_PARENT_PATH == null ]] && echo 'Empty argument. Skipping' && exit $INVALID_ARGUMENT;
##


# Create task
mkdir -p $TASK_PARENT_PATH/$TASK_FILE_CAPTION || exit $SOMETHING_WENT_WRONG;
readonly TASK_DESCRIPTION_FILE=$TASK_PARENT_PATH/$TASK_FILE_CAPTION/description 
touch $TASK_DESCRIPTION_FILE
echo -e "NAME=$NAME\n"\
"DESCRIPTION=$DESCRIPTION"\
>> $TASK_DESCRIPTION_FILE

git -C $ORGANIZE_IT_PROJECTS add "${TASK_DESCRIPTION_FILE}" && \
git -C $ORGANIZE_IT_PROJECTS commit -m "$TASK_DESCRIPTION_FILE" && \
git -C $ORGANIZE_IT_PROJECTS push || echo 'Task origin push failed'
echo Task created! && tree -d $ORGANIZE_IT_PROJECTS && exit 0;
