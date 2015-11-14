#!/bin/bash
export JAVA_HOME=$(/usr/libexec/java_home)
mvn package
java -jar target/POD_2015_Q2-0.0.1-SNAPSHOT-jar-with-dependencies.jar