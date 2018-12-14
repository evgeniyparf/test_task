package codeit.testtask;

public class Event implements Runnable {

    private String eventName;
    private long time;

    public Event(String eventName, String stringTime) {
        this.eventName = eventName;
        this.time = convertToMilliseconds(stringTime);
    }

    @Override
    public void run() {
        System.out.println(eventName + " { scheduled: " + time + ", executed: " + (System.currentTimeMillis() - Main.TIME_START) + " }");
    }

    private static long convertToMilliseconds(String stringTime) {
        String[] time = stringTime.split(":");
        int mm = Integer.parseInt(time[0]);
        int ss = Integer.parseInt(time[1]);
        return (mm * 60000) + (ss * 1000);
    }

    @Override
    public String toString() {
        return "Event { name: '" + eventName + "' time: " + time + " }";
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(String stringTime) {
        this.time = convertToMilliseconds(stringTime);
    }
}
