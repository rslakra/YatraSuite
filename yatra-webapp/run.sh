#!/bin/bash
set -euo pipefail
IFS=$'\n\t'

./mvnw clean install
java -jar target/web-app-0.0.1-SNAPSHOT.jar
