// HEY: I removed the "import java.util.LinkedList" - strictly forbidden!
// HEY: I removed "abstract" so the class actually works.

public class StudentList implements IStudentList {

    // HEY: Just declare it here.
    private LinkedList<IStudent> students;

    // HEY: This is where we initialize it. It's safer for custom lists!
    public StudentList() {
        this.students = new LinkedList<IStudent>();
    }

    @Override
    public boolean add(IStudent student) {
        if (student == null) return false;

        if (students.empty()) {
            students.insert(student); 
            return true;
        }
        
        students.findFirst();
        
        // HEY: Handling the case where the new student has the smallest ID.
        if (student.compareTo(students.retrieve()) < 0) {
            students.insertAtBegin(student);
            return true;
        }
        
        // HEY: We use a 'while' loop with .findNext() because custom lists don't support 'for' loops.
        // This checks for duplicate IDs while it finds the right spot (O(N) complexity).
        while (!students.last() && (student.compareTo(students.retrieve()) >= 0)) {
            if (student.getStudentId() == students.retrieve().getStudentId()) {
                return false; // Duplicate ID found!
            }
            students.findNext();
        }

        if (student.getStudentId() == students.retrieve().getStudentId()) {
            return false; 
        }
        
        // Final sorted insertion
        int comp = student.compareTo(students.retrieve());
        if (comp > 0) {
            students.insert(student);
        } else {
            students.insertBefore(student);
        }
        return true;
    }

    @Override
    public IStudent findById(int studentId) {
        if (students.empty()) return null;
        
        students.findFirst();
        while (!students.last()) {    
            int currentId = students.retrieve().getStudentId();
            if (currentId == studentId) return students.retrieve();
            
            // HEY: EARLY EXIT! If we pass the ID, we stop to save time.
            if (currentId > studentId) return null; 
            
            students.findNext();
        }
        if (studentId == students.retrieve().getStudentId()) return students.retrieve();
        return null;
    }

    @Override
    public LinkedList<IStudent> findByName(String fullName) {
        LinkedList<IStudent> result = new LinkedList<>();
        if (students.empty()) return result;

        students.findFirst();
        while (!students.last()) {
            if (students.retrieve().getName().equalsIgnoreCase(fullName)) {
                result.insert(students.retrieve());
            }
            students.findNext();
        }
        if (students.retrieve().getName().equalsIgnoreCase(fullName)) {
            result.insert(students.retrieve());
        }
        return result;
    }

    @Override
    public LinkedList<IStudent> findByNameContains(String partialName) {
        LinkedList<IStudent> result = new LinkedList<>();
        if (students.empty() || partialName == null) return result;

        // HEY: This must be case-insensitive per the rubric.
        String key = partialName.toLowerCase();

        students.findFirst();
        while (!students.last()) {   
            if (students.retrieve().getName().toLowerCase().contains(key)) {
                result.insert(students.retrieve());
            }
            students.findNext();
        }
        if (students.retrieve().getName().toLowerCase().contains(key)) {
            result.insert(students.retrieve());
        }
        return result;
    }

    @Override
    public IStudent findByEmail(String email) {
        if (students.empty()) return null;

        students.findFirst();
        while (!students.last()) {
            if (students.retrieve().getEmail().equalsIgnoreCase(email)) {
                return students.retrieve();
            }
            students.findNext();
        }
        if (students.retrieve().getEmail().equalsIgnoreCase(email)) {
            return students.retrieve();
        }
        return null;
    }

    @Override
    public LinkedList<IStudent> findByMajor(String major) {
        LinkedList<IStudent> result = new LinkedList<>();
        if (students.empty()) return result;

        students.findFirst();
        while (!students.last()) {
            if (students.retrieve().getMajor().equalsIgnoreCase(major)) {
                result.insert(students.retrieve());
            }
            students.findNext();
        }
        if (students.retrieve().getMajor().equalsIgnoreCase(major)) {
            result.insert(students.retrieve());
        }
        return result;
    }

    @Override
    public LinkedList<IStudent> findByYearLevel(int yearLevel) {
        LinkedList<IStudent> result = new LinkedList<>();
        if (students.empty()) return result;

        students.findFirst();
        while (!students.last()) {
            if (students.retrieve().getYearLevel() == yearLevel) {
                result.insert(students.retrieve());
            }
            students.findNext();
        }
        if (students.retrieve().getYearLevel() == yearLevel) {
            result.insert(students.retrieve());
        }
        return result;
    }

    @Override
    public LinkedList<IStudent> getAll() {
        // HEY: We manually copy the list to protect the original data.
        LinkedList<IStudent> copy = new LinkedList<>();
        if (students.empty()) return copy;

        students.findFirst();
        while (!students.last()) {
            copy.insert(students.retrieve());
            students.findNext();
        }
        copy.insert(students.retrieve());
        return copy;
    }

    @Override
    public boolean deleteById(int studentId) {
        if (students.empty()) return false;
        
        students.findFirst();
        while (!students.last()) {
            int currentId = students.retrieve().getStudentId();
            if (currentId == studentId) {
                students.remove(); // HEY: Custom list uses .remove() on the cursor.
                return true;
            }
            // EARLY EXIT for better average complexity!
            if (currentId > studentId) return false;
            students.findNext();
        }
        if (studentId == students.retrieve().getStudentId()) {
            students.remove();
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        // HEY: Using .getSize() from our custom list class.
        return students.getSize();
    }
}
