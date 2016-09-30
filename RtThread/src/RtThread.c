/*
 * com_codeandme_jni_JNITest.c
 *
 *  Created on: 9 mai 2016
 *      Author: emeraude
 */
#define _GNU_SOURCE
#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <math.h> 		// used for the floor operations
#include <unistd.h>
#include "RtThread_RtThread.h"
#include <sched.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/syscall.h>
#include <errno.h>


#define handle_error_en(en, msg) \
               do { errno = en; perror(msg); exit(EXIT_FAILURE); } while (0)
long getPthredSelf() {
	long y = (long) pthread_self();
	printf("get_pthread from JNI : %ld\n", y);
	return y;
}

int setAffinity(int core_id) {

	cpu_set_t cpuset;
	CPU_ZERO(&cpuset);
	CPU_SET(core_id, &cpuset);

	return pthread_setaffinity_np(pthread_self(), sizeof(cpu_set_t), &cpuset);
}

JNIEXPORT jlong JNICALL Java_RtThread_RtJNI_getPthreadSelf(JNIEnv *env,
		jobject o) {
	return getPthredSelf();
}

JNIEXPORT jint JNICALL Java_RtThread_RtJNI_getThreadPolicy(JNIEnv *env,
		jclass c, jlong pthreadId) {
	int policy;
	struct sched_param param;
	int s = pthread_getschedparam(pthreadId, &policy, &param);

	if (s != 0) {
		handle_error_en(s, "pthread_getschedparam");
	}

	return policy;
}

JNIEXPORT jint JNICALL Java_RtThread_RtJNI_getThreadAffinity(JNIEnv *env,
		jclass c, jlong pthreadId) {
	int j = 0;
	cpu_set_t cpuset;
	CPU_ZERO(&cpuset);
	int s = pthread_getaffinity_np(pthreadId, sizeof(cpu_set_t), &cpuset);
	if (s != 0) {
		handle_error_en(s, "pthread_getaffinity_np");
	}

	//CPU_SIZE_SET
	for (j = 0; j < 2; j++) {
		if (CPU_ISSET(j, &cpuset)) {
			return j;
		}
	}
	return 0;
}

JNIEXPORT jint JNICALL Java_RtThread_RtJNI_getThreadPriority(JNIEnv *env,
		jclass c, jlong pthreadId) {
	int y;
	struct sched_param param;

	int s = pthread_getschedparam(pthreadId, &y, &param);

	if (s != 0) {
		handle_error_en(s, "pthread_getschedparam");
	}

	return param.sched_priority;
}

JNIEXPORT jint JNICALL Java_RtThread_RtJNI_setThreadParameters(JNIEnv *env,
		jclass c, jlong pthreadId, jint priority, jint policy, jint affinity) {

	struct sched_param param;
	param.sched_priority = priority;
	int returnValue = 1;

	if (policy == 0) {

		int s = pthread_setschedparam(pthreadId, SCHED_RR, &param);

		if (s != 0) {
			handle_error_en(s, "pthread_setschedparam");
		}

		returnValue = pthread_setschedprio(pthreadId, priority);
		setAffinity(affinity);
	}

	if (policy == 1) {

		int s = pthread_setschedparam(pthreadId, SCHED_FIFO, &param);

		if (s != 0) {
			handle_error_en(s, "pthread_setschedparam");
		}

		returnValue = pthread_setschedprio(pthreadId, priority);

		setAffinity(affinity);
	}
	return returnValue;
}

