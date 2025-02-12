import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

abstract class Event implements Comparable<Event> {
    protected String name;
    protected LocalDateTime dateTime;
    protected List<Reminder> reminders;  // A list to hold reminders for this event.

    public Event(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
        this.reminders = new ArrayList<>();  // Initialize reminder list
    }

    public abstract String getName();
    public LocalDateTime getDateTime() { return this.dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setName(String name) { this.name = name; }

    public List<Reminder> getReminders() {
        return this.reminders;
    }

    public void addReminder(Reminder reminder) {
        this.reminders.add(reminder);
    }

    public void removeReminder(Reminder reminder) {
        this.reminders.remove(reminder);
    }

    @Override
    public int compareTo(Event e) {
        return this.dateTime.compareTo(e.dateTime);
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
        return "Reminder: " + event.getName() + " at " + event.getDateTime().toString();
    }

    @Override
    public LocalDateTime getDateTime() {
        return event.getDateTime().minus(timeBefore);
    }
}
