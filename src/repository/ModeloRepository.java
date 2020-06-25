package repository;

import domain.automovel.Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class ModeloRepository implements Repository<Modelo, Integer> {
    private static ModeloRepository instance = null;
    private final List<Modelo> modelos;

    private ModeloRepository() {
        this.modelos = new ArrayList<>();
    }

    public static ModeloRepository getInstance() {
        if (instance == null)
            instance = new ModeloRepository();

        return instance;
    }

    @Override
    public void save(Modelo model) {
        this.modelos.add(model);
    }

    @Override
    public Modelo findOne(Integer codigo) {
        return this.modelos.stream().filter(modelo -> modelo.getCodigo().equals(codigo)).findFirst().orElse(null);
    }

    @Override
    public List<Modelo> filter(Predicate<? super Modelo> fn) {
        return this.modelos.stream().filter(fn).collect(toList());
    }

    @Override
    public List<Modelo> findAll() {
        return this.modelos;
    }

    @Override
    public String toString() {
        String listaModelos = "";
        for (Modelo model : modelos) {
            listaModelos += model.toString() + '\n';
        }
        return listaModelos;
    }
}
