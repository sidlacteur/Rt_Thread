#!/bin/bash

sudo gcc -fPIC -o  libJNITest.so -shared -I/usr/jdk/include -I/usr/jdk/include/linux \
RtThread.c  -pthread

