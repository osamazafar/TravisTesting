#!/bin/bash

export DISPLAY=:20
Xvfb :20 -screen 0 1366x768x16 &

./gradlew assemble

./gradlew check

echo "Hello world"
echo "This is first test to run to check"

cd build/libs

java -jar TravisTesting.jar

