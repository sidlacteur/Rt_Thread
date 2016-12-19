package ThreadTest;

import RtMgrpackage.RtMgr;
import RtThread.RtJNI;
import Test.Test;

public class ThreadTest extends Thread {

	int index;
	String resp;
	String exec;

	public ThreadTest(int x, String respi, String execi) {
		super();
		index = x;
		resp = respi;
		exec = execi;

	}

	public void run() {

		/**
		 * put here your code to execute it.
		 */
		for (int i = 0; i < 10; i++) {
			new Test(resp + i, exec + i);
		}
		/**
		 * if curent thread is in the map, returns scheduling parameters
		 */

		if (RtMgr.isManaged(this)) {
			int prio = RtJNI.getThreadPriority(RtMgr.getParam(this).getPthreadId());
			long pid = RtMgr.getParam(this).getPid();

			int affinity = RtJNI.getThreadAffinity(RtMgr.getParam(this).getPthreadId());
			int policy = RtJNI.getThreadPolicy(RtMgr.getParam(this).getPthreadId());

			System.out.println("Thread :" + index + "   priority = " + prio + " affinity " + affinity + " policy "
					+ policy + "pid " + pid);
		}
		/**
		 * else just print Normal Thread with his Index
		 */
		else {
			System.out.println("Normal thread " + index);

		}

	}
}
