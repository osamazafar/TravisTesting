#!/bin/bash

set -x

docker pull selenium/hub

docker pull selenium/node-chrome

docker ps -a

docker run -d -p 127.0.0.1:4444:4444  --name sh  selenium/hub

docker images

docker run -d -P  --link sh:hub -v /dev/shm:/dev/shm  --name chrome  selenium/node-chrome

sleep 5

docker ps -a

docker logs sh









