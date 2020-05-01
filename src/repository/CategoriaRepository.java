package repository;

import automovel.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaRepository implements Repository<Categoria> {

    private List<Categoria> categorias;
    private static CategoriaRepository instance = null;

    private CategoriaRepository() {
        this.categorias = new ArrayList<>();
    }

    public static CategoriaRepository getInstance() {
        if (instance == null)
            instance = new CategoriaRepository();

        return instance;
    }

    @Override
    public void save(Categoria model) {
        this.categorias.add(model);
    }

    @Override
    public Categoria findOne(String nome) {
        return this.categorias.stream().filter(categoria -> categoria.getNome().equals(nome)).findFirst().orElse(null);
    }
}
