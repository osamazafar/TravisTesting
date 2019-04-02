#!/bin/bash

set -x

docker pull selenium/hub

docker pull selenium/node-chrome

docker ps -a

docker run -d -p 127.0.0.1:4444:4444  --name sh -e selenium/hub:3.141.59-lithium

docker images

docker run -d  --link sh:hub -v /dev/shm:/dev/shm  --name chrome  selenium/node-chrome:3.141.59-lithium

docker ps -a

docker logs sh

#cd build/libs

docker cp /build/libs/TravisTesting.jar sh:/opt/bin/entry_point/TravisTesting.jar

docker run -it sh /bin/bash

java -jar TravisTesting.jar


docker stop sh

docker stop chrome

#bash code_run.sh

google-chrome-stable --headless --disable-gpu --remote-debugging-port=4444 http://localhost &
