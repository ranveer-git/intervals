This problem asks the candidate to create an implementation of the IntervalsHolder interface. The IntervalsHolder
interface takes Interval objects which it will use to respond to the interface methods. A shell implementation is
created at IntervalsHolderImpl for you to fill out. Please keep the constructor signature of
`public IntervalsHolderImpl(Interval... intervals)` intact but feel free to modify the contents.

**Project Files:**
* app/src/main/java/intervals/IntervalsHolderImpl.java = class to implement in this exercise.
* This contains a gradle wrapper, which can be run from the root directory with:
  * ./gradlew check <-- To run all checks on the code

**Notes:**
1. Intervals can overlap.
2. The Start and End Dates are in an open/closed range. "Jan 1 @ 00:00:00 to Jan 5 @ 00:00:00" includes Jan 1 and
does not include Jan 5.

**Example usage:**<br>
If creating an IntervalsHolder with these two intervals:<br>
Interval 1: 2021-01-01 @ 00:00 to 2021-01-11 @ 12:00<br>
Interval 2: 2021-01-20 @ 06:00 to 2021-01-25 @ 12:00

The following calls would have the following results:
* isActiveOnDay(2021-01-01): true
* isActiveOnDay(2021-01-11): true
* isActiveOnDay(2021-01-12): false
<br><br>
* getCoveredPortionOfDay(2021-01-01): 1.0
* getCoveredPortionOfDay(2021-01-11): 0.5
* getCoveredPortionOfDay(2021-01-12): 0.0
<br><br>
* getCoveredPortionFromDate(2021-01-01): 15.75
  * Because there are 10.5 days in the first interval and 5.25 days in the second, there are 15.75 days remaining for the IntervalsHolder on Jan 1.
* getCoveredPortionFromDate(2021-01-02): 14.75
* getCoveredPortionFromDate(2021-01-12): 5.25
* getCoveredPortionFromDate(2021-02-01): 0.0

**Constraints:**
* 2000-01-01T00:00 < LocalDateTimes < 2038-12-31T23:59
* 2000-01-01 < LocalDates < 2038-12-31
* intervals.length < 1024
* You can consider that a day has 24*60 minutes.
* The methods should return at a minute granularity.
* Timezones do not need to be considered.
* Solution should be written in Java 8 and not include additional libraries beyond those already present.

**Criteria of Done:**
* The implementation returns the correct answers.
* The implementation is optimized to be as close to garbage-free when calling its methods as you can make it.
  * If time does not allow for a garbage-free implementation, please explain how you might achieve this.
