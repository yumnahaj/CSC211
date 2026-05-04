import java.util.LinkedList;

public abstract class StudentList implements IStudentList {

    private LinkedList<IStudent> students = new LinkedList<>();

    @Override
    public boolean add(IStudent student) {
        if (student == null) return false;

        for (IStudent current : students ) {
           

            // detect if duplicate ID
            if (current.getStudentId() == student.getStudentId()) {
                return false;
            }

            // insert sorted
            if (current.getStudentId() > student.getStudentId()) {
                students.add( student);
                return true;
            }
        }

        students.add(student);
        return true;
    }

    @Override
    public IStudent findById(int studentId) {
        for (IStudent s : students) {
            if (s.getStudentId() == studentId) {
                return s;
            }
        }
        return null;
    }

    @Override
    public LinkedList<IStudent> findByName(String fullName) {
        LinkedList<IStudent> result = new LinkedList<>();
        for (IStudent s : students) {
            if (s.getName().equals(fullName)) {
                result.add(s);
            }
        }
        return result;
    }

    @Override
    public LinkedList<IStudent> findByNameContains(String partialName) {
        LinkedList<IStudent> result = new LinkedList<>();
        String key = partialName.toLowerCase();

        for (IStudent s : students) {
            if (s.getName().toLowerCase().contains(key)) {
                result.add(s);
            }
        }
        return result;
    }

    @Override
    public IStudent findByEmail(String email) {
        for (IStudent s : students) {
            if (s.getEmail().equals(email)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public LinkedList<IStudent> findByMajor(String major) {
        LinkedList<IStudent> result = new LinkedList<>();
        for (IStudent s : students) {
            if (s.getMajor().equals(major)) {
                result.add(s);
            }
        }
        return result;
    }

    @Override
    public LinkedList<IStudent> findByYearLevel(int yearLevel) {
        LinkedList<IStudent> result = new LinkedList<>();
        for (IStudent s : students) {
            if (s.getYearLevel() == yearLevel) {
                result.add(s);
            }
        }
        return result;
    }

    @Override
    public LinkedList<IStudent> getAll() {
        return new LinkedList<>(students);
    }

    @Override
    public boolean deleteById(int studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId() == studentId) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return students.size();
    }
}