public class Location implements ILocation {

    private int id;
    private String name;
    private boolean reservable;
    private boolean online;
    private int capacity;
    private ISchedule schedule;

    public Location(int id, String name, boolean reservable,
                    boolean online, int capacity,
                    ISchedule schedule) {

        this.id = id;
        this.name = name;
        this.reservable = reservable;
        this.online = online;
        this.capacity = capacity;
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
    public boolean isReservable() {
        return reservable;
    }

    @Override
    public ISchedule getSchedule() {
        return schedule;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean isOnline() {
        return online;
    }

    @Override
    public int compareTo(ILocation l) {

        if (this.id < l.getId())
            return -1;

        else if (this.id > l.getId())
            return 1;

        return 0;
    }
}