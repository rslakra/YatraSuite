#!/bin/bash
#Author: Rohtash Lakra
set -euo pipefail
IFS=$'\n\t'
#docker-compose up -d
#docker-compose -f kafka/docker-compose.yml run backup_db
docker-compose up -d
