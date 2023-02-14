/**
 * @file timer.c
 * @brief implementation of timer functions
 *
 * @author Matt Stallmann
 * @date 2019/09/03, adapted from Timer.cpp in C++-Utilities
 *
 * [Master copy in Utilities/C-Utilities -- please propagate changes to there]
 */

#include<float.h>
#include<sys/time.h>
#include<sys/resource.h>
#include"timer.h"

/**
 * start time of the program
 */
static double start_time = -1.0;

/**
 * time to stop the program if time limit has been set
 */
static double stop_time = DBL_MAX;

/**
 * time at last checkpoint
 */
static double checkpoint_time = -1.0;

/**
 * @return number of "user" CPU seconds used by this process so far
 * (accurate to whatever the system clock allows)
 */
static double getUserSeconds(void) {
  struct rusage ru;
  getrusage( RUSAGE_SELF, &ru );
  return ( ru.ru_utime.tv_sec + 
           ru.ru_utime.tv_usec / 1000000.0 );
}

/**
 * sets current time as start time for the current program
 */
void setStartTime(void) {
    start_time = getUserSeconds();
}

/**
 * sets a time limit (in seconds)
 */
void setTimeLimit(double seconds) {
    if( start_time < 0 ) setStartTime();
    stop_time = start_time + seconds;
}

/**
 * @return the elapsed time in seconds
 */
double elapsedTime(void) {
    return getUserSeconds() - start_time;
}

/**
 * check to see if time has run out
 */
bool timeLimitReached(void) {
    return getUserSeconds() >= stop_time;
}

/**
 * sets a checkpoint
 */
void setCheckPoint(void) {
    checkpoint_time = getUserSeconds();
}

/**
 * @return elapsed time since last checkpoint
 */
double timeSinceCheckPoint(void) {
    if( checkpoint_time < 0 ) {
        fprintf(stderr, "*** Warning: timeSinceCheckPoint() - no checkpoint set\n");
        return 0;
    }
    return getUserSeconds() - checkpoint_time;
}

// -------- functions for individual timers --------

/**
 * @return a new instance of a timer
 */
TIMER new_timer(void) {
    TIMER t = malloc(sizeof(struct _timer));
    t->my_elapsed_time = 0;
    t->my_last_checkpoint = getUserSeconds();
    return t;
}

/**
 * resets the timer so that elapsed time is 0
 */
void reset(TIMER timer) {
    timer->my_elapsed_time = 0;
}

/**
 * (re)starts the timer
 */
void start(TIMER timer) {
    timer->my_last_checkpoint = getUserSeconds();
}

/**
 * stops the timer
 */
void stop(TIMER timer) {
    timer->my_elapsed_time += getUserSeconds() - timer->my_last_checkpoint;
}

/**
 * @return the total time recorded by the timer, i.e., the sum of all time
 * intervals between start() and stop() calls
 */
double get_total_time(TIMER timer) {
    return timer->my_elapsed_time;
}

#ifdef TESTING

#include <math.h>

int main(int argc, char * argv[]) {
    int iterations = atoi(argv[1]);
    double limit = atof(argv[2]);
    setStartTime();
    setTimeLimit(limit);
    TIMER t1 = new_timer();
    TIMER t2 = new_timer();
    bool odd = true;
    while ( ! timeLimitReached() ) {
        if ( odd ) start(t1); else start(t2);
        for ( int i = 0; i < iterations; i++ ) {
            int n = 0;
            for ( int j = 0; j < iterations; j++ ) {
                n *= i + j;
                double s = sqrt(n);
                n += (int) s;
            }
        }
        if ( odd ) stop(t1); else stop(t2);
        odd = ! odd;
    }
    printf("t1 = %f, t2 = %f\n",
           get_total_time(t1), get_total_time(t2));
}

#endif

//  [Last modified: 2019 09 03 at 14:36:32 GMT]
