package RtMgrpackage;

import java.util.HashMap;

import RtThread.RtJNI;

public class RtMgr {
	/**
	 * RtMgr : Manager of rt_thread
	 * 
	 * threadSet : set of rt_thread with them rt_thread
	 * 
	 */
	private static HashMap<Thread, Parameters> threadSet = new HashMap<Thread, Parameters>();

	/**
	 * Returns the scheduling parameters for thread t, or null if the thread is
	 * not real-time.
	 * 
	 * @param t
	 * @return
	 */
	public static Parameters getParam(Thread t) {
		return threadSet.get(t);
	}

	/**
	 * Return the pthread id of the current thread as a long.
	 * 
	 * @return
	 */
	public static long getPthreadOfCurrentThread() {
		return RtJNI.getPthreadSelf();
	}

	/**
	 * Returns the process ID of the current thread, as a long
	 * 
	 * @return
	 */
	public static long getPidCurrentThread() {
		return RtJNI.getPid();
	}

	public static void setSchedThreadParams(Thread t, Parameters param) {
		/**
		 * if t is in hash map, and has already started, call JNI to change the
		 * parameters
		 */
		if (isRealTime(t)) {

			Parameters p = getParam(t);
			System.out.println(p.toString());

			/* TODO: test input parameters */

			long pthreadId = getParam(t).getPthreadId();
			p.setAffinity(param.affinity);
			p.setPolicy(param.policy);
			p.setPriority(param.priority);

			RtJNI.setThreadParameters(pthreadId, param.priority, param.policy, param.affinity);
		}
		/**
		 * else, just put the thread parameters into the hash map
		 */
		else {
			System.out.println("Thread: " + Thread.currentThread() + ": is not real-time");

			threadSet.put(t, param);
			System.out.println("Thread added to the real-time map");
		}
	}

	/**
	 * TODO: (LP) Starting all threads at the same time is helpful for the
	 * moment, but at some point we should allow a specific group of threads to
	 * be activated together
	 */
	public static void startAllThreads() {
		for (Thread Threads : threadSet.keySet()) {
			Threads.start();
		}
	}

	/**
	 * Tells us if a certain thread has real-time parameters or not
	 * 
	 * @param t
	 * @return
	 */
	public synchronized static boolean isRealTime(Thread t) {
		if (!threadSet.containsKey(t) || !getParam(t).started) {
			return false;
		} else {
			return true;
		}
	}

	public synchronized static boolean isManaged(Thread t) {
		return threadSet.containsKey(t);
	}

	/**
	 *  Searches a thread with the required Pid
	 */

	public static thread getThreadFromPid(long pid) {
		for (Thread Threads : threadSet.keySet()) {
			if (getParam(Threads).getPid() == pid)
				return Threads;
			else
				return 0;

		}
	}

}