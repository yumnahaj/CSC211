package datastructcureproject;

public abstract class Event implements IEvent {

    // HEY: Changed to exactly match how the tutor handles the variables!
    private final int eventId;
    private String title;
    
    // PDF Rule: Set start and end Date/Time values only in the constructor
    private final IDateTime startDateTime;
    private final IDateTime endDateTime;
    
    private String location;

    // HEY: We now accept the eventId (eID) as a parameter, just like your tutor did!
    public Event(int eventId, String title, IDateTime startDateTime, IDateTime endDateTime, String location) {
        this.eventId = eventId;
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.location = location;
    }

    // Returns the unique internal event ID.
    @Override
    public int getEventId() {
        return this.eventId;
    }

    // Returns the title of the event.
    @Override
    public String getTitle() {
        return this.title;
    }

    // Sets the title of the event.
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    // Returns the start date/time of the event.
    @Override
    public IDateTime getStartDateTime() {
        return this.startDateTime;
    }

    // Returns the end date/time of the event.
    @Override
    public IDateTime getEndDateTime() {
        return this.endDateTime;
    }

    // Returns the location of the event.
    @Override
    public String getLocation() {
        return this.location;
    }

    // Sets the location of the event.
    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    // Abstract method: Forces Meeting and Workshop to define how they check for students
    @Override
    public abstract boolean hasStudent(int studentId);

    // Compares events alphabetically by title (Case-insensitive to match tutor logic)
    @Override
    public int compareTo(IEvent other) {
        return this.title.compareToIgnoreCase(other.getTitle());
    }

    // Base formatting for the event
    @Override
    public String toString() {
        return "Event ID: " + this.eventId + "\n" +
               "Title: " + this.title + "\n" +
               "Start: " + this.startDateTime.format() + "\n" +
               "End: " + this.endDateTime.format() + "\n" +
               "Location: " + this.location + "\n";
    }
}