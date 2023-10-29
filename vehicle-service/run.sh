#!/bin/bash
set -euo pipefail
IFS=$'\n\t'

./mvnw clean install
java -jar target/vehicle-service-0.0.1-SNAPSHOT.jar
