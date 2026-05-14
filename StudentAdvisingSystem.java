import java .util.*;
public class StudentAdvisingSystem
{
  public static void main (String[] args)
  {
    Scanner input = new Scanner(System.in);
    AdvisingSystem advisingSystem = new AdvisingSystem();
    int choice ;
    System.out.println("======================================\nWelcome to the Student Advising System!\n======================================");

    do
    {
      System.out.println("======================================\nPlease choose an option:\n1.Add a student\n2.Search for a student\n3.Delete a student\n4.Schedule a meeting/workshop\n5.Print meeting/workshop details\n6.Print All Students\n7.Print all meetings/workshops alphabetically\n8.Load Students from CSV\n9.Load Events from CSV\n10.Exit");
      System.out.println("Enter your choice: ");
      choice = input.nextInt();
      input.nextLine(); // consume the newline character

      while(choice !=1 && choice !=2 && choice !=3 && choice !=4 && choice !=5 && choice !=6 && choice !=7 && choice !=8&& choice !=9 && choice !=10)
      {
         System.out.println("Please enter a valid choice:");
         choice = input.nextInt();
         input.nextLine(); // consume the newline character

      }
      switch(choice)
      {
        case 1://Add a student
              System.out.println("Enter the student's name:");
              String name = input.nextLine();
              System.out.println("Enter the student's ID:");
              int id = input.nextInt();
              input.nextLine(); // consume the newline character
              System.out.println("Enter the student's email address:");
              String email = input.nextLine();
              System.out.println("Enter the student's major:");
              String major = input.nextLine();
              System.out.println("Enter the student's year level");
              int yearLevel = input.nextInt();
              input.nextLine(); // consume the newline character
              System.out.println("Enter any notes for the student:");
              String notes = input.nextLine();
              Student student = new Student(name, id, email,major,yearLevel, notes);
              boolean addedSuccessfully =  advisingSystem.addStudent(student);
              if(addedSuccessfully)
                 System.out.println("Student added successfully^-^");
              else 
                 System.out.println("Failed to add the student:(Student with this ID is already exists"); 
            break;
        case 2://Search for a student
              System.out.println("Enter search criteria");
              System.out.println("1.Email\n2.StudentID");
              System.out.println("Enter your choice: ");
              int searchChoice = input.nextInt();
              input.nextLine(); // consume the newline character
              while(searchChoice !=1 && searchChoice !=2 )
              {
                 System.out.println("Please enter a valid choice:");
                 searchChoice = input.nextInt();
                 input.nextLine(); // consume the newline character
              }
              switch(searchChoice)
              {
                case 1://search by email
                    System.out.println("Enter the student's email:");
                    String findByEmail = input.nextLine();
                    if(advisingSystem.searchStudentByEmail(findByEmail)!= null)
                      System.out.println("Student found ^-^\n"+advisingSystem.searchStudentByEmail(findByEmail).toString());
                    else
                      System.out.println("Student not found:(");
                    break;
                case 2://search by ID
                    System.out.println("Enter the student's ID:");
                    int findByID = input.nextInt();
                    if(advisingSystem.searchStudentById(findByID)!= null)
                      System.out.println("Student found ^-^\n"+advisingSystem.searchStudentById(findByID).toString());
                    else
                      System.out.println("Student not found:(");

                    break;    
              }

             
            break;
        case 3://Delete a student
               System.out.println("Enter the student's ID:");
               int deleteStudent = input.nextInt();
               boolean removedSuccessfully =  advisingSystem.deleteStudent(deleteStudent);
              if(removedSuccessfully)
                 System.out.println("Student removed successfully^-^");
              else 
                 System.out.println("Failed to remove the student:(Student with this ID is not found");
              
            break;
        case 4://Schedule a meeting/workshop
              System.out.println("Enter type:");
              System.out.println("1.Workshop\n2.Meeting");
              System.out.println("Enter your choice: ");
              int typeChoice = input.nextInt();
              input.nextLine(); // consume the newline character
              while(typeChoice !=1 && typeChoice !=2 )
              {
                 System.out.println("Please enter a valid choice:");
                 typeChoice = input.nextInt();
                 input.nextLine(); // consume the newline character
              }
              switch(typeChoice)
              {
                  case 1://schedule a workshop
                    System.out.println("Enter workshop title:");
                    String workshopTitle = input.nextLine();
                    System.out.println("Enter student IDs separated by a comma:");
                    String studentIds = input.nextLine();
                    String[] arrayID = studentIds.split(",");
                    int[] studentIdArray = new int[arrayID.length];
                    for (int i = 0; i < arrayID.length; i++) 
                        studentIdArray[i] = Integer.parseInt(arrayID[i]);
                    System.out.println("Enter workshop start date and time (MM/DD/YYYY HH:MM):");
                    String startWorkshopDateTime = input.nextLine();
                    DateTime startDateTime = new DateTime(startWorkshopDateTime);
                    System.out.println("Enter workshop end date and time (MM/DD/YYYY HH:MM):");
                    String endWorkshopDateTime = input.nextLine();
                    DateTime endDateTime = new DateTime(endWorkshopDateTime);
                    System.out.println("Enter workshop location:");
                    String workshopLocation = input.nextLine();
                    boolean scheduleWorkshopSuccess = advisingSystem.scheduleWorkshop(workshopTitle, startDateTime, endDateTime, workshopLocation, studentIdArray);
                    if(scheduleWorkshopSuccess)
                      System.out.println("Workshop scheduled successfully^-^");
                    else 
                      System.out.println("Failed to schedule the workshop");
              
                    break;
                  case 2://schedule a meeting
                    System.out.println("Enter meeting title:");
                    String meetingTitle = input.nextLine();
                    System.out.println("Enter student ID:");
                    int studentID= input.nextInt();
                    input.nextLine(); // consume the newline character
                    System.out.println("Enter meeting start date and time (MM/DD/YYYY HH:MM):");
                    String startMeetingDateTime = input.nextLine();
                    DateTime startDateTimeM = new DateTime(startMeetingDateTime);
                    System.out.println("Enter meeting end date and time (MM/DD/YYYY HH:MM):");
                    String endMeetingDateTime = input.nextLine();
                    DateTime endDateTimeM = new DateTime(endMeetingDateTime);
                    System.out.println("Enter meeting location:");
                    String meetingLocation = input.nextLine();
                    boolean scheduleMeetingSuccess = advisingSystem.scheduleMeeting(meetingTitle, startDateTimeM, endDateTimeM, meetingLocation, studentID);
                    if(scheduleMeetingSuccess)
                      System.out.println("Meeting scheduled successfully^-^");
                    else 
                      System.out.println("Failed to schedule the meeting");
              
                    break;    
              }
            break;
        case 5://Print meeting/workshop details
              System.out.println("Enter search criteria");
              System.out.println("1.Title\n2.Student name");
              System.out.println("Enter your choice: ");
              int printChoice = input.nextInt();
              input.nextLine(); // consume the newline character
              while(printChoice !=1 && printChoice !=2 )
              {
                 System.out.println("Please enter a valid choice:");
                 printChoice = input.nextInt();
                 input.nextLine(); // consume the newline character
              }

              switch(printChoice)
              {
                 case 1://print by title
                    System.out.println("Enter the event title:");
                    String printByTitle = input.nextLine();
                    advisingSystem.printEventDetailsByTitle(printByTitle);
                    break;
                case 2://print by student name
                    System.out.println("Enter the student name:");
                    String printByStudentName = input.nextLine();
                    advisingSystem.printEventDetailsByStudentName(printByStudentName);
                    break;
              }
            break;
        case 6://Print All Students
              advisingSystem.printAllStudents();
            break;
        case 7://Print all meetings/workshops alphabetically
              advisingSystem.printAllEventsAlphabetically();
            break;
        case 8://Load Students from CSV
              System.out.println("Enter the file path:");
              String studentFilePath = input.nextLine();
              if(advisingSystem.loadStudentsFromCSV(studentFilePath))
                 System.out.println("Students loaded successfully^-^");
              else 
                 System.out.println("Failed to load students:(Please check the file path and try again)");
              break;
        case 9://Load Events from CSV
              System.out.println("Enter the file path:");
              String eventFilePath = input.nextLine();
              if(advisingSystem.loadEventsFromCSV(eventFilePath))
                 System.out.println("Events loaded successfully^-^");
              else 
                 System.out.println("Failed to load events:(Please check the file path and try again)");
              break;    
        case 10://Exit
            System.out.println("Goodbye!\nHave a nice day^-^");
            System.exit(0);
             break;
              
        

      }

        
    }
    while(choice!=10);
    
  }
    
}
