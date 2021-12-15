package intervals;

import java.time.LocalDateTime;

/**
 * Holder for a date range.
 *
 * Date ranges are open/closed. They are inclusive of the start date time and
 * exclusive of the end date time.
 */
public class Interval {
    private LocalDateTime start;
    private LocalDateTime end;

    public Interval(LocalDateTime start, LocalDateTime end) {
        if (!start.isBefore(end)) {
            throw new IllegalArgumentException("The Start must come before the End.");
        }
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
