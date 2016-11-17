/*
 * comptime.h
 *
 *  Created on: 14 juin 2016
 *      Author: Sidlacteur
 */

#ifndef COMPTIME_H_
#define COMPTIME_H_

#include <time.h>

typedef struct timespec tspec;

/* time units */
#define	SEC	0
#define	MILLI	1
#define	MICRO	2
#define	NANO	3
#define unite 1

void tspec_init();
/**     Returns the reference time */
tspec tspec_get_ref();

/**     Returns the current time from the reference, in unit     */


long tspec_to(const tspec *t, int unit);

tspec tspec_from(long tu, int unit);

int tspec_cmp(const tspec *a, const tspec *b);

tspec tspec_sub(const tspec *a, const tspec *b);

tspec tspec_add(const tspec *a, const tspec *b);


long gettime(int unit);

void mysleep_ms(long milisec);

#endif /* COMPTIME_H_ */
