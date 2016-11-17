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
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_period_PeriodJNI_comDeadline
  (JNIEnv *, jclass, jlong, jlong);

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
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_period_PeriodJNI_endInstance
  (JNIEnv *, jclass, jlong);

/*
 * Class:     period_PeriodJNI
 * Method:    timeadd
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_period_PeriodJNI_timeadd
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     period_PeriodJNI
 * Method:    timesub
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_period_PeriodJNI_timesub
  (JNIEnv *, jclass, jlong, jlong);

#ifdef __cplusplus
}
#endif
#endif
