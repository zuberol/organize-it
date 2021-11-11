#!/usr/bin/env bash

[ ! -f ~/.bash_aliases ] && touch ~/.bash_aliases
[[ ! $(grep newTask.sh ~/.bash_aliases) ]] && echo 'alias org="newTask.sh"' >> ~/.bash_aliases
. ~/.bash_aliases


source newTask_completion_init.sh
