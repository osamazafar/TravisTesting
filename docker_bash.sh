#!/bin/bash

set -x

docker pull selenium/hub

docker pull selenium/node-chrome

docker ps -a

docker run -d -p 127.0.0.1:4446:4444  --name sh  selenium/hub:3.141.59-lithium

docker run -d -P --link sh:hub  --name chrome -P selenium/node-chrome:3.141.59-lithium

docker ps -a

docker logs sh

RUN apt-get update && apt-get install -y \
    software-properties-common \
    unzip \
    curl \
    xvfb 

RUN curl https://dl-ssl.google.com/linux/linux_signing_key.pub -o /tmp/google.pub \
    && cat /tmp/google.pub | apt-key add -; rm /tmp/google.pub \
    && echo 'deb http://dl.google.com/linux/chrome/deb/ stable main' > /etc/apt/sources.list.d/google.list \
    && mkdir -p /usr/share/desktop-directories \
    && apt-get -y update && apt-get install -y google-chrome-stable

bash code_run.sh

google-chrome-stable --headless --disable-gpu --remote-debugging-port=4444 http://localhost &
