package codeit.testtask;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {

    private String filePath;

    private PriorityQueue<Event> events = new PriorityQueue<>(Comparator.comparing(Event::getTime));

    public Scheduler(String filePath) {
        this.filePath = filePath;
    }

    public void scheduleTasks() {
        fillQueue();
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        while(events.size() > 0) {
            Event event = events.poll();
            scheduler.schedule(event, event.getTime(), TimeUnit.MILLISECONDS);
        }

        scheduler.shutdown();
    }

    private void fillQueue() {
        List<Event> events = parseEventsFromJson();
        if(events == null) return;
        for(Event e : events) {
            this.events.offer(e);
        }
    }

    private List<Event> parseEventsFromJson() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Event.class, new EventDeserializer()).create();
        try (Reader reader = new FileReader(filePath)) {
            JsonArray jsonElements = new JsonParser().parse(reader).getAsJsonArray();
            return gson.fromJson(jsonElements, new TypeToken<List<Event>>(){}.getType());
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
