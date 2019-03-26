#!/bin/bash

set -x

docker pull selenium/hub

docker pull selenium/node-chrome

docker ps -a

docker run -d -p 4446:4444  --name sh  selenium/hub:3.141.59-lithium

docker run -d -P --link sh:hub  --name chrome -P selenium/node-chrome:3.141.59-lithium

docker ps -a

docker logs sh



bash code_run.sh

google-chrome-stable --headless --disable-gpu --remote-debugging-port=4444 http://localhost &
