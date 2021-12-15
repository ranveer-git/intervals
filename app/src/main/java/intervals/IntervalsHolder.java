package intervals;

import java.time.LocalDate;

/**
 * A collection of Interval that can be queried for various information.
 */
interface IntervalsHolder {

    /**
     * Responds with an Intervals being active on a particular day.
     *
     * @param localDate The date to determine if this is active or not.
     * @returns true if any Interval overlaps localDate, false otherwise.
     */
    boolean isActiveOnDay(LocalDate localDate);

    /**
     * Returns the fraction of localDate that this is active. If it's active
     * for 12 hours on a given day, it will return 0.5.
     *
     * @param localDate The date to get the covered portion of the day for.
     * @return The portion of localDate that this is active for.
     */
    double getCoveredPortionOfDay(LocalDate localDate);

    /**
     * Returns the total portions of days from localDate to the end.
     *
     * @param localDate The date to get the covered portion for the remaining lifetime from.
     * @return The total portions of days for the remaining lifetime.
     */
    double getCoveredPortionFromDate(LocalDate localDate);
}
