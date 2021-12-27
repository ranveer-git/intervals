package intervals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * A collection of Interval that can be queried for various information.
 */
interface IntervalsHolder {

  public static final int MAX_INTERVAL_LENGTH = 1023;
  public static final LocalDateTime MIN_DATETIME = LocalDateTime.of(LocalDate.of(2000, 1, 1), LocalTime.of(0, 0, 0));
  public static final LocalDateTime MAX_DATETIME = LocalDateTime.of(LocalDate.of(2038, 12, 31), LocalTime.of(23, 59, 0));

  //1440 mins = 1 day
  //6 hours = 6 * 60 = 360 mins
  static final float HOURS_IN_DAY = 24;
  static final float MINUTES_IN_HOUR = 60;
  static final float MINUTES_IN_A_DAY = HOURS_IN_DAY * MINUTES_IN_HOUR;
  static final float FRACTION_DAY_PER_MINUTE = 1 / MINUTES_IN_A_DAY;
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
