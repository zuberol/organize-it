#!/usr/bin/env bash

_newTask_complete(){

	local CDPATH=$ORGANIZE_IT_PROJECTS;
	_cd;

}


complete -F _newTask_complete -o nospace newTask.sh
complete -F _newTask_complete -o nospace org
