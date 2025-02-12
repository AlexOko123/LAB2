import java.time.LocalDateTime;
import java.time.Duration;

public class Meeting extends Event implements Completable {
    private LocalDateTime endDateTime;  // The end time of the meeting
    private String location;  // The location of the meeting
    private boolean complete;  // Tracks if the meeting is marked as complete

    // Constructor
    public Meeting(String name, LocalDateTime startDateTime, LocalDateTime endDateTime, String location) {
        super(name, startDateTime);  // Call the constructor of Event
        this.endDateTime = endDateTime;
        this.location = location;
        this.complete = false;  // Initially the meeting is not complete
    }

    @Override
    public String getName() {
        return name;  // Return the name of the meeting
    }

    @Override
    public void complete() {
        this.complete = true;  // Mark the meeting as complete
    }

    @Override
    public boolean isComplete() {
        return complete;  // Return whether the meeting is complete or not
    }

    public LocalDateTime getEndTime() {
        return this.endDateTime;  // Return the end time of the meeting
    }

    public void setEndTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;  // Set a new end time for the meeting
    }

    public String getLocation() {
        return this.location;  // Return the location of the meeting
    }

    public void setLocation(String location) {
        this.location = location;  // Set a new location for the meeting
    }

    // Method to calculate the duration of the meeting
    public Duration getDuration() {
        return Duration.between(dateTime, endDateTime);  // Calculate the duration between start and end times
    }
}
