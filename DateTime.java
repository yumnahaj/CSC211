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