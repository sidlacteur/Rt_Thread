/*
 * com_Ircica_emeraude_jni_RtThread.c
 *
 *  Created on: 6 novembre 2016
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

/**
 * it returns pthread id of current thread
 */
long getPthredSelf() {
	long y = (long) pthread_self();
	printf("get_pthread from JNI : %ld\n", y);
	return y;
}

/**
 * it set affinity to the current thread
 */

int setAffinity(int core_id) {

	cpu_set_t cpuset;
	CPU_ZERO(&cpuset);
	CPU_SET(core_id, &cpuset);

	return pthread_setaffinity_np(pthread_self(), sizeof(cpu_set_t), &cpuset);
}

/**
 *  it returns pthread id of current thread
 */

JNIEXPORT jlong JNICALL Java_RtThread_RtJNI_getPthreadSelf(JNIEnv *env,
		jobject o) {

	return getPthredSelf();
}


/**
 *  it returns getpid of current thread
 */

JNIEXPORT jlong JNICALL Java_RtThread_RtJNI_getPid(JNIEnv *env,
		jobject o) {
	//printf("\ngetpid: %d \n t_id:%lu\n",getpid(),syscall(SYS_gettid));
	return (long) getpid();
}

/**
 *  it returns
 */
JNIEXPORT jint JNICALL Java_RtThread_RtJNI_getThreadPolicy(JNIEnv *env,
		jclass c, jlong pthreadId) {
	int policy;
	struct sched_param param;

	if(pthreadId != -1){

		int s = pthread_getschedparam(pthreadId, &policy, &param);

		if (s != 0) {
			handle_error_en(s, "pthread_getschedparam");
		}
	}else {
		printf("pthread_getschedparam(pthreadId) error :: pthreadId not initialized yet ");
		return 0;
	}


	return policy;
}

JNIEXPORT jint JNICALL Java_RtThread_RtJNI_getThreadAffinity(JNIEnv *env,
		jclass c, jlong pthreadId) {

	if(pthreadId != -1){
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
	return 0;}
	else {
		printf("pthread_getaffinity_np(pthreadId) error :: pthreadId not initialized yet ");
		return 0;
		}
}

JNIEXPORT jint JNICALL Java_RtThread_RtJNI_getThreadPriority(JNIEnv *env,
		jclass c, jlong pthreadId) {

if(pthreadId != -1){

	int y;
	struct sched_param param;

	int s = pthread_getschedparam(pthreadId, &y, &param);

	if (s != 0) {
		handle_error_en(s, "pthread_getschedparam");
	}

	return param.sched_priority;
}
else {
	printf("pthread_getschedparam(pthreadId) error :: pthreadId not initialized yet ");
	return 0;
}
}

/**
 *   policy == 1 for SCHED_FIFO  && policy == 0 for SCHED_RR
 */

JNIEXPORT jint JNICALL Java_RtThread_RtJNI_setThreadParameters(JNIEnv *env,
		jclass c, jlong pthreadId, jint priority, jint policy, jint affinity) {
if(pthreadId != -1){

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
else {
	printf("pthread_getschedparam(pthreadId) error :: pthreadId not initialized yet ");
	return 0;
}
}

