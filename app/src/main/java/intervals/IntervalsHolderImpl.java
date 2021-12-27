package intervals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * Implementation class of an IntervalsHolder for the candidate to implement.
 */
public class IntervalsHolderImpl implements IntervalsHolder {


  Set<Interval> intervalsSet = new TreeSet<>();
  Map<LocalDate, Float> coveredPortion = new LinkedHashMap<>();

  public IntervalsHolderImpl(Interval... intervals) {
    validate(intervals);
    Collections.addAll(intervalsSet, intervals);
    for (Interval interval : intervalsSet) {
      LocalDate currentDate = interval.getStart().toLocalDate();

      while (currentDate.compareTo(interval.getEnd().toLocalDate()) <= 0) {
        coveredPortion.put(currentDate, 1.0f);
        if (currentDate.equals(interval.getStart().toLocalDate())) {
          //get total and deduct time before start time
          coveredPortion.put(currentDate,
              coveredPortion.get(currentDate)
                  - getMinutesFromStartOfDayTillTime(interval.getStart()));
        }

        if (currentDate.equals(interval.getEnd().toLocalDate())) {
          coveredPortion.put(currentDate,
              coveredPortion.get(currentDate) - getMinutesFromTimeTillEndOfDay(interval.getEnd()));
        }
        currentDate = currentDate.plusDays(1);
      }
    }
  }

  /**
   * Adding Validate logic to handle out of range values given in constraints
   * 1. Array < 1024
   * 2. DateTime should be in 2000-01-01T00:00 < LocalDateTimes < 2038-12-31T23:59
   * @param intervals
   */
  private void validate(Interval... intervals) {
    if (intervals.length > 1023) {
      throw new RuntimeException("Size of input intervals is larger than max allowed size 1023");
      //intervals.length < 1024
    }
    for (Interval interval : intervals) {
      if (interval.getStart().compareTo(MIN_DATETIME) < 0 || interval.getStart().compareTo(MAX_DATETIME) > 0) {
        throw new RuntimeException("StartDate of Interval out of the allowed Range: [2000-01-01T00:00 , 2038-12-31T23:59]");
      }
      if (interval.getEnd().compareTo(MIN_DATETIME) < 0 || interval.getEnd().compareTo(MAX_DATETIME) > 0) {
        throw new RuntimeException("EndDate of Interval out of the allowed Range: [2000-01-01T00:00 , 2038-12-31T23:59]");
      }
    }
  }

  private static float getMinutesFromStartOfDayTillTime(LocalDateTime datetime) {
    return ((datetime.getHour() * MINUTES_IN_HOUR) + datetime.getMinute()) * FRACTION_DAY_PER_MINUTE;
  }

  private static float getMinutesFromTimeTillEndOfDay(LocalDateTime datetime) {
    return 1f - getMinutesFromStartOfDayTillTime(datetime);
  }

//  public static void main(String[] args) {
//    System.out.println(getMinutesFromStartOfDayTillTime(LocalDateTime.of(LocalDate.of(2021, 1, 1), LocalTime.of(16, 0, 15))));
//    System.out.println(getMinutesFromTimeTillEndOfDay(LocalDateTime.of(LocalDate.of(2021, 1, 1), LocalTime.of(16, 0, 15))));
//  }

  @Override
  public boolean isActiveOnDay(LocalDate localDate) {
    return coveredPortion.containsKey(localDate);
//    for (Interval interval : intervalsSet) {
//      //startate<=localDate<endDate
//      if (interval.getStart().toLocalDate().compareTo(localDate) <= 0
//          && localDate.compareTo(interval.getEnd().toLocalDate()) < 0) {
//        return true;
//      }
//    }
//    return false;
  }

  @Override
  public double getCoveredPortionOfDay(LocalDate localDate) {
    return Optional.ofNullable(coveredPortion.get(localDate)).orElse(0.0f);
  }

  @Override
  public double getCoveredPortionFromDate(LocalDate localDate) {
    return coveredPortion.entrySet().stream()
        .filter(entry -> entry.getKey().compareTo(localDate) >= 0)
        .map(entry -> entry.getValue())
        .reduce(0.0f, (a,b) -> a + b);
  }
}
