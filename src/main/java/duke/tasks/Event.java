package duke.tasks;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event object that extends Task.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for an Event object.
     *
     * @param description The Event description.
     * @param from The start date of the Event.
     * @param to The end date of the Event.
     */
    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter newDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        DateTimeFormatter newDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDateTime fromDateTime;
        LocalDateTime toDateTime;
        try {
            fromDateTime = LocalDateTime.parse(from, dateTimeFormatter);
            this.from = fromDateTime.format(newDateTime);
        } catch (DateTimeParseException e) {
            LocalDate fromDate = LocalDate.parse(from, dateFormatter);
            this.from = fromDate.format(newDate);
            fromDateTime = fromDate.atStartOfDay();
        }
        try {
            toDateTime = LocalDateTime.parse(to, dateTimeFormatter);
            this.to = toDateTime.format(newDateTime);
        } catch (DateTimeParseException e) {
            LocalDate toDate = LocalDate.parse(to, dateFormatter);
            this.to = toDate.format(newDate);
            toDateTime = toDate.atTime(23, 59);

        }
        assert toDateTime.isAfter(fromDateTime) : "End date is earlier than start date";
        assert fromDateTime.isAfter(LocalDateTime.now()) : "Date has passed";
    }

    /**
     * Check if the event's dates contains the given keyword.
     *
     * @param keyword The keyword argument.
     * @return A boolean value.
     */
    public boolean dateContains(String keyword) {
        keyword = keyword.toLowerCase();
        if ((this.from.length() >= keyword.length()) || (this.to.length() >= keyword.length())) {
            return this.from.toLowerCase().contains(keyword) || this.to.toLowerCase().contains(keyword);
        } else {
            return false;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "\n (from: " + from + " to: " + to + ")";
    }
}
