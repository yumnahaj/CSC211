package datastructcureproject;

import java.io.File;
import java.util.Scanner;

public class AdvisingSystem implements IAdvisingSystem {

    private IStudentList studentList;
    private IEventList eventList;
    private int eventIdCounter; 

    public AdvisingSystem() {
        this.studentList = new StudentList();
        this.eventList = new EventList(); 
        this.eventIdCounter = 1; 
    }

    @Override
    public boolean loadStudentsFromCSV(String studentsFilePath) {
        try {
            File file = new File(studentsFilePath);
            Scanner reader = new Scanner(file);
            
            // Skip the header line directly
            String line = reader.nextLine(); 

            while (reader.hasNextLine()) {
                line = reader.nextLine();
                String[] data = line.split(",");
                
                // Direct parsing without length checks
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                String email = data[2].trim();
                String major = data[3].trim();
                int year = Integer.parseInt(data[4].trim());
                String notes = data[5].trim();

                IStudent newStudent = new Student(name, id, email, major, year, notes);
                this.addStudent(newStudent); 
            }
            reader.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error reading Students CSV: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean loadEventsFromCSV(String eventsFilePath) {
        try {
            File file = new File(eventsFilePath);
            Scanner reader = new Scanner(file);
            
            // Skip the header line directly
            String line = reader.nextLine(); 

            while (reader.hasNextLine()) {
                line = reader.nextLine();
                String[] data = line.split(",");
                
                // Direct parsing without length checks
                int eventId = Integer.parseInt(data[0].trim());
                String title = data[1].trim();
                String type = data[2].trim();
                int studentId = Integer.parseInt(data[3].trim());
                
                // Uses the string parser constructor
                IDateTime start = new DateTime(data[4].trim());
                IDateTime end = new DateTime(data[5].trim());
                String loc = data[6].trim();

                // Dynamic ID Counter
                if (eventId >= eventIdCounter) {
                    eventIdCounter = eventId + 1;
                }

                IStudent student = studentList.findById(studentId);
                if (student == null) continue; // Skip if student doesn't exist

                if (hasConflict(student, start, end)) continue; // Check for conflicts

                IEvent existingEvent = findEventByIdHelper(eventId);

                if (existingEvent != null && existingEvent instanceof IWorkshop) {
                    IWorkshop ws = (IWorkshop) existingEvent;
                    ws.addParticipant(student);
                    student.getSchedule().insert(ws); 
                } else {
                    if (type.equalsIgnoreCase("Meeting")) {
                        IMeeting meeting = new Meeting(eventId, title, start, end, loc, student);
                        eventList.addEvent(meeting);
                        student.getSchedule().insert(meeting);
                    } else if (type.equalsIgnoreCase("Workshop")) {
                        IWorkshop workshop = new Workshop(eventId, title, start, end, loc);
                        workshop.addParticipant(student);
                        eventList.addEvent(workshop);
                        student.getSchedule().insert(workshop);
                    }
                }
            }
            reader.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error reading Events CSV: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean addStudent(IStudent student) {
        return studentList.add(student);
    }

    @Override
    public IStudent searchStudentById(int studentId) {
        return studentList.findById(studentId);
    }

    @Override
    public IStudent searchStudentByEmail(String email) {
        return studentList.findByEmail(email);
    }

    @Override
    public void printStudentsByMajor(String major) {
        printStudentList(studentList.findByMajor(major));
    }

    @Override
    public void printStudentsByYearLevel(int yearLevel) {
        printStudentList(studentList.findByYearLevel(yearLevel));
    }

    @Override
    public void printStudentsByName(String fullName) {
        printStudentList(studentList.findByName(fullName));
    }

    @Override
    public void printStudentsByPartialName(String partialName) {
        printStudentList(studentList.findByNameContains(partialName));
    }

    @Override
    public void printAllStudents() {
        printStudentList(studentList.getAll());
    }

    @Override
 /*   public boolean deleteStudent(int studentId) {
        IStudent student = studentList.findById(studentId);
        if (student == null) return false;

        LinkedList<IEvent> allEvents = eventList.getAllAlphabetically();
        
        if (!allEvents.empty()) {
            allEvents.findFirst();
            while (!allEvents.last()) {
                handleCascadeDeletion(allEvents.retrieve(), studentId);
                allEvents.findNext();
            }
            handleCascadeDeletion(allEvents.retrieve(), studentId);
        }
        return studentList.deleteById(studentId);
    } 
*/
  public boolean scheduleMeeting(String title, IDateTime startDateTime, IDateTime endDateTime, String location, int studentId) {
        IStudent student = studentList.findById(studentId);
        if (student == null) return false; 
        if (hasConflict(student, startDateTime, endDateTime)) return false; 

        IMeeting newMeeting = new Meeting(eventIdCounter++, title, startDateTime, endDateTime, location, student);
        
        if (eventList.addEvent(newMeeting)) {
            student.getSchedule().insert(newMeeting);
            return true;
        }
        return false;
    }

    public boolean deleteStudent(int studentId) {
        IStudent student = studentList.findById(studentId);
        if (student == null) {
            return false; // Safely return false if student doesn't exist
        }

        // 1. Get a temporary list of ONLY this student's events to loop through safely
        LinkedList<IEvent> studentEvents = eventList.findByStudentName(student.getName());
        
        if (!studentEvents.empty()) {
            studentEvents.findFirst();
            while (!studentEvents.last()) {
                handleCascadeDeletion(studentEvents.retrieve(), studentId);
                studentEvents.findNext();
            }
            // Handle the final element
            handleCascadeDeletion(studentEvents.retrieve(), studentId);
        }

        // 2. Finally, delete the student from the main list
        return studentList.deleteById(studentId);
    }
    public boolean scheduleWorkshop(String title, IDateTime startDateTime, IDateTime endDateTime, String location, int[] studentIds) {
        for (int i = 0; i < studentIds.length; i++) {
            if (studentList.findById(studentIds[i]) == null) return false;
        }

        for (int i = 0; i < studentIds.length; i++) {
            IStudent student = studentList.findById(studentIds[i]);
            if (hasConflict(student, startDateTime, endDateTime)) return false;
        }

        IWorkshop newWorkshop = new Workshop(eventIdCounter++, title, startDateTime, endDateTime, location);

        for (int i = 0; i < studentIds.length; i++) {
            IStudent student = studentList.findById(studentIds[i]);
            newWorkshop.addParticipant(student);
            student.getSchedule().insert(newWorkshop);
        }

        return eventList.addEvent(newWorkshop);
    }

    @Override
    public void printEventDetailsByTitle(String title) {
        printEventList(eventList.findByTitle(title));
    }

    @Override
    public void printEventDetailsByStudentName(String studentName) {
        printEventList(eventList.findByStudentName(studentName));
    }

    @Override
    public void printWorkshopStudents(String workshopTitle) {
        LinkedList<IEvent> matches = eventList.findByTitle(workshopTitle);
        if (matches == null || matches.empty()) {
            System.out.println("No workshop found.");
            return;
        }
        
        matches.findFirst();
        while (!matches.last()) {
            if (matches.retrieve() instanceof IWorkshop) {
                System.out.println(matches.retrieve().toString());
            }
            matches.findNext();
        }
        if (matches.retrieve() instanceof IWorkshop) {
            System.out.println(matches.retrieve().toString());
        }
    }

    @Override
    public void printAllEventsAlphabetically() {
        printEventList(eventList.getAllAlphabetically());
    }

    // ==========================================
    // PRIVATE HELPERS 
    // ==========================================

    private void printStudentList(LinkedList<IStudent> list) {
        if (list == null || list.empty()) {
            System.out.println("No students found.");
            return;
        }
        list.findFirst();
        while (!list.last()) {
            System.out.println(list.retrieve().toString());
            list.findNext();
        }
        System.out.println(list.retrieve().toString());
    }

    private void printEventList(LinkedList<IEvent> list) {
        if (list == null || list.empty()) {
            System.out.println("No events found.");
            return;
        }
        list.findFirst();
        while (!list.last()) {
            System.out.println(list.retrieve().toString());
            list.findNext();
        }
        System.out.println(list.retrieve().toString());
    }

    private void handleCascadeDeletion(IEvent event, int studentId) {
        if (event.hasStudent(studentId)) {
            if (event instanceof IMeeting) {
                eventList.removeEventById(event.getEventId());
            } else if (event instanceof IWorkshop) {
                IWorkshop workshop = (IWorkshop) event;
                workshop.removeParticipantById(studentId);
                if (workshop.isEmpty()) {
                    eventList.removeEventById(event.getEventId());
                }
            }
        }
    }

    private boolean hasConflict(IStudent student, IDateTime newStart, IDateTime newEnd) {
        LinkedList<IEvent> schedule = student.getSchedule();
        if (schedule == null || schedule.empty()) return false;

        schedule.findFirst();
        while (!schedule.last()) {
            IEvent existing = schedule.retrieve();
            if (isOverlapping(existing.getStartDateTime(), existing.getEndDateTime(), newStart, newEnd)) {
                return true;
            }
            schedule.findNext();
        }
        IEvent existing = schedule.retrieve();
        return isOverlapping(existing.getStartDateTime(), existing.getEndDateTime(), newStart, newEnd);
    }

    private boolean isOverlapping(IDateTime start1, IDateTime end1, IDateTime start2, IDateTime end2) {
        return (start1.compareTo(end2) < 0) && (end1.compareTo(start2) > 0);
    }

    private IEvent findEventByIdHelper(int eventId) {
        LinkedList<IEvent> all = eventList.getAllAlphabetically();
        if (all.empty()) return null;
        all.findFirst();
        while (!all.last()) {
            if (all.retrieve().getEventId() == eventId) return all.retrieve();
            all.findNext();
        }
        if (all.retrieve().getEventId() == eventId) return all.retrieve();
        return null;
    }
}
