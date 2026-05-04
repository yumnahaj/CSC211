package datastructcureproject;

/**
 * Represents a Meeting event involving exactly one student.
 */
public class Meeting extends Event implements IMeeting {

    private IStudent student;

    // Constructor 1: When we schedule a meeting but don't have a student yet
    public Meeting(int eventId, String title, IDateTime startDateTime, IDateTime endDateTime, String location) {
        super(eventId, title, startDateTime, endDateTime, location);
        this.student = null;
    }

    // Constructor 2: When we schedule a meeting and assign the student immediately
    public Meeting(int eventId, String title, IDateTime startDateTime, IDateTime endDateTime, String location, IStudent student) {
        super(eventId, title, startDateTime, endDateTime, location);
        this.student = student;
    }

    @Override
    public IStudent getStudent() {
        return this.student;
    }

    @Override
    public void setStudent(IStudent student) {
        this.student = student;
    }

    @Override
    public boolean hasStudent(int studentId) {
        // Safely checks if the student exists and matches the ID
        return (this.student != null && this.student.getStudentId() == studentId);
    }

    @Override
    public String toString() {
        String studentInfo = (this.student != null) 
            ? this.student.getName() + " (" + this.student.getStudentId() + ")" 
            : "No student assigned";

        return "Meeting ID: " + getEventId() + "\n" +
               "Title: " + getTitle() + "\n" +
               "Student: " + studentInfo + "\n" +
               "Start: " + getStartDateTime().format() + "\n" +
               "End: " + getEndDateTime().format() + "\n" +
               "Location: " + getLocation() + "\n";
    }
}