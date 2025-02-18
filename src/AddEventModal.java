//this is for adding new events
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

        // Add instruction label for DateTime format
        JLabel dateTimeInstructionLabel = new JLabel("<html><i>Enter DateTime in format: yyyy-MM-dd HH:mm</i></html>");
        add(dateTimeInstructionLabel);  // Display instruction label below the DateTime input

        JButton addButton = new JButton("Add");

        // Use DateTimeFormatter for the friendly DateTime format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        addButton.addActionListener(e -> {
            String selectedType = (String) eventTypeComboBox.getSelectedItem();
            String dateText= dateField.getText();

            try {
                // Parse the input date using the formatter
                LocalDateTime eventDate = LocalDateTime.parse(dateText, formatter);

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

                this.dispose();  // Close the modal after adding the event
            } catch (DateTimeParseException ex) {
                // Show error if the date format is incorrect
                JOptionPane.showMessageDialog(this, "Invalid DateTime format. Please use yyyy-MM-dd HH:mm.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                // Show error if duration is not a valid number
                JOptionPane.showMessageDialog(this, "Duration must be a valid number.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(new JLabel("")); // Empty placeholder
        add(addButton);

        setSize(300, 250);
        setLocationRelativeTo(parent);
    }
}
