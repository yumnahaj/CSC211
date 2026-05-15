public class Person implements IPerson {

    protected int id;
    protected String name;
    protected String email;
    protected ISchedule schedule;

    public Person(int id, String name, String email, ISchedule schedule) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.schedule = schedule;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public ISchedule getSchedule() {
        return schedule;
    }

    @Override
    public int compareTo(IPerson p) {

        if (this.id < p.getId())
            return -1;

        else if (this.id > p.getId())
            return 1;

        return 0;
    }
}