package RtThread;

public class RtJNI {

	static {
		System.loadLibrary("JNITest");
	}

	/**
	 * It returns the pthread id of the current thread. It never fails.
	 * 
	 * @return
	 */
	public static native synchronized long getPthreadSelf();

	/**
	 * It returns the scheduling policy of the speficied thread id. If the
	 * thread id does not exist, it returns an error (-1)
	 * 
	 * @param pthreadId
	 * @return
	 */
	public static native synchronized int getThreadPolicy(long pthreadId);

	/**
	 * It returns the scheduling priority of specified thread id. If the thread
	 * id does not exist, it returns an error (-1)
	 * 
	 * @param pthreadId
	 * @return
	 */

	public static native synchronized int getThreadPriority(long pthreadId);

	/**
	 * It returns id of core where the specified thread id are launched. If the thread
	 * id does not exist, it returns an error (-1)
	 * 
	 * @param pthreadId
	 * @return
	 */

	public static native synchronized int getThreadAffinity(long pthreadId);

	/**
	 * It set parameters scheduling of the specified thread id. If the thread
	 * id does not exist, it returns an error (-1)
	 * 
	 * @param pthreadId
	 * @return
	 */
	
	public static native synchronized int setThreadParameters(long pthreadId, int prio, int policy, int aff);

}
