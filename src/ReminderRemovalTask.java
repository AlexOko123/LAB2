import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class ReminderRemovalTask extends TimerTask {
    private List<Event> events;

    public ReminderRemovalTask(List<Event> events) {
        this.events = events;
    }

    @Override
    public void run() {
        for (Event event : events) {
            for (Reminder reminder : new ArrayList<>(event.getReminders())) {  // Avoid concurrent modification
                if (reminder.getDateTime().isBefore(LocalDateTime.now())) {
                    event.removeReminder(reminder);
                }
            }
        }
    }
}
