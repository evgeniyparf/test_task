package codeit.testtask;

import com.google.gson.*;

import java.lang.reflect.Type;

public class EventDeserializer implements JsonDeserializer<Event> {
    public Event deserialize(JsonElement json, Type typeOfT,
                             JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        return new Event(   object.get("eventName").getAsString(),
                            object.get("time").getAsString());
    }
}
