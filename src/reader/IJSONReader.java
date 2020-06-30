package reader;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;

public interface IJSONReader<T> {
    T read(String fileName, Type type) throws FileNotFoundException;

    String toJSON(T object);
}
