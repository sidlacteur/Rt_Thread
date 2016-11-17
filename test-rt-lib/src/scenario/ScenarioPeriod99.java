package scenario;

import RtMgrpackage.Parameters;
import RtMgrpackage.RtMgr;
import rtTask.RtTask;

public class ScenarioPeriod99 {

	public ScenarioPeriod99() {

		/**
		 * declaration of threads
		 */

		// RtTask rt = new RtTask(100, 50);

		RtTask rt = new RtTask(100, 50);
		/**
		 * get pthread_self of main thread
		 */

		System.out.println("main pthread_self " + RtMgr.getPthreadOfCurrentThread());

		/**
		 * set thread parameters
		 */

		Parameters p1 = new Parameters(1, 9, 1);

		RtMgr.setSchedThreadParams(rt, p1);

		/**
		 * start all rt_threads
		 */

		RtMgr.startAllThreads();

		/**
		 * set other parameters for existing threads
		 * 
		 */
		Parameters p2 = new Parameters(1, 99, 1);

		RtMgr.setSchedThreadParams(rt, p2);

		/**
		 * join all threads to main thread
		 */

		try {
			rt.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
