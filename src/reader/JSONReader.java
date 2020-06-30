package reader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public class JSONReader {
    private final Gson gson;

    public JSONReader() {
        this.gson = new Gson();
    }

    public <T> T read(String fileName, Type type) throws FileNotFoundException {
        return gson.fromJson(new FileReader(fileName), type);
    }

    public <T> void write(String fileName, T object) throws IOException {
        String json = gson.toJson(object);
        JsonWriter writer = new JsonWriter(new FileWriter(fileName));
        writer.jsonValue(json);
        writer.close();
    }
}
