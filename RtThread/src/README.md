
You need to add : 
		 CDT plug-in for C developement (if you work in eclipse).
		 AspectJ plug-in for Aspect programation.	

		


How it's work ? 

	_ run Makefile to generate JNI lib.

	_ add the containing directory to LD_LIBRARY_PATH before launching the application

		in eclipse  : -Djava.library.path="${$WORKSPACE_PATH:/RtThread/src/}".

        	or run this command:	export LD_LIBRARY_PATH = $LD_LIBRARY_PATH:/some/pathOfContainingDirectory

					Use java -XshowSettings:properties to show the java.library.path (and others) value.



Java directory :

	_ package :  _ Aspect : - set priority before run rt_thread and mark that rt_thread was started.
				- test correctness of user's parameters : priority(1-99), affinity(number of CPU, in our case 1-0), policy(0 for SCHED_RR, and 1 for 										SCHED_FIFO).
				- reset all the parameters and mark that rt_thread was finished.
		     _ RtMgrPackage : - Parameters.java contains rt_thread parameters : pthread_id, affinity, SCHEd_policy, priority.
				      - RtMgr.java it manage rt_thread, for example : get/set map that contain rt_thread with them Parameters, start all rt_threads, add new rt_thread to the map, affect 											      Parameters to specific rt_thread, test if thread is rt_thread or not.
		     _ RtThread: RtThread.java contains declarationof all native methodes. 

		     _ Scenario: differents scenario testing.
		    





 
