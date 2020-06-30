package factory;

import java.io.FileNotFoundException;

public interface JSONFactory<T> {
    T createFromJSON() throws FileNotFoundException;

    T createToJSON();
}
