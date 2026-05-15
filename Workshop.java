
public class Workshop extends Event implements IWorkshop {
    
    private IStudentList students;

    public Workshop(int eventId, String title, IDateTime startDateTime, IDateTime endDateTime, String location) {
        super(eventId, title, startDateTime, endDateTime, location);
        this.students = new StudentList(); 
    }

    @Override
    public LinkedList<IStudent> getParticipants() {
        return this.students.getAll();
    }

    @Override
    public boolean addParticipant(IStudent student) {
        return this.students.add(student); 
    }

    @Override
    public boolean removeParticipantById(int studentId) {
        return this.students.deleteById(studentId);
    }

    @Override
    public boolean isEmpty() {
        return this.students.size() == 0;
    }

    @Override
    public boolean hasStudent(int studentId) {
        return this.students.findById(studentId) != null;
    }

    @Override
    public String toString() {
        String result = "Workshop ID: " + getEventId() + "\n" +
                        "Title: " + getTitle() + "\n" +
                        "Start: " + getStartDateTime().format() + "\n" +
                        "End: " + getEndDateTime().format() + "\n" +
                        "Location: " + getLocation() + "\n" +
                        "Participants:\n";
        
        LinkedList<IStudent> list = this.students.getAll();
        
        if (list.empty()) {
            result += "  [No students registered yet]\n";
            return result;
        }
        list.findFirst();
        while (!list.last()) {
            result += "  - " + list.retrieve().getName() + " (" + list.retrieve().getStudentId() + ")\n";
            list.findNext();
        }
        result += "  - " + list.retrieve().getName() + " (" + list.retrieve().getStudentId() + ")\n";
        
        return result;
    }
}
