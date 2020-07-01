package factory;

import repository.Repository;

public abstract class BaseFactory<T, ID> implements Factory<T> {

    private final Repository<T, ID> repository;

    public BaseFactory(Repository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public void createFromTxt(String line) {
        this.repository.save(this.build(line));
    }

}
