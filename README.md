# POD_2015_Q2

Distributed Objects Programming 2015 Q2. Use of `hazelcast` for a movie querying distributed system based of IMDB database.

##Instructions

To run you'll need 2 terminals your pwd must be **at the same directory as this README**

###Compiling
This is the first and **most important** step. Use `./deploy.sh` for compiling the source code. Might need to `chmod +x run.sh` first.

 You'll need this for **both** server and client.

**Tips:** This script contains `$JAVA_HOME` set at the default location for a mac computer. If run form linux change the first line to the output of `which java`

####Dependencies
You must have **maven** installed in your computer.

###Server

Execute `./run.sh`, might need to `chmod +x run.sh` first.


###Client
Execute `./run_client.sh`, might need to `chmod +x run.sh` first.

This script will need to be set in the command line args and saved before every run. That is explained in its self-contained help.





