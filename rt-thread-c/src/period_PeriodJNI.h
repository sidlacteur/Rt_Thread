/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class period_PeriodJNI */

#ifndef _Included_period_PeriodJNI
#define _Included_period_PeriodJNI
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     period_PeriodJNI
 * Method:    comDeadline
 * Signature: (JJI)J
 */
JNIEXPORT jlong JNICALL Java_period_PeriodJNI_comDeadline
  (JNIEnv *, jclass, jlong, jlong, jint);

/*
 * Class:     period_PeriodJNI
 * Method:    getExactClockTime
 * Signature: (I)J
 */
JNIEXPORT jlong JNICALL Java_period_PeriodJNI_getExactClockTime
  (JNIEnv *, jclass, jint);

/*
 * Class:     period_PeriodJNI
 * Method:    getClockTime
 * Signature: (I)J
 */
JNIEXPORT jlong JNICALL Java_period_PeriodJNI_getClockTime
  (JNIEnv *, jclass, jint);

/*
 * Class:     period_PeriodJNI
 * Method:    endInstance
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_period_PeriodJNI_endInstance
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     period_PeriodJNI
 * Method:    timeadd
 * Signature: (JJI)J
 */
JNIEXPORT jlong JNICALL Java_period_PeriodJNI_timeadd
  (JNIEnv *, jclass, jlong, jlong, jint);

/*
 * Class:     period_PeriodJNI
 * Method:    timesub
 * Signature: (JJI)J
 */
JNIEXPORT jlong JNICALL Java_period_PeriodJNI_timesub
  (JNIEnv *, jclass, jlong, jlong, jint);

#ifdef __cplusplus
}
#endif
#endif
