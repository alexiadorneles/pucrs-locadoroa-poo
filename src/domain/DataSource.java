package domain;

import domain.automovel.*;
import domain.cliente.PessoaFisica;
import domain.cliente.PessoaJuridica;
import domain.locacao.Locacao;

import java.util.List;

public class DataSource {
    private List<Categoria> categorias;
    private List<Marca> marcas;
    private List<ModeloNacional> modelosNacionais;
    private List<ModeloInternacional> modelosInternacionais;
    private List<PessoaFisica> clientePF;
    private List<PessoaJuridica> clientePJ;
    private List<Locacao> locacoes;
    private List<Automovel> automoveis;

    public DataSource(List<Categoria> categorias, List<Marca> marcas, List<ModeloNacional> modelosNacionais, List<ModeloInternacional> modelosInternacionais, List<PessoaFisica> clientePF, List<PessoaJuridica> clientePJ, List<Locacao> locacoes, List<Automovel> automoveis) {
        this.categorias = categorias;
        this.marcas = marcas;
        this.modelosNacionais = modelosNacionais;
        this.modelosInternacionais = modelosInternacionais;
        this.clientePF = clientePF;
        this.clientePJ = clientePJ;
        this.locacoes = locacoes;
        this.automoveis = automoveis;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public List<Marca> getMarcas() {
        return marcas;
    }

    public List<ModeloNacional> getModelosNacionais() {
        return modelosNacionais;
    }

    public List<ModeloInternacional> getModelosInternacionais() {
        return modelosInternacionais;
    }

    public List<PessoaFisica> getClientePF() {
        return clientePF;
    }

    public List<PessoaJuridica> getClientePJ() {
        return clientePJ;
    }

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public List<Automovel> getAutomoveis() {
        return automoveis;
    }
}
