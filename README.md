# Rt_Thread for JAVA Applications

- Authors: Y. Sidlakhdar & G. Lipari

This project provides a library for Real-Time programming in Java. It
is an interface towards POSIX threads in Linux. For the moment it
is not portable to other operating systems, it can only be used on Linux.

The library provides a JNI interface to POSIX threads, and it uses
Aspect Oriented Programming to implement hooks to Java Thread class
methods, so that the use of this library is transparent to the JVM. 
	
# Compile and Install

## Prerequisites

To compile the library you need
- Eclipse IDE

- AspectJ. You can download the plug-in for your version of Eclipse from here:
http://www.eclipse.org/ajdt/downloads/index.php

- You have to download aspectj.jar from here : http://www.java2s.com/Code/Jar/a/Downloadaspectjweaverjar.html, and include it in the Java build Path of the project : test-rt-lib/

- (Optional) CDT for compiling the JNI library 

## Compilation

* Let us first compile the JNI code. You will find it in directory
`rt-thread-c/src`. You can compile it with CDT, _or_ you can use
the instructions below:

	* Open a terminal in that directory

	* Edit the `Makefile`, and change the value of variable `JDK_PATH` to
	   point to the place where your JDK is installed.

	* run `make`
	
		* Then, to compile the library, open Eclipse IDE and import a new
		project with root directory `rt-thread-java`. Build should be automatic

## Executing the tests from the command line

To execute the tests from the terminal, execute the script `test.sh` in
directory `test-rt-lib` as superuser. 

# Usage

With our library, you can create java's thread with real time
scheduling policy, change their native priority, affect each thread to
a specific core of CPU.

Java directory contains

* Aspect package

 	* It permits to set priority after starting the thread. It intercepts
	the `run()` method of the Thread class, and invokes the JNI method
   	to change the thread priority.

	* It checks the correctness of user's parameters : priority(1-99),
	affinity(number of CPU, in our case 1-0), policy(0 for `SCHED_RR`,
   	and 1 for `SCHED_FIFO`).
	
	* It resets all the parameters and then set variable finished to true
   	when the Threads completes by intercepting the completion of the
   	`run()` method.

* RtMgrPackage

	* Parameters.java contains the thread parameters : `pthread_id`,
	`affinity`, `sched_policy`, `priority`.

	* RtMgr.java manages the java thread with a hash map that links a
	java Thread with its real-time parameters. In addition, it can start
	all threads.  It is possible to add a new thread to the map, affect
	the parameters to a specific thread, test if a thread has real-time
	parameters or not.

	* RtThread.java contains the declaration of all native methodes.

	* Scenario: differents scenario testing.


A simple example of code (Main function) to create two periodic threads:
```java
		// creating thread
		ThreadTest t1 = new ThreadTest(1)

		// initialition of scheduling parameters 	
		Parameters p1 = new Parameters(int policy,int priority, int affinity);
		
		// Affect parameters p1 to Thread t1
		RtMgr.setSchedThreadParams(t1, p1);

		// Start rt_thread
		RtMgr.startAllThreads()
```
# Documentation

   The documentation with Javadoc can be found in doc/


----

		
		    





 
