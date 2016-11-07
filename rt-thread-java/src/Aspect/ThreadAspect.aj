package Aspect;

import RtMgrpackage.RtMgr;
import RtThread.RtJNI;
import ThreadTest.ThreadTest;

public aspect ThreadAspect {

	/**
	 * set thread parameters to all rt_thread before his start execution
	 */

	pointcut ThreadTarget() : execution(void Runnable+.run());

	before() : ThreadTarget() {
		ThreadTest me = (ThreadTest) Thread.currentThread();

		/**
		 * if thread are contained in rt_thread_map than we can change him to
		 * rt_thread
		 */

		if (RtMgr.isManaged(me)) {
			long pthreadId = RtJNI.getPthreadSelf();
			int priority = RtMgr.getParam(me).getPriority();
			int policy = RtMgr.getParam(me).getPolicy();
			int affinity = RtMgr.getParam(me).getAffinity();
			RtMgr.getParam(me).setStarted(true);
			RtMgr.getParam(me).setPthreadId(pthreadId);

			RtMgr.getParam(me).setPid(RtMgr.getPidCurrentThread());
			RtJNI.setThreadParameters(pthreadId, priority, policy, affinity);
			System.out.println(
					"Thread: " + me + " " + RtMgr.getParam(me).toString() + " parameters set correctly");

		} else {
			System.out.println("Thread: " + Thread.currentThread() + ": is not real-time");
		}
	}

	/**
	 * reset thread parameters after its execution
	 */
	after() : ThreadTarget() {

		Thread me = Thread.currentThread();

		/**
		 * after his execution, we reset its real-time parameters
		 */

		if (RtMgr.isManaged(me)) {
			RtMgr.getParam(me).setStarted(false);
			/*RtMgr.getParam(me).setPthreadId(-1);
			RtMgr.getParam(me).setPid(-1);*/
			RtMgr.getParam(me).setFinished(true);
			System.out.println("After Thread: " + me + " " + RtMgr.getParam(me).toString());

		}

	}

}
