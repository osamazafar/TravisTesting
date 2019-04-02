#!/bin/bash

export DISPLAY=:20
Xvfb :20 -screen 0 1366x768x16 &

./gradlew assemble

./gradlew check

echo "Hello world"
echo "This is first test to run to check"

docker pull selenium/hub

docker run -d -p 127.0.0.1:4444:4444 --name sh -P selenium/hub

docker ps -a

docker pull selenium/node-chrome

docker run -d -P --link sh:hub --name chrome -P selenium/node-chrome

echo  "Checking process running"
docker ps -a

echo "Logs of hub"

docker logs sh

cd build/libs

java -jar TravisTesting.jar

