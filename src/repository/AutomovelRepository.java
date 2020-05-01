package repository;

import automovel.Automovel;

import java.util.ArrayList;
import java.util.List;

public class AutomovelRepository implements Repository<Automovel> {
    private static AutomovelRepository instance = null;
    private List<Automovel> automoveis;

    private AutomovelRepository() {
        this.automoveis = new ArrayList<>();
    }

    public static AutomovelRepository getInstance() {
        if (instance == null)
            instance = new AutomovelRepository();

        return instance;
    }

    @Override
    public void save(Automovel model) {
        this.automoveis.add(model);
    }

    @Override
    public Automovel findOne(String placa) {
        return this.automoveis.stream().filter(automovel -> automovel.getPlaca().equals(placa)).findFirst().orElse(null);
    }
}
