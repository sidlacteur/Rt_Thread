package rtTask;

import RtMgrpackage.RtMgr;
import Test.Test;

public class RtTask extends Thread {
	int period;
	int deadline;
	String resp;
	String exec;
	int iteration;

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

	public RtTask(int period, int deadline, String resp, String exec, int iter) {
		this.period = period;
		this.deadline = deadline;
		this.resp = resp;
		this.exec = exec;
		this.iteration = iter;
	}

	@Override
	public String toString() {
		return "RtTask [period=" + period + ", deadline=" + deadline + "]";
	}

	public void run() {

		/******************************************************************************/
		int uniteTime = 1;
		// long AbsolutBegin = PeriodJNI.getExactClockTime(uniteTime);
		RtMgr.TimerStart();
		/******************************************************************************/

		long periodForEachThread;

		long executionTime;
		periodForEachThread = (long) getPeriod();

		int compt = 0;
		int it = 0;

		long begin = RtMgr.getExactClockTime(uniteTime);
		long end;
		while (this.iteration > 0) {

			begin = RtMgr.getExactClockTime(uniteTime);
			long next = begin + periodForEachThread;

			/**
			 * put here your code execution
			 */

			new Test(resp + iteration, exec + iteration);

			end = RtMgr.getExactClockTime(uniteTime);

			executionTime = end - begin;

			RtMgr.endOfInstance(next, uniteTime);

			if (periodForEachThread < executionTime) {
				compt++;
			}

			it++;
			iteration--;

		}
		System.out.println("Thread who's Stranded deadline = " + compt + " in " + it + " iteration");
	}
}
