import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

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

        // Add these lines in the constructor of your EventListPanel, after the other UI elements are initialized.
        JButton sortByNameButton = new JButton("Sort by Name");
        sortByNameButton.addActionListener(e -> {
            events.sort(Comparator.comparing(Event::getName));  // Sort events by name
            refreshEventList();  // Re-render the list
        });
        add(sortByNameButton, BorderLayout.SOUTH);  // Example, adjust positioning as needed

        JButton sortByReverseNameButton = new JButton("Sort by Reverse Name");
        sortByReverseNameButton.addActionListener(e -> {
            events.sort((e1, e2) -> e2.getName().compareTo(e1.getName()));  // Sort events by reverse name
            refreshEventList();
        });
        add(sortByReverseNameButton, BorderLayout.SOUTH);

        JButton sortByDateTimeButton = new JButton("Sort by DateTime");
        sortByDateTimeButton.addActionListener(e -> {
            events.sort(Comparator.comparing(Event::getDateTime));  // Sort events by dateTime
            refreshEventList();
        });
        add(sortByDateTimeButton, BorderLayout.SOUTH);

        JButton filterCompletedButton = new JButton("Filter Completed");
        filterCompletedButton.addActionListener(e -> {
            events.removeIf(event -> event instanceof Completable && ((Completable) event).isComplete());
            refreshEventList();
        });
        add(filterCompletedButton, BorderLayout.SOUTH);

        JButton filterDeadlineButton = new JButton("Filter Deadlines");
        filterDeadlineButton.addActionListener(e -> {
            events.removeIf(event -> event instanceof Deadline);
            refreshEventList();
        });
        add(filterDeadlineButton, BorderLayout.SOUTH);

        JButton filterMeetingButton = new JButton("Filter Meetings");
        filterMeetingButton.addActionListener(e -> {
            events.removeIf(event -> event instanceof Meeting);
            refreshEventList();
        });
        add(filterMeetingButton, BorderLayout.SOUTH);

        JButton removeAllFiltersButton = new JButton("Remove All Filters");
        removeAllFiltersButton.addActionListener(e -> {
            events.clear();
            EventPlanner.addDefaultEvents(this);  // You could add this method to reload the default events
            refreshEventList();
        });
        add(removeAllFiltersButton, BorderLayout.SOUTH);

    }
    // Define the refreshEventList method to update the display panel
    public void refreshEventList() {
        displayPanel.removeAll();
        for (Event event : events) {
            EventPanel eventPanel = new EventPanel(event);
            displayPanel.add(eventPanel);
        }
        displayPanel.revalidate();
        displayPanel.repaint();
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
