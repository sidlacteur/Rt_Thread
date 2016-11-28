package ThreadTest;

import RtMgrpackage.RtMgr;
import RtThread.RtJNI;
import Test.Test;
import Test.TestWithLibWithIncrGC;

public class ThreadTest extends Thread {

	int index;

	public ThreadTest(int x) {
		super();
		index = x;
	}

	public void run() {
		/*
		 * for (int i = 0; i < 10; i++) { Thread.sleep(1000);}
		 */

		/**
		 * put here your code to execute it.
		 */
		new Test("std-rt-k-1000-noint-resp","std-rt-k-1000-noint-exec");
		

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
