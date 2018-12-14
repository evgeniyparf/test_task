package codeit.testtask;

import java.time.temporal.ChronoField;
import java.util.Date;

public class Main {

    public static final long TIME_START = System.currentTimeMillis();

    public static void main(String[] args) {
        System.out.println("Started at " + TIME_START);
        Scheduler scheduler = new Scheduler("events.json");
        scheduler.scheduleTasks();
    }
}
