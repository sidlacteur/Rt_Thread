package Scenario;

import RtMgrpackage.Parameters;
import RtMgrpackage.RtMgr;
import ThreadTest.ThreadTest;

public class Scenario_ParamError {

	public Scenario_ParamError() {

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
		 * set other parameters for existing threads to generate runtime
		 * exception
		 */
//
//
		p1.setAffinity(0);
		p1.setPriority(26);
		p1.setPolicy(0);

		p2.setAffinity(1);
		p2.setPriority(56);
		p2.setPolicy(1);

		RtMgr.setSchedThreadParams(t1, p1);
		RtMgr.setSchedThreadParams(t2, p2);

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
