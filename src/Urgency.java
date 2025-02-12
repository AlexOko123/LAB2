import java.time.Duration;
import java.time.LocalDateTime;

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
