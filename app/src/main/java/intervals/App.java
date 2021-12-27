package intervals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App {
  public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  public static void main(String[] args) {
    try {
      Interval[] intervalList = new Interval[]{
          Interval.of(LocalDateTime.now(), LocalDateTime.now().plusDays(2)),
          Interval.of(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1)),
          Interval.of("2021-01-01 00:00", "2021-01-11 12:00", FORMATTER),
          Interval.of("2021-01-20 06:00", "2021-01-25 12:00", FORMATTER)

      };
      IntervalsHolder intervals = new IntervalsHolderImpl(intervalList);
      intervals.isActiveOnDay(LocalDate.of(2021, 1, 25));
      System.out.println(intervals);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
