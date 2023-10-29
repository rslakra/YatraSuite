#!/bin/bash
#Author: Rohtash Lakra
set -euo pipefail
IFS=$'\n\t'

USERNAME="$(whoami)"
USER_HOME="/Users/${USERNAME}"
WORKSPACE_PATH="${USER_HOME}/Workspaces"
PROJECT_PATH="${WORKSPACE_PATH}/SpringServices"
SERVICES_PATH="${PROJECT_PATH}/Microservice"
INPUT="${SERVICES_PATH}/micro-services.txt"
SERVICE_NAME=""
SERVICE_PATH=""
cd "${SERVICES_PATH}"

function help {
    echo "This command builds the micro-services."
    echo
    echo "USAGE: runServices.sh service-name"
    echo
    echo "<service-name>  The name of service to build."
    echo
}

EXERCISE=${1:-}

if [ -z $EXERCISE ]
then
    help
    exit 0
fi

SUB_FOLDERS=(
    "user-service/"
    "vehicle-service/"
    "ride-service/"
    "web-app/"
)

SOLUTION_FOLDER=../solutions

EXERCISE_FOLDER=$(find $SOLUTION_FOLDER -maxdepth 1 -type d -name "*$EXERCISE*" -print -quit)

if [ -z $EXERCISE_FOLDER ]
then
    echo "Unable to find a solution for the requested exercise: $EXERCISE"
    help
    exit 0
fi

for folder in ${SUB_FOLDERS[@]};
do
    SOLUTION=$EXERCISE_FOLDER/$folder
    EXERCISE=./$folder

    echo "Pulling Solution from $SOLUTION to $EXERCISE"

    if [ ! -d $SOLUTION ]
    then
        echo "WARNING: Unable to find tests for in the requested folder: $SOLUTION...skipping"
    fi

#    rm -rf $EXERCISE
#    mkdir $EXERCISE
#    cp -rp $SOLUTION/. $EXERCISE/
done