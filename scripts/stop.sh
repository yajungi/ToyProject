#!/usr/bin/env bash

ABS_PATH=$(readlink -f $0)
ABS_DIR=$(dirname $ABS_PATH)

source ${ABS_DIR}/profile.sh

IDLE_PORT=$(find_idle_port)

echo "> $IDLE_PORT에서 구동 중인 어플리케이션 pid 확인"
IDLE_PID=$(lsof -ti tcp:${IDLE_PORT})

if [ -z ${IDLE_PID}]
then
  echo "> 현재 구동 중인 어플리케이션이 없습니다."
else
  echo "> kill -15 $IDLE_PID"
  kill -15 ${IDLE_PID}
  sleep 5
fi