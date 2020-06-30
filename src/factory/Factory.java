package factory;

public interface Factory<T> {
    void createFromTxt(String line);

    T build(String line);

    boolean verify(String type);
}
