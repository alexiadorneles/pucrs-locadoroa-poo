package repository;

import java.util.List;
import java.util.function.Predicate;

public interface Repository<T> {
    void save(T model);

    T findOne(String nome);

    List<T> filter(Predicate<? super T> fn);

    List<T> findAll();
}
