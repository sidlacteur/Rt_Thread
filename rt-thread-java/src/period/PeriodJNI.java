package period;

public class PeriodJNI {

	/******************************************
	 * Lib Call
	 *********************************************************/

	static {
		System.loadLibrary("TimeJNI");
	}

	/***** Native Method *************/

	public static native synchronized long comDeadline(long i, long k);

	public static native synchronized long getClockTime(int time);

	public static native synchronized void endInstance(long milisec);

	public static native synchronized long timeadd(long tspec, long getPeriod);

	public static native synchronized long timesub(long tspec, long getPeriod);

}
