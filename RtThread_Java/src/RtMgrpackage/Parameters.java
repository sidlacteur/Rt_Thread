package RtMgrpackage;

public class Parameters {

	int affinity;
	int priority;
	int policy;
	boolean started;
	long pthreadId;
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
		return "Parameters [affinty=" + affinity + ", priority=" + priority
				+ ", policy=" + policy + ", started=" + started
				+ ", pthreadId=" + pthreadId + ", finished=" + finished + "]";
	}

	public Parameters(int affinty, int priority, int policy) {
		this.affinity = affinty;
		this.priority = priority;
		this.policy = policy;
		this.finished = false;
		this.started = false;
		this.pthreadId = -1;
	}

}
