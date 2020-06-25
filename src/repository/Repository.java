package repository;

import java.util.List;
import java.util.function.Predicate;

public interface Repository<T, ID> {
    void save(T model);

    T findOne(ID id);

    List<T> filter(Predicate<? super T> fn);

    List<T> findAll();
}
