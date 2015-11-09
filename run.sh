#!/bin/bash
export CLASSPATH=hazelcast-all-3.5.2.jar:target/classes/;
echo $CLASSPATH;
java com.hazlecast.console.ConsoleApp