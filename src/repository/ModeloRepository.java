package repository;

import automovel.Modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloRepository implements Repository<Modelo> {
    private static ModeloRepository instance = null;
    private List<Modelo> modelos;

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
    public Modelo findOne(String nome) {
        return this.modelos.stream().filter(modelo -> modelo.getNome().equals(nome)).findFirst().orElse(null);
    }
}
