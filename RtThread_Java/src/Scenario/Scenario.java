package Scenario;

import RtMgrpackage.Parameters;
import RtMgrpackage.RtMgr;
import ThreadTest.ThreadTest;

public class Scenario {

	public Scenario() {

		/**
		 * declaration of threads
		 */

		ThreadTest t1 = new ThreadTest(1);
		ThreadTest t2 = new ThreadTest(2);
		ThreadTest t3 = new ThreadTest(3);

		/**
		 * get pthread_self of main thread
		 */

		System.out.println("main pthread_self " + RtMgr.getPthreadOfCurrentThread());

		/**
		 * set thread parameters
		 */

		Parameters p1 = new Parameters(1, 10, 1);
		Parameters p2 = new Parameters(0, 20, 0);

		RtMgr.setSchedThreadParams(t1, p1);
		RtMgr.setSchedThreadParams(t2, p2);

		/**
		 * start all rt_threads
		 */

		RtMgr.startAllThreads();

		/**
		 * start normal thread
		 */

		t3.start();

		/**
		 * set other parameters for existing threads
		 * 
		 */
		Parameters p4 = new Parameters(1, 20, 1);
		Parameters p5 = new Parameters(0, 40, 0);

		RtMgr.setSchedThreadParams(t1, p4);
		RtMgr.setSchedThreadParams(t2, p5);

		/**
		 * join all threads to main thread
		 */

		try {
			t1.join();
			t2.join();
			t3.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
