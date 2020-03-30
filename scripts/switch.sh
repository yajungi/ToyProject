#!/usr/bin/env bash

ABS_PATH=$(readlink -f $0)
ABS_DIR=$(dirname $ABS_PATH)

source ${ABS_DIR}/profile.sh

function switch_proxy() {
  IDLE_PORT=$(find_idle_port)

  echo "> 전환할 Port: $IDLE_PORT"
  echo "> Port 전환"
  echo "set \$service_url http://localhost:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc

  echo "> 엔진엑스 Reload"
  sudo service nginx reload
}