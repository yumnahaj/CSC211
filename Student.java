import java.util.LinkedList;

public abstract class Student implements IStudent {

   private String name;
   private final int studentId;
   private String email;
   private String major;
   private int yearLevel;
   private String notes;
   private LinkedList<IEvent> schedule = new LinkedList<>();

   public Student(String name, int studentId, String email,
                  String major, int yearLevel, String notes) {
      this.name = name;
      this.studentId = studentId;
      this.email = email;
      this.major = major;
      this.yearLevel = yearLevel;
      this.notes = notes;
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

   @Override
   public String getFirstName() {
      if (name == null || name.indexOf(" ")==-1) {
         return name;
      }
      return name.substring(0, name.indexOf(" "));
   }

   @Override
   public LinkedList<IEvent> getSchedule() {
      return schedule;
   }

   @Override
   public String toString() {
      return "Student{" +
             "Name='" + name + '\'' +
             ", Student ID=" + studentId +
             ", Email='" + email + '\'' +
             ", Major='" + major + '\'' +
             ", Year Level=" + yearLevel +
             ", Notes='" + notes + '\'' +
             '}';
   }

   @Override
   public int compareTo(IStudent other) {
      return Integer.compare(this.studentId, other.getStudentId());
   }
}