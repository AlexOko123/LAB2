//this is for adding new events
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;

public class AddEventModal extends JDialog {
    public AddEventModal(EventListPanel parent) {  // Change parameter to EventListPanel
        super((JFrame) null, "Add Event", true);  // Pass null for JFrame (you won't use it here)
        setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Event Name:");
        JTextField nameField = new JTextField();
        add(nameLabel);
        add(nameField);

        JComboBox<String> eventTypeComboBox = new JComboBox<>(new String[] {"Meeting", "Deadline"});
        add(new JLabel("Event Type:"));
        add(eventTypeComboBox);

        // Add fields for other event data like location and duration (for Meeting) or deadline time
        JTextField locationField = new JTextField();
        JTextField durationField = new JTextField();
        JTextField dateField = new JTextField();

        add(new JLabel("Location:"));
        add(locationField);
        add(new JLabel("Duration (in hours):"));
        add(durationField);
        add(new JLabel("DateTime:"));
        add(dateField);

        JButton addButton = new JButton("Add");

        addButton.addActionListener(e -> {
            String selectedType = (String) eventTypeComboBox.getSelectedItem();
            LocalDateTime eventDate = LocalDateTime.parse(dateField.getText());

            if ("Meeting".equals(selectedType)) {
                // Create a meeting
                LocalDateTime endDate = eventDate.plusHours(Integer.parseInt(durationField.getText()));
                Meeting meeting = new Meeting(nameField.getText(), eventDate, endDate, locationField.getText());
                parent.addEvent(meeting);  // Use EventListPanel's addEvent method
            } else if ("Deadline".equals(selectedType)) {
                // Create a deadline
                Deadline deadline = new Deadline(nameField.getText(), eventDate);
                parent.addEvent(deadline);  // Use EventListPanel's addEvent method
            }

            this.dispose();
        });

        add(new JLabel("")); // Empty placeholder
        add(addButton);

        setSize(300, 200);
        setLocationRelativeTo(parent);
    }
}
