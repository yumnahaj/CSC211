public class DateTime implements IDateTime
{
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public DateTime(int year, int month, int day, int hour, int minute)//constructor
    {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }
       public DateTime(String dt) {
        // Example input: "05/20/2026 14:00"
        
        // Step 1: Split the string at the blank space.
        // parts[0] becomes "05/20/2026"
        // parts[1] becomes "14:00"
        String[] parts = dt.trim().split(" "); 
        
        // Step 2: Take the date part and split it at the slashes
        // dateParts[0] = "05", dateParts[1] = "20", dateParts[2] = "2026"
        String[] dateParts = parts[0].split("/");
        this.month = Integer.parseInt(dateParts[0]);
        this.day = Integer.parseInt(dateParts[1]);
        this.year = Integer.parseInt(dateParts[2]);
        
        // Step 3: Take the time part and split it at the colon
        // timeParts[0] = "14", timeParts[1] = "00"
        String[] timeParts = parts[1].split(":");
        this.hour = Integer.parseInt(timeParts[0]);
        this.minute = Integer.parseInt(timeParts[1]);
    }
    public int getYear()
    {
        return year;
    }
    
    public int getMonth()
    {
        return month;
    }

    public int getDay()
    {
        return day;
    }

    public int getHour()
    {
        return hour;
    }
    public int getMinute()
    {
        return minute;
    }
    public String format()
    {
        return String.format("%02d/%02d/%04d %02d:%02d", month, day, year, hour, minute);
    }

    public int compareTo(IDateTime other)
    {
        if (this.year != other.getYear())
           return this.year - other.getYear();
        else
          if (this.month != other.getMonth())
           return this.month - other.getMonth();
        else
          if (this.day != other.getDay())
           return this.day - other.getDay();
        else
           if (this.hour != other.getHour())
           return this.hour - other.getHour();
        else
           if (this.minute != other.getMinute())
           return this.minute - other.getMinute();
        else
           return 0;//if the all condethions are false and we reach this line thats mean they are equals


    }
}
