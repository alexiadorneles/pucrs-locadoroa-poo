package repository;

import automovel.Marca;

import java.util.ArrayList;
import java.util.List;

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
}
