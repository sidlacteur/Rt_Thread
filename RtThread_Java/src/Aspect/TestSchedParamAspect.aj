package Aspect;

import RtThread.RtJNI;

public aspect TestSchedParamAspect {

	/**
	 * point_cut on RtJNI method, to test if enter parameters set correctly or
	 * not.
	 * 
	 * @param pthreadId
	 * @param prio
	 * @param policy
	 * @param aff
	 */
	pointcut PriorityTest(long pthreadId, int prio, int policy, int aff) : call(* RtThread.RtJNI.setThreadParameters(..)) && args(pthreadId,prio,policy,aff);

	int around(long pthreadId, int prio, int policy, int aff) : PriorityTest(pthreadId,prio,policy,aff){
		if (RtJNI.getPthreadSelf() != (-1)) {

			if (prio < 0) {
				throw new RuntimeException("Error, priority should be upper than 0");
			}
			if (prio > 99) {
				throw new RuntimeException("Error, priority should be less than 99");
			}
			if (aff != 0 && aff != 1) {
				throw new RuntimeException("Error, affinity should be equal to  0 or 1");
			}
			if (policy != 0 && policy != 1) {
				throw new RuntimeException("Error, policy should be equal to  0 or 1");
			} else {
				return proceed(pthreadId, prio, policy, aff);
			}
		}
		return 0;

	}
}
