import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;  // Importing LocalDateTime
import java.time.Month;

public class EventPanel extends JPanel {
    private Event event;

    public EventPanel(Event event) {
        this.event = event;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Event: " + event.getName());
        JLabel dateLabel = new JLabel("Date: " + event.getDateTime().toString());

        this.add(nameLabel);
        this.add(dateLabel);

        if (event instanceof Completable) {
            JButton completeButton = new JButton("Complete Event");
            completeButton.addActionListener(e -> {
                ((Completable) event).complete();
                JOptionPane.showMessageDialog(this, "Event marked as complete.");
            });
            this.add(completeButton);
        }
    }
}
