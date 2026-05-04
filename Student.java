// FIX 1: Deleted the forbidden 'import java.util.LinkedList;'

// FIX 2: Removed the 'abstract' keyword so the class can actually be instantiated
public class Student implements IStudent {

   private String name;
   private final int studentId;
   private String email;
   private String major;
   private int yearLevel;
   private String notes;
   
   // FIX 4: Only declare the list here. Don't use 'new LinkedList<>()' at the top.
   private LinkedList<IEvent> schedule;

   public Student(String name, int studentId, String email,
                  String major, int yearLevel, String notes) {
      this.name = name;
      this.studentId = studentId;
      this.email = email;
      this.major = major;
      this.yearLevel = yearLevel;
      this.notes = notes;
      
      // FIX 4: Initialize the custom list safely inside the constructor
      this.schedule = new LinkedList<IEvent>();
   }

   @Override
   public String getName() {
      return name;
   }

   @Override
   public void setName(String name) {
      this.name = name;
   }

   @Override
   public int getStudentId() {
      return studentId;
   }

   @Override
   public String getEmail() {
      return email;
   }

   @Override
   public void setEmail(String email) {
      this.email = email;
   }

   @Override
   public String getMajor() {
      return major;
   }

   @Override
   public void setMajor(String major) {
      this.major = major;
   }

   @Override
   public int getYearLevel() {
      return yearLevel;
   }

   @Override
   public void setYearLevel(int yearLevel) {
      this.yearLevel = yearLevel;
   }

   @Override
   public String getNotes() {
      return notes;
   }

   @Override
   public void setNotes(String notes) {
      this.notes = notes;
   }

   // Your friend did a GREAT job here making this crash-proof!
   @Override
   public String getFirstName() {
      if (name == null || name.indexOf(" ") == -1) {
         return name;
      }
      return name.substring(0, name.indexOf(" "));
   }

   @Override
   public LinkedList<IEvent> getSchedule() {
      return schedule;
   }

   // FIX 3: Replaced the auto-generated format with the exact Phase 1 Rubric format
   @Override
   public String toString() {
      return "Name: " + this.name + "\n" +
             "Student ID: " + this.studentId + "\n" +
             "Email Address: " + this.email + "\n" +
             "Major: " + this.major + "\n" +
             "Year Level: " + this.yearLevel + "\n" +
             "Notes: " + this.notes;
   }

   @Override
   public int compareTo(IStudent other) {
      return Integer.compare(this.studentId, other.getStudentId());
   }
}
