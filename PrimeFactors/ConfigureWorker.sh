#!/bin/bash

javac FindPrimes.java
javac WorkServer.java

while :
do
	echo "CTRL+C to stop"
	echo Launching Work Server
	java WorkServer 3131
done

