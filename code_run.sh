#!/bin/bash

./gradlew assemble

./gradlew check

echo "Hello world"
echo "This is first test to run to check"

cd build/libs

java -jar TravisTesting.jar

