package scenario;

import RtMgrpackage.Parameters;
import RtMgrpackage.RtMgr;
import ThreadTest.ThreadTest;

public class ScenarioWithDiffPrio {

	public ScenarioWithDiffPrio() {

		/**
		 * declaration of threads
		 */

		ThreadTest t1 = new ThreadTest(1);
		ThreadTest t2 = new ThreadTest(2);
		ThreadTest t3 = new ThreadTest(3);

		/**
		 * 
		 * get pthread_self of main thread
		 * 
		 */

		System.out.println(
				"main pthread_self " + RtMgr.getPthreadOfCurrentThread() + " and pid =" + RtMgr.getPidCurrentThread());

		/**
		 * 
		 * set thread parameters HIGH, MEDIUM, LOW
		 * 
		 */

		Parameters p1 = new Parameters(0, 2, 0);
		Parameters p2 = new Parameters(0, 40, 0);
		Parameters p3 = new Parameters(0, 99, 0);

		RtMgr.setSchedThreadParams(t1, p1);
		RtMgr.setSchedThreadParams(t2, p2);
		RtMgr.setSchedThreadParams(t3, p3);

		/**
		 * start all rt_threads
		 */

		RtMgr.startAllThreads();

		/**
		 * set other parameters for existing threads
		 * 
		 */
		Parameters p4 = new Parameters(0, 1, 0);
		Parameters p5 = new Parameters(0, 40, 0);
		Parameters p6 = new Parameters(0, 99, 0);

		RtMgr.setSchedThreadParams(t1, p4);
		RtMgr.setSchedThreadParams(t2, p5);
		RtMgr.setSchedThreadParams(t3, p6);

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
