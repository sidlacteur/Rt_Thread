package RtMgrpackage;

public class Parameters {

	/**
	 * real-time parameters
	 */

	/**
	 * affinity : (0 - CPU's number) in which CPU we affect our thread
	 */
	int affinity;

	/**
	 * real-time priority priority its between (0 - 99)
	 */
	int priority;

	/**
	 * real-time policy : (SCHED_RR , SCHED_FIFO)
	 * 
	 */
	int policy;
	/**
	 * 
	 * started, finished : boolean variable, to determinate if thread started or
	 * finished 
	 * 
	 */
	boolean started;
	boolean finished;
	
	/**
	 * pthreadId: it's pthread_id of thread
	 */
	long pthreadId;

	/**
	 * pid: it's process id of thread
	 */
	long pid;

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}


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

	public Parameters(int affinity, int priority, int policy) {
		this.affinity = affinity;
		this.priority = priority;
		this.policy = policy;
		this.finished = false;
		this.started = false;
		this.pthreadId = -1;
		this.pid = -1;
	}

}
