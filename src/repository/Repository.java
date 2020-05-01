package repository;

import automovel.Marca;

public interface Repository<T> {
    void save(T model);

    T findOne(String nome);
}
