package repository;

import domain.automovel.Automovel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class AutomovelRepository implements Repository<Automovel, String> {
    private static AutomovelRepository instance = null;
    private final List<Automovel> automoveis;

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

    @Override
    public List<Automovel> filter(Predicate<? super Automovel> fn) {
        return this.automoveis.stream().filter(fn).collect(toList());
    }

    @Override
    public List<Automovel> findAll() {
        return this.automoveis;
    }

    @Override
    public void clear() {
        this.automoveis.clear();
    }

    public boolean removeByPlaca(String placa) {
        return this.automoveis.remove(this.findOne(placa));
    }


    @Override
    public String toString() {
        String listaAutomoveis = "";
        for (Automovel auto : automoveis) {
            listaAutomoveis += auto.toString() + '\n';
        }
        return listaAutomoveis;
    }
}
