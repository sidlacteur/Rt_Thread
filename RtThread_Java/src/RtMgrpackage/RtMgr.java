package RtMgrpackage;

import java.util.HashMap;

import RtThread.RtJNI;
import ThreadTest.ThreadTest;

public class RtMgr {
	private static HashMap<ThreadTest, Parameters> threadSet = new HashMap<ThreadTest, Parameters>();

	public static Parameters getParam(ThreadTest t) {
		return threadSet.get(t);
	}
	
	

	public static HashMap<ThreadTest, Parameters> getThreadSet() {
		return threadSet;
	}



	public static void setThreadSet(HashMap<ThreadTest, Parameters> threadSet) {
		RtMgr.threadSet = threadSet;
	}



	public static long getPthreadOfCurrentThread() {
		return RtJNI.getPthreadSelf();
	}

	public static void setSchedThreadParams(ThreadTest t, Parameters param) {

		/*
		 * if t is in hash map, and has already started, call JNI to change the
		 * parameters
		 *
		 */

		if (isRealTime(t)) {

			Parameters p = getParam(t);
			System.out.println(p.toString());

			long pthreadId = getParam(t).getPthreadId();

			p.setAffinity(param.affinity);
			p.setPolicy(param.policy);
			p.setPriority(param.priority);

			RtJNI.setThreadParameters(pthreadId, param.priority, param.policy, param.affinity);

		} /*
			 * else, just put the threrad parameters into the hash map
			 */
		else {
			System.out.println("Thread: " + Thread.currentThread() + ": is not real-time");

			threadSet.put(t, param);
			System.out.println("Thread added to the real-time map");
		}

	}

	public static void startAllThreads() {

		for (Thread Threads : threadSet.keySet()) {
			Threads.start();
		}
	}

	public synchronized static boolean isRealTime(ThreadTest t) {
		if (!threadSet.containsKey(t) || !getParam(t).started) {
			return false;
		} else {
			return true;
		}
	}
}