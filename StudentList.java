package datastructcureproject;

public class StudentList implements IStudentList {

    private LinkedList<IStudent> students;
    private int size; // Manual counter (LinkedList ADT has no size method)

    public StudentList() {
        this.students = new LinkedList<IStudent>();
        this.size = 0;
    }

    @Override
    public boolean add(IStudent student) {
        if (student == null) return false;

        // Base case: first element
        if (students.empty()) {
            students.insert(student); 
            size++;
            return true;
        }
        
        students.findFirst();
        
        // Search for correct sorted position
        while (!students.last()) {
            if (students.retrieve().getStudentId() == student.getStudentId()) return false; // No duplicates
            
            // Swap Trick: needed because ADT only supports "insert after"
            if (student.compareTo(students.retrieve()) < 0) {
                IStudent temp = students.retrieve();
                students.update(student);
                students.insert(temp);
                size++;
                return true;
            }
            students.findNext();
        }

        // Post-loop: Check the final node (since .last() stops the loop)
        if (students.retrieve().getStudentId() == student.getStudentId()) return false; 
        
        if (student.compareTo(students.retrieve()) < 0) {
            IStudent temp = students.retrieve();
            students.update(student);
            students.insert(temp);
        } else {
            students.insert(student); // New largest ID goes at the end
        }
        
        size++;
        return true;
    }

    

    @Override
    public IStudent findById(int studentId) {
        if (students.empty()) return null;
        
        students.findFirst();
        while (!students.last()) {    
            int currentId = students.retrieve().getStudentId();
            if (currentId == studentId) return students.retrieve();
            
            // Optimization: Stop early if we pass the target ID
            if (currentId > studentId) return null; 
            
            students.findNext();
        }
        // Final node check
        if (studentId == students.retrieve().getStudentId()) return students.retrieve();
        return null;
    }

    @Override
    public LinkedList<IStudent> findByName(String fullName) {
        LinkedList<IStudent> result = new LinkedList<IStudent>();
        if (students.empty() || fullName == null) return result;

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

    

[Image of linear search flowchart]


    @Override
    public LinkedList<IStudent> findByNameContains(String partialName) {
        LinkedList<IStudent> result = new LinkedList<IStudent>();
        if (students.empty() || partialName == null) return result;

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
        if (students.empty() || email == null) return null;

        students.findFirst();
        while (!students.last()) {
            if (students.retrieve().getEmail().equalsIgnoreCase(email)) return students.retrieve();
            students.findNext();
        }
        if (students.retrieve().getEmail().equalsIgnoreCase(email)) return students.retrieve();
        return null;
    }

    @Override
    public LinkedList<IStudent> findByMajor(String major) {
        LinkedList<IStudent> result = new LinkedList<IStudent>();
        if (students.empty() || major == null) return result;

        students.findFirst();
        while (!students.last()) {
            if (students.retrieve().getMajor().equalsIgnoreCase(major)) result.insert(students.retrieve());
            students.findNext();
        }
        if (students.retrieve().getMajor().equalsIgnoreCase(major)) result.insert(students.retrieve());
        return result;
    }

    @Override
    public LinkedList<IStudent> findByYearLevel(int yearLevel) {
        LinkedList<IStudent> result = new LinkedList<IStudent>();
        if (students.empty()) return result;

        students.findFirst();
        while (!students.last()) {
            if (students.retrieve().getYearLevel() == yearLevel) result.insert(students.retrieve());
            students.findNext();
        }
        if (students.retrieve().getYearLevel() == yearLevel) result.insert(students.retrieve());
        return result;
    }

    @Override
    public LinkedList<IStudent> getAll() {
        LinkedList<IStudent> copy = new LinkedList<IStudent>();
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
                students.remove(); 
                size--; // Keep manual counter in sync
                return true;
            }
            if (currentId > studentId) return false; // Early exit for sorted list
            students.findNext();
        }
        // Last node deletion check
        if (studentId == students.retrieve().getStudentId()) {
            students.remove();
            size--; 
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size; // Returns O(1) manual count
    }
}
