#!/bin/bash

set -x

docker pull selenium/hub

docker pull selenium/node-chrome

docker ps -a

docker run -d -p 4444:4444  --name sh  selenium/hub

docker images

sleep 5

docker run -d  -p --link sh:hub -v /dev/shm:/dev/shm  --name chrome  selenium/node-chrome



docker ps -a

docker logs sh









