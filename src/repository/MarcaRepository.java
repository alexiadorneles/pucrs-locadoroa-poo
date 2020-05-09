package repository;

import domain.automovel.Marca;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class MarcaRepository implements Repository<Marca> {
    private List<Marca> marcas = new ArrayList<>();
    private static MarcaRepository instance = null;

    private MarcaRepository() {
        this.marcas = new ArrayList<>();
    }

    public static MarcaRepository getInstance() {
        if (instance == null)
            instance = new MarcaRepository();

        return instance;
    }

    @Override
    public void save(Marca marca) {
        this.marcas.add(marca);
    }

    @Override
    public Marca findOne(String nome) {
        return this.marcas.stream().filter(marca -> marca.getNome().equals(nome)).findFirst().orElse(null);
    }

    @Override
    public List<Marca> filter(Predicate<? super Marca> fn) {
        return this.marcas.stream().filter(fn).collect(toList());
    }
}
