package datastructcureproject;

public class StudentList implements IStudentList {

    private LinkedList<IStudent> students;
    
    private int size; 

    public StudentList() {
        this.students = new LinkedList<IStudent>();
        this.size = 0;
    }

    @Override
    public boolean add(IStudent student) {
        if (student == null) return false;

        // If the list is empty, just drop it in.
        if (students.empty()) {
            students.insert(student); 
            size++;
            return true;
        }
        
        students.findFirst();
        
        // HEY: We traverse to find the right sorted spot AND check for duplicates in one pass.
        while (!students.last()) {
            if (students.retrieve().getStudentId() == student.getStudentId()) {
                return false; // Duplicate ID found!
            }
            
            // HEY: If the new student belongs BEFORE the current student:
            if (student.compareTo(students.retrieve()) < 0) {
                // THE SWAP TRICK: Because our custom list only has "insert after", 
                // we replace the current node's data with the new student, 
                // and then insert the old data right after it!
                IStudent temp = students.retrieve();
                students.update(student);
                students.insert(temp);
                size++;
                return true;
            }
            students.findNext();
        }

        // HEY: We exited the loop, so we must check the very last node!
        if (students.retrieve().getStudentId() == student.getStudentId()) {
            return false; 
        }
        
        // Final check on the last node for placement
        if (student.compareTo(students.retrieve()) < 0) {
            // Apply the Swap Trick on the last node
            IStudent temp = students.retrieve();
            students.update(student);
            students.insert(temp);
        } else {
            // It is the biggest ID, so it just goes at the very end
            students.insert(student); 
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
            
            // Optimization: Stop early if we pass where the ID should be!
            if (currentId > studentId) return null; 
            
            students.findNext();
        }
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
        LinkedList<IStudent> result = new LinkedList<IStudent>();
        if (students.empty() || major == null) return result;

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
        LinkedList<IStudent> result = new LinkedList<IStudent>();
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
                size--; // HEY: We must subtract from our manual size counter!
                return true;
            }
            // Optimization: stop early if we pass it
            if (currentId > studentId) return false;
            students.findNext();
        }
        if (studentId == students.retrieve().getStudentId()) {
            students.remove();
            size--; // HEY: Subtracting here too!
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        // HEY: We return our manual counter because the custom list has no getSize() method!
        return this.size;
    }
}
