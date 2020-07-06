package repository;

import domain.automovel.Categoria;
import domain.automovel.Marca;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class MarcaRepository implements Repository<Marca, Integer> {
    private final List<Marca> marcas;
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
        IDGenerator.getInstance().registerTopFor(Marca.class.getName(), marca.getCodigo() + 1);
        this.marcas.add(marca);
    }

    @Override
    public Marca findOne(Integer codigo) {
        return this.marcas.stream().filter(marca -> marca.getCodigo().equals(codigo)).findFirst().orElse(null);
    }

    @Override
    public List<Marca> filter(Predicate<? super Marca> fn) {
        return this.marcas.stream().filter(fn).collect(toList());
    }

    @Override
    public List<Marca> findAll() {
        return this.marcas;
    }

    @Override
    public void clear() {
        this.marcas.clear();
    }

    @Override
    public String toString() {
        String listaMarcas = "";
        for (Marca marca : marcas) {
            listaMarcas += marca.toString() + '\n';
        }
        return listaMarcas;
    }
}
