package factory;

public interface Factory<T> {
    void create(String line);

    T build(String line);

    boolean verify(String type);
}
