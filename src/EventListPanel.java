import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class EventListPanel extends JPanel {
    private ArrayList<Event> events;
    private ArrayList<Event> originalEvents;
    private JPanel displayPanel;
    private JPanel buttonPanel;  // New panel to hold buttons

    public EventListPanel() {
        events = new ArrayList<>();
        originalEvents = new ArrayList<>(); // Initialize the original events list
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

        this.setLayout(new BorderLayout());

        // Create a panel for buttons with BoxLayout
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Stack buttons vertically

        // Add buttons to the button panel
        JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> showAddEventModal());
        buttonPanel.add(addEventButton);

        // Sort by Name Button
        JButton sortByNameButton = new JButton("Sort by Name");
        sortByNameButton.addActionListener(e -> sortEvents(Comparator.comparing(Event::getName)));
        buttonPanel.add(sortByNameButton);

        // Sort by Reverse Name Button
        JButton sortByReverseNameButton = new JButton("Sort by Reverse Name");
        sortByReverseNameButton.addActionListener(e -> sortEvents((e1, e2) -> e2.getName().compareTo(e1.getName())));
        buttonPanel.add(sortByReverseNameButton);

        // Sort by DateTime Button
        JButton sortByDateTimeButton = new JButton("Sort by DateTime");
        sortByDateTimeButton.addActionListener(e -> sortEvents(Comparator.comparing(Event::getDateTime)));
        buttonPanel.add(sortByDateTimeButton);

        // Filter Completed Button
        JButton filterCompletedButton = new JButton("Filter Completed");
        filterCompletedButton.addActionListener(e -> {
            // Remove completed events from the list
            events.removeIf(event -> event instanceof Completable && ((Completable) event).isComplete());
            refreshEventList();  // Refresh the event list to update the display
        });
        buttonPanel.add(filterCompletedButton);

        // Filter Deadlines Button
        JButton filterDeadlineButton = new JButton("Filter Deadlines");
        filterDeadlineButton.addActionListener(e -> {
            // Remove Deadline events from the list
            events.removeIf(event -> event instanceof Deadline);
            refreshEventList();  // Refresh the event list to update the display
        });
        buttonPanel.add(filterDeadlineButton);

        // Filter Meetings Button
        JButton filterMeetingButton = new JButton("Filter Meetings");
        filterMeetingButton.addActionListener(e -> {
            // Remove Meeting events from the list
            events.removeIf(event -> event instanceof Meeting);
            refreshEventList();  // Refresh the event list to update the display
        });
        buttonPanel.add(filterMeetingButton);

        // Remove All Filters Button (reset all filters and show all events)
        JButton removeAllFiltersButton = new JButton("Remove All Filters");
        removeAllFiltersButton.addActionListener(e -> {

            //Restore the original set of events
            restoreOriginalEvents();
        });
        buttonPanel.add(removeAllFiltersButton);

        // Add button panel to the south region
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Display panel for events
        this.add(new JScrollPane(displayPanel), BorderLayout.CENTER);
    }

    private void restoreOriginalEvents() {
        // Restore the original events list and refresh the display
        events.clear();
        events.addAll(originalEvents); // Restore all events
        refreshEventList();  // Refresh the event list to show all events
    }

    // Helper method to sort events
    private void sortEvents(Comparator<Event> comparator) {
        events.sort(comparator);
        refreshEventList();
    }

    // Refresh the event list display
    public void refreshEventList() {
        displayPanel.removeAll();  // Clear existing panels
        for (Event event : events) {
            EventPanel eventPanel = new EventPanel(event);  // Create a new panel for each event
            displayPanel.add(eventPanel);
        }
        displayPanel.revalidate();  // Revalidate the panel to reflect changes
        displayPanel.repaint();  // Repaint the panel to refresh UI
    }

    // Add new event to the list
    public void addEvent(Event event) {
        events.add(event);
        originalEvents.add(event);
        refreshEventList();  // Refresh the event list to show newly added event
    }

    // Display modal to add event (this can be improved later)
    private void showAddEventModal() {
        JOptionPane.showMessageDialog(this, "Add Event Modal goes here.");
    }


}
