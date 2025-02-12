//this is for adding new events
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddEventModal extends JDialog {
    public AddEventModal(JFrame parent) {
        super(parent, "Add Event", true);
        setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Event Name:");
        JTextField nameField = new JTextField();
        add(nameLabel);
        add(nameField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            // Event creation logic here (either a Deadline or Meeting)
            this.dispose();
        });

        add(new JLabel("")); // Empty placeholder
        add(addButton);

        setSize(300, 200);
        setLocationRelativeTo(parent);
    }
}
