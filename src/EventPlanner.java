import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EventPlanner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        EventListPanel eventListPanel = new EventListPanel();
        addDefaultEvents(eventListPanel);
        frame.add(eventListPanel);

        frame.setVisible(true);
    }

    public static void addDefaultEvents(EventListPanel eventsPanel) {
        // Add some default events (deadlines and meetings)
        Deadline deadline = new Deadline("Project Deadline", LocalDateTime.of(2025, Month.MAY, 20, 17, 0));
        Meeting meeting = new Meeting("Team Meeting", LocalDateTime.of(2025, Month.MAY, 15, 10, 0),
                LocalDateTime.of(2025, Month.MAY, 15, 11, 0), "Conference Room");

        eventsPanel.addEvent(deadline);
        eventsPanel.addEvent(meeting);
    }
}
