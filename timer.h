/**
 * @file timer.h
 * @brief utility for computing elapsed time, starting and stopping a virtual
 * stopwatch, and checking whether a time limit has been reached
 *
 * @author Matt Stallmann
 * @date 2019/09/01, adapted from Timer.h in C++-Utilities
 *
 * [Master copy in Utilities/C-Utilities -- please propagate changes to there]
 */

#ifndef TIMER_H
#define TIMER_H

#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>

/**
 * sets current time as start time for the current program
 */
void setStartTime(void);

/**
 * sets a time limit (in seconds)
 */
void setTimeLimit(double seconds);


/**
 * @return the elapsed time in seconds
 */
double elapsedTime(void);

/**
 * check to see if time has run out
 */
bool timeLimitReached(void);

/**
 * sets a checkpoint
 */
void setCheckPoint(void);

/**
 * @return elapsed time (in seconds) since last checkpoint
 */
double timeSinceCheckPoint(void);

// ----  Allow creation of individual timers; these behave like stopwatches -------

typedef struct _timer { double my_elapsed_time; double my_last_checkpoint; } * TIMER; 

/**
 * @return a new instance of a timer
 */
TIMER new_timer(void);

/**
 * resets the timer so that elapsed time is 0
 */
void reset(TIMER timer);

/**
 * (re)starts the timer
 */
void start(TIMER timer);

/**
 * stops the timer
 */
void stop(TIMER timer);

/**
 * @return the total time (in seconds) recorded by the timer,
 * i.e., the sum of all time intervals between start() and stop() calls
 */
double get_total_time(TIMER timer);

#endif

//  [Last modified: 2021 02 22 at 19:24:42 GMT]
