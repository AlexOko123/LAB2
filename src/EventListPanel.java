import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EventListPanel extends JPanel {
    private ArrayList<Event> events;
    private JPanel displayPanel;

    public EventListPanel() {
        events = new ArrayList<>();
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(displayPanel), BorderLayout.CENTER);

        JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> showAddEventModal());
        this.add(addEventButton, BorderLayout.NORTH);
    }

    public void addEvent(Event event) {
        events.add(event);
        EventPanel eventPanel = new EventPanel(event);
        displayPanel.add(eventPanel);
        displayPanel.revalidate();
    }

    private void showAddEventModal() {
        JOptionPane.showMessageDialog(this, "Add Event Modal goes here.");
    }
}
