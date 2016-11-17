package rtTask;

import java.util.Comparator;

public class PeriodComparator implements Comparator<RtTask> {
	public int compare(RtTask thread1, RtTask thread2) {
		return thread1.getPeriod() - thread2.getPeriod();
	}

}
