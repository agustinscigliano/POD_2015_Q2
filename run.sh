#!/bin/bash
export CLASSPATH=hazelcast-all-3.5.2.jar:target/classes/:gson-2.4.jar
java com.hazelcast.console.ConsoleApp
