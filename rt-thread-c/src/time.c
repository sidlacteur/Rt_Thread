/*
 * time.c
 *
 *  Created on: 14 juin 2016
 *      Author: Sidlacteur
 */
#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <math.h> 		// used for the floor operations
#include <unistd.h>		/* for gettimeofday(), getpid() */
#include <sched.h>		/* for scheduling calls/constants */
#include <unistd.h>
#include <sys/types.h>
#include <sys/syscall.h>
#include"period_PeriodJNI.h"
#include "time.h"
#include "comptime.h"

JNIEXPORT jlong JNICALL Java_period_PeriodJNI_comDeadline(JNIEnv *env,
		jobject o, jlong dl, jlong exec,jint unit) {

	tspec t, t2;

	t = tspec_from(dl, unit);

	t2 = tspec_from(exec, unit);

	/*add period to next */
	jlong ret = tspec_cmp(&t, &t2);

	/*return additinal time in long type*/
	return ret;
}
JNIEXPORT jlong JNICALL Java_period_PeriodJNI_getExactClockTime
  (JNIEnv  *env, jclass o, jint i)
{

	return gettimeThread(i);
}
JNIEXPORT jlong JNICALL Java_period_PeriodJNI_getClockTime(JNIEnv *env,
		jobject o, jint i) {
	return gettime(i);
}

JNIEXPORT void JNICALL Java_period_PeriodJNI_endInstance(JNIEnv *env,
		jobject o, jlong i,jint unit) {
	mysleep_ms(i,unit);
}

JNIEXPORT jlong JNICALL Java_period_PeriodJNI_timeadd(JNIEnv *env, jobject o,
		jlong exec, jlong period,jint unittime) {

	/*Convert period and next from long to tspec  struct */
	tspec t, t2;
	t = tspec_from(exec, unittime);

	t2 = tspec_from(period, unittime);

	/*add period to next */
	t = tspec_add(&t, &t2);

	/*return additinal time in long type*/
	return tspec_to(&t, unittime);
}

JNIEXPORT jlong JNICALL Java_period_PeriodJNI_timesub(JNIEnv *env, jobject o,
		jlong period1, jlong period2, jint unittime) {
	/*Convert period and next from long to tspec  struct */
	tspec t, t2;
	t = tspec_from(period1, unittime);

	t2 = tspec_from(period2, unittime);

	/*add period to next */
	t = tspec_sub(&t, &t2);

	/*return additinal time in long type*/
	return tspec_to(&t, unittime);
}
