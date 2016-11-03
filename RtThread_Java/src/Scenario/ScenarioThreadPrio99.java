package Scenario;

import RtMgrpackage.Parameters;
import RtMgrpackage.RtMgr;
import ThreadTest.ThreadTest;

public class ScenarioThreadPrio99 {

	public ScenarioThreadPrio99() {

		/**
		 * declaration of threads
		 */

		ThreadTest t1 = new ThreadTest(1);

		/**
		 * get pthread_self of main thread
		 */

		System.out.println("main pthread_self " + RtMgr.getPthreadOfCurrentThread());

		/**
		 * set thread parameters
		 */

		Parameters p1 = new Parameters(1, 9, 1);

		RtMgr.setSchedThreadParams(t1, p1);

		/**
		 * start all rt_threads
		 */

		RtMgr.startAllThreads();

		/**
		 * set other parameters for existing threads
		 * 
		 */
		Parameters p2 = new Parameters(1, 99, 1);

		RtMgr.setSchedThreadParams(t1, p2);

		/**
		 * join all threads to main thread
		 */

		try {
			t1.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
