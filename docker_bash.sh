#!/bin/bash

set -x

docker pull selenium/hub:3.141.59-lithium

docker pull selenium/node-chrome:3.141.59-lithium

docker ps -a

docker run -d -p 127.0.0.1:4444:4444 -e JAVA_OPTS=-Xmx512m   --name sh  selenium/hub:3.141.59-lithium

docker run -d -P --link sh:hub -v /dev/shm:/dev/shm --name chrome -P selenium/node-chrome-debug:3.141.59-lithium

docker ps -a

docker logs sh

