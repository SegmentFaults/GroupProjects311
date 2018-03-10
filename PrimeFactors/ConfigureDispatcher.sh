#!/bin/bash

javac Dispatcher.java

while :
do
	echo "CTRL+C to stop"
	echo Launching Dispatcher
	java Dispatcher 3131 172.17.31.234 172.17.31.141 172.17.31.162 172.17.31.152 3131
done