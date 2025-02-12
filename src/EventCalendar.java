import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

abstract class Event implements Comparable<Event> {
    protected String name;
    protected LocalDateTime dateTime;

    public Event(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    public abstract String getName();
    public LocalDateTime getDateTime() { return this.dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setName(String name) { this.name = name; }

    @Override
    public int compareTo(Event e) {
        return this.dateTime.compareTo(e.dateTime);
    }
}

interface Completable {
    void complete();
    boolean isComplete();
}

class Deadline extends Event implements Completable {
    private boolean complete;

    public Deadline(String name, LocalDateTime dateTime) {
        super(name, dateTime);
        this.complete = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void complete() {
        this.complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }
}

class Meeting extends Event implements Completable {
    private LocalDateTime endDateTime;
    private String location;
    private boolean complete;

    public Meeting(String name, LocalDateTime start, LocalDateTime end, String location) {
        super(name, start);
        this.endDateTime = end;
        this.location = location;
        this.complete = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void complete() {
        this.complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }

    public LocalDateTime getEndTime() {
        return this.endDateTime;
    }

    public void setEndTime(LocalDateTime end) {
        this.endDateTime = end;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getDuration() {
        return java.time.Duration.between(dateTime, endDateTime).toMinutes();
    }
}

class Reminder extends Event {
    private Duration timeBefore;
    private Event event;

    public Reminder(String name, LocalDateTime dateTime, Event event, Duration timeBefore) {
        super(name, dateTime);
        this.event = event;
        this.timeBefore = timeBefore;
    }

    @Override
    public String getName() {
        return "Reminder: " + name + " at " + dateTime.toString();
    }

    @Override
    public LocalDateTime getDateTime() {
        return event.getDateTime().minus(timeBefore);
    }
}
enum Urgency {
    DISTANT, IMMINENT, OVERDUE
}

class UrgencyHelper {
    static Duration thresholdOfImminence = Duration.ofHours(2);

    public static void setThresholdOfImminence(Duration threshold) {
        thresholdOfImminence = threshold;
    }

    public static Urgency getUrgency(LocalDateTime eventTime) {
        Duration remainingTime = Duration.between(LocalDateTime.now(), eventTime);

        if (remainingTime.isNegative()) {
            return Urgency.OVERDUE;
        } else if (remainingTime.compareTo(thresholdOfImminence) <= 0) {
            return Urgency.IMMINENT;
        } else {
            return Urgency.DISTANT;
        }
    }
}
