package rtTask;

import RtMgrpackage.RtMgr;
import Test.TestWithLibWithIncrGC;

public class RtTask extends Thread {
	int period;
	int deadline;

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public RtTask(int period, int deadline) {
		this.period = period;
		this.deadline = deadline;
	}

	@Override
	public String toString() {
		return "RtTask [period=" + period + ", deadline=" + deadline + "]";
	}

	public void run() {

		/******************************************************************************/
		int uniteTime = 1;
		long AbsolutBegin = RtMgr.getClockTime(uniteTime);
		/******************************************************************************/

		int iteration = 10;
		long periodForEachThread;

		long executionTime;
		periodForEachThread = (long) getPeriod();

		int compt = 0;
		int it = 0;

		long begin = RtMgr.getClockTime(uniteTime);
		long end;
		while (iteration > 0) {
			System.out.println("Iteration " + iteration + " of thread " + this.getId());
			begin = RtMgr.getClockTime(uniteTime);
			long next = RtMgr.getAddOfTime(begin, periodForEachThread);

			/**
			 * put here your code execution
			 */
			new TestWithLibWithIncrGC();

			end = RtMgr.getClockTime(uniteTime);
			end = RtMgr.getSubOfTime(end, AbsolutBegin);

			executionTime = RtMgr.getSubOfTime(end, begin);

			RtMgr.endOfInstance(next);

			if (RtMgr.getComparOfDL((long) periodForEachThread, executionTime) == -1) {
				compt++;
			}

			it++;
			iteration--;

		}
		System.out.println("Thread who's Stranded deadline = " + compt + " in " + it + " iteration");
	}

}
