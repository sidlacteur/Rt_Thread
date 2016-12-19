package period;

public class PeriodIntger {
	/******************************************
	 * Lib Call
	 *********************************************************/

	static {
		System.loadLibrary("TimeIntJNI");
	}

	/***** Native Method *************/

	public static native synchronized Integer getExactClockTime(int time);

	public static native synchronized Integer getClockTime(int time);

	public static native synchronized void endInstance(long milisec, int unit);

}
