#!/bin/bash

set -x

docker pull selenium/hub

docker pull selenium/node-chrome

docker ps -a

docker run -d -p 127.0.0.1:127.0.0.1:127.0.0.1:127.0.0.1:127.0.0.1:127.0.0.1:127.0.0.1:127.0.0.1:127.0.0.1:127.0.0.1:127.0.0.1:127.0.0.1:4444:4444 --net grid  --name sh -e START_XVFB=false selenium/hub:3.141.59-lithium

bash wait-for-grid.sh

docker run -d -P --link sh:hub -v /dev/shm:/dev/shm  --name chrome -P selenium/node-chrome:3.141.59-lithium

docker ps -a

docker logs sh

#cd build/libs

docker cp /build/libs/TravisTesting.jar sh:/opt/bin/entry_point/TravisTesting.jar

java -jar TravisTesting.jar



#bash code_run.sh

google-chrome-stable --headless --disable-gpu --remote-debugging-port=4444 http://localhost &
