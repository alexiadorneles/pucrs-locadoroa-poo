package repository;

import domain.locacao.Locacao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class LocacaoRepository implements Repository<Locacao> {
    private final List<Locacao> locacoes;

    private static LocacaoRepository instance = null;

    private LocacaoRepository() {
        this.locacoes = new ArrayList<>();
    }

    public static LocacaoRepository getInstance() {
        if (instance == null)
            instance = new LocacaoRepository();

        return instance;
    }


    @Override
    public void save(Locacao model) {
        this.locacoes.add(model);
    }

    @Override
    public Locacao findOne(String codigo) {
        return this.filter(locacao -> locacao.getCodigo().equals(codigo)).get(0);
    }

    @Override
    public List<Locacao> filter(Predicate<? super Locacao> fn) {
        return this.locacoes.stream().filter(fn).collect(toList());
    }

    @Override
    public List<Locacao> findAll() {
        return this.locacoes;
    }
}
