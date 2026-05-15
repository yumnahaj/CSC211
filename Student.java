public class Student extends Person implements IStudent {

    private String major;

    public Student(int id, String name, String email,
                   ISchedule schedule, String major) {

        super(id, name, email, schedule);
        this.major = major;
    }

    @Override
    public String getMajor() {
        return major;
    }
}