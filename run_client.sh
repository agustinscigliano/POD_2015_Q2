#!/bin/bash
echo 'Remember to add command line args in here!'
export CLASSPATH=target/classes/:target/POD_2015_Q2-0.0.1-SNAPSHOT-jar-with-dependencies.jar
java -jar target/POD_2015_Q2-0.0.1-SNAPSHOT-jar-with-dependencies.jar query=1 N=5 path=res/imdb-40.json addresses=127.0.0.1