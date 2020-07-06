package repository;

import domain.automovel.Categoria;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class CategoriaRepository implements Repository<Categoria, Integer> {

    private final List<Categoria> categorias;
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
        IDGenerator.getInstance().registerTopFor(Categoria.class.getName(), model.getCodigo() + 1);
        this.categorias.add(model);
    }

    @Override
    public Categoria findOne(Integer codigo) {
        return this.categorias.stream().filter(categoria -> categoria.getCodigo().equals(codigo)).findFirst().orElse(null);
    }

    @Override
    public List<Categoria> filter(Predicate<? super Categoria> fn) {
        return this.categorias.stream().filter(fn).collect(toList());
    }

    @Override
    public List<Categoria> findAll() {
        return this.categorias;
    }

    @Override
    public void clear() {
        this.categorias.clear();
    }

    @Override
    public String toString() {
        String listaCategoria = "";
        for (Categoria categoria : categorias) {
            listaCategoria += categoria.toString() + '\n';
        }
        return listaCategoria;
    }
}
