package intervals;

import com.google.common.base.Objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Holder for a date range.
 * <p>
 * Date ranges are open/closed. They are inclusive of the start date time and
 * exclusive of the end date time.
 */
public class Interval implements Comparable<Interval> {
  private final LocalDateTime start;
  private final LocalDateTime end;

  public Interval(LocalDateTime start, LocalDateTime end) {
    if (!start.isBefore(end)) {
      throw new IllegalArgumentException("The Start must come before the End.");
    }
    this.start = start;
    this.end = end;
  }

  public static Interval of(LocalDateTime start, LocalDateTime end) {
    return new Interval(start, end);
  }

  public static Interval of(String start, String end, DateTimeFormatter formatter) {
    return new Interval(LocalDateTime.parse(start, formatter), LocalDateTime.parse(end, formatter));
  }

  public LocalDateTime getStart() {
    return start;
  }

  public LocalDateTime getEnd() {
    return end;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Interval interval = (Interval) o;
    return Objects.equal(start, interval.start) && Objects.equal(end, interval.end);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(start, end);
  }

  @Override
  public int compareTo(Interval o) {
    return this.getStart().compareTo(o.getStart());
  }
}
