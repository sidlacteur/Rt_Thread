# TITLE: 
	rt_Thread for JAVA Application

# Author: 
	Y.Sidlakhdar & G.Lipari

# Compile and Install

	_ run Makefile to generate JNI lib.	


## Eclipse

You need to add : 
		 CDT plug-in for C developement.
		 AspectJ plug-in for Aspect programation.	

	than run main.java with arguments: -Djava.library.path="${$WORKSPACE_PATH:/RtThread/src/}".


## Command line

_ add the containing directory to LD_LIBRARY_PATH before launching the application

       or run this command: export LD_LIBRARY_PATH = $LD_LIBRARY_PATH:/some/pathOfContainingDirectory

		Use java -XshowSettings:properties to show the java.library.path (and others) value.


# Usage

how to use the library

With our library, you can create java's thread with real time scheduling policy, change their native priorite, affect each thread to specific core of CPU. 

Java directory contain :

	_ package :  _ Aspect : - set priority before run rt_thread and that set variable started to true.
				- test correctness of user's parameters : priority(1-99), affinity(number of CPU, in our case 1-0), policy(0 for SCHED_RR, and 1 for 										SCHED_FIFO).
				- reset all the parameters and than set variable finished to true.
		     _ RtMgrPackage : - Parameters.java contains rt_thread parameters : pthread_id, affinity, SCHEd_policy, priority.
				      - RtMgr.java it manage rt_thread, for example : get/set map that contain rt_thread with them Parameters, start all rt_threads, add new rt_thread to the map, affect 											      Parameters to specific rt_thread, test if thread is rt_thread or not.
		     _ RtThread: RtThread.java contains declarationof all native methodes. 

		     _ Scenario: differents scenario testing.


simple example of code (Main function) to create two periodic threads

		// creating thread
		ThreadTest t1 = new ThreadTest(1)

		// initialition of scheduling parameters 	
		Parameters p1 = new Parameters(int policy,int priority, int affinity);
		
		// Affect parameters p1 to Thread t1
		RtMgr.setSchedThreadParams(t1, p1);

		// Start rt_thread
		RtMgr.startAllThreads()
# Doc

   The documentation with Javadoc can be found in doc/


----

		
		    





 
