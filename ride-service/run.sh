#!/bin/bash
set -euo pipefail
IFS=$'\n\t'

./mvnw clean install
java -jar target/ride-service-0.0.1-SNAPSHOT.jar
