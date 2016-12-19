package Affichage;

public class task_per {
	int iteration;
	int unittime;
	long periodForEachThread;
	String exec;
	String resp;
	int prio;
	int dim;

	public task_per(int iter, int unit, String exec, String resp, int period, int prio, int dim) {

		iteration = iter;
		unittime = unit;
		this.exec = exec;
		this.resp = resp;
		periodForEachThread = period;
		this.prio = prio;
		this.dim = dim;
	}

	public int getDim() {
		return dim;
	}

	public void setDim(int dim) {
		this.dim = dim;
	}

	@Override
	public String toString() {
		return "task_per [iteration=" + iteration + ", unittime=" + unittime + ", periodForEachThread="
				+ periodForEachThread + ", exec=" + exec + ", resp=" + resp + ", prio=" + prio + ", dim=" + dim + "]";
	}

	public int getIteration() {
		return iteration;
	}

	public void setIteration(int iteration) {
		this.iteration = iteration;
	}

	public int getUnittime() {
		return unittime;
	}

	public void setUnittime(int unittime) {
		this.unittime = unittime;
	}

	public long getPeriodForEachThread() {
		return periodForEachThread;
	}

	public void setPeriodForEachThread(long periodForEachThread) {
		this.periodForEachThread = periodForEachThread;
	}

	public String getExec() {
		return exec;
	}

	public void setExec(String exec) {
		this.exec = exec;
	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

	public int getPrio() {
		return prio;
	}

	public void setPrio(int prio) {
		this.prio = prio;
	}
}
