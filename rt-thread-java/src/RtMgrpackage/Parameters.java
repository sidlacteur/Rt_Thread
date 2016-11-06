package RtMgrpackage;

public class Parameters {

	/**
	 * rt_parameters :
	 * 
	 * affinity : (0 - CPU's number) in which CPU we affect our thread priority
	 * : (0 - 99) rt_priority policy : (SCHED_RR , SCHED_FIFO) rt_policy
	 * started, finished : boolean variable, to determinate if thread started or
	 * finished pthreadId: it's pthread_id of thread
	 * 
	 */

	int affinity;
	int priority;
	int policy;
	boolean started;
	long pthreadId;
	long pid;

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	boolean finished;

	public int getAffinity() {
		return affinity;
	}

	public void setAffinity(int affinty) {
		this.affinity = affinty;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getPolicy() {
		return policy;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public long getPthreadId() {
		return pthreadId;
	}

	public void setPthreadId(long pthreadId) {
		this.pthreadId = pthreadId;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public void setPolicy(int policy) {
		this.policy = policy;
	}

	@Override
	public String toString() {
		return "Parameters [affinity=" + affinity + ", priority=" + priority + ", policy=" + policy + ", started="
				+ started + ", pthreadId=" + pthreadId + ", pid=" + pid + ", finished=" + finished + "]";
	}

	public Parameters(int affinty, int priority, int policy) {
		this.affinity = affinty;
		this.priority = priority;
		this.policy = policy;
		this.finished = false;
		this.started = false;
		this.pthreadId = -1;
		this.pid = -1;
	}

}
