package period;

public class PeriodJNI {

	/******************************************
	 * Lib Call
	 *********************************************************/

	static {
		System.loadLibrary("TimeJNI");
	}

	/***** Native Method *************/

	public static native synchronized long comDeadline(long i, long k, int unit);

	public static native synchronized long getExactClockTime(int time);
	
	public static native synchronized long getClockTime(int time);

	public static native synchronized void endInstance(long milisec,int unit);

	public static native synchronized void TimerStart();
	

}
