#!/bin/bash
#Author: Rohtash Lakra
#clear
set -e
USERNAME="$(whoami)"
PROJECT_PATH="$(pwd)"
USER_HOME="/Users/${USERNAME}"
#echo "USERNAME: ${USERNAME}, USER_HOME: ${USER_HOME}, PROJECT_PATH: ${PROJECT_PATH}"
INPUT="${PROJECT_PATH}/micro-services.txt"
SERVICE_NAME=""
SERVICE_PATH=""
cd "${PROJECT_PATH}"
while IFS= read -r line
do
	# Ignore comments
	if [ ! -z "$line" ] && [ "${line:0:1}" != "#" ]; then
		echo
		echo "Building [${line}] ..."
		echo
		SERVICE_NAME="${line%.*}"
		SERVICE_NAME="${SERVICE_NAME##*/}"
		SERVICE_PATH="${PROJECT_PATH}/${SERVICE_NAME}"
		echo "${SERVICE_PATH}"
		echo
		if [ -d "$SERVICE_PATH" ]; then
		  cd "${SERVICE_PATH}"
		  mvn clean package
			cd "${SERVICES_PATH}"
		fi
		echo
	fi
done < "$INPUT"
