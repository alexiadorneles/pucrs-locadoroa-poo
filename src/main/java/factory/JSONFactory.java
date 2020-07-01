package factory;

import java.io.FileNotFoundException;

public interface JSONFactory<T> {
    T createFromJSON(String fileName) throws FileNotFoundException;

    T createToJSON();
}
