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
            // Create a checkbox for "Complete Event"
            JCheckBox completeCheckBox = new JCheckBox("Complete", event instanceof Completable && ((Completable) event).isComplete());
            completeCheckBox.addActionListener(e -> {
                if (event instanceof Completable) {
                    // Mark the event as complete when checked
                    ((Completable) event).complete();
                    JOptionPane.showMessageDialog(this, "Event marked as complete.");

                    // Change the UI appearance, like disabling the checkbox after completion
                    completeCheckBox.setEnabled(false);  // Disable the checkbox after marking as complete
                    nameLabel.setText(nameLabel.getText() + " (Completed)");  // Update label text to show completion
                    setBackground(Color.GRAY);  // Gray out the event panel to show it's completed
                }
            });
            this.add(completeCheckBox);
        }

    }
}
