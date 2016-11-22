/*
 * comptime.c
 *
 *  Created on: 14 juin 2016
 *      Author: Sidlacteur
 */

#include"comptime.h"
#include <stdlib.h>
#include <stdio.h>

struct unit_conv {
	long mul;
	long div;
};

const struct unit_conv conv_table[] = { { 1, 1000000000 },  // SEC
		{ 1000, 1000000 },  // MILLI
		{ 1000000, 1000 },  // MICRO
		{ 1000000000, 1 }   // NANO
};

/**
 Given a timespec, converts to a long according to unit.
 */
long tspec_to(const tspec *t, int unit) {
	long tu;

	tu = (t->tv_sec) * conv_table[unit].mul;

	tu += (t->tv_nsec) / conv_table[unit].div;

	return tu;
}

/**
 Given a long integer, expressed as unit, converts it into a
 timespec.
 */
tspec tspec_from(long tu, int unit) {
	tspec t;

	long mm = tu % conv_table[unit].mul;

	t.tv_sec = tu / conv_table[unit].mul;

	t.tv_nsec = mm * conv_table[unit].div;

	return t;
}

/**
 Given a timespec, converts to a long according to unit.
 */
long gettime(int unit) {

	tspec t;
	clock_gettime(CLOCK_REALTIME, &t);
	return tspec_to(&t, unit);
}

long gettimeThread(int unit) {

	tspec t;
	clock_gettime(CLOCK_THREAD_CPUTIME_ID, &t);
	return tspec_to(&t, unit);
}
void mysleep_ms(long nsec) {
	tspec t;

	t = tspec_from(nsec, unite);
	clock_nanosleep(CLOCK_REALTIME, TIMER_ABSTIME, &t, NULL);
}

tspec tspec_add(const tspec *a, const tspec *b) {
	tspec s;
	s.tv_nsec = a->tv_nsec + b->tv_nsec;
	s.tv_sec = a->tv_sec + b->tv_sec;
	while (s.tv_nsec >= 1000000000) {
		s.tv_nsec = s.tv_nsec - 1000000000;
		s.tv_sec += 1;
	}
	return s;
}

/**
 Compares two timespecs
 */
int tspec_cmp(const tspec *a, const tspec *b) {
	if (a->tv_sec > b->tv_sec)
		return 1;
	else if (a->tv_sec < b->tv_sec)
		return -1;
	else if (a->tv_sec == b->tv_sec) {
		if (a->tv_nsec > b->tv_nsec)
			return 1;
		else if (a->tv_nsec == b->tv_nsec)
			return 0;
		else
			return -1;
	}
	return 0;
}

tspec tspec_sub(const tspec *a, const tspec *b) {
	tspec d;

	d.tv_nsec = a->tv_nsec - b->tv_nsec;
	d.tv_sec = a->tv_sec - b->tv_sec;
	if (a->tv_nsec < b->tv_nsec) {
		d.tv_nsec += 1000000000;
		d.tv_sec -= 1;
	}
	return d;   //tspec_to(&d, unit);
}
