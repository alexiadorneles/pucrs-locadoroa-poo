package factory;

import domain.DataSource;
import domain.automovel.*;
import domain.cliente.PessoaFisica;
import domain.cliente.PessoaJuridica;
import domain.locacao.Locacao;
import reader.IJSONReader;
import repository.*;

import java.io.FileNotFoundException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DataSourceFactory implements JSONFactory<DataSource> {
    private IJSONReader<DataSource> reader;

    public DataSourceFactory(IJSONReader<DataSource> reader) {
        this.reader = reader;
    }


    @Override
    public DataSource createFromJSON() throws FileNotFoundException {
        DataSource data = reader.read("resources/db.json", DataSource.class);
        data.getCategorias().forEach(categoria -> CategoriaRepository.getInstance().save(categoria));
        data.getMarcas().forEach(marca -> MarcaRepository.getInstance().save(marca));
        data.getModelosInternacionais().forEach(modelo -> ModeloRepository.getInstance().save(modelo));
        data.getModelosNacionais().forEach(modelo -> ModeloRepository.getInstance().save(modelo));
        data.getAutomoveis().forEach(automovel -> AutomovelRepository.getInstance().save(automovel));
        data.getClientePF().forEach(cliente -> ClienteRepository.getInstance().save(cliente));
        data.getClientePJ().forEach(cliente -> ClienteRepository.getInstance().save(cliente));
        data.getLocacoes().forEach(locacao -> LocacaoRepository.getInstance().save(locacao));
        return data;
    }

    @Override
    public String generateJSON() {
        List<Categoria> categorias = CategoriaRepository.getInstance().findAll();
        List<Marca> marcas = MarcaRepository.getInstance().findAll();
        List<Automovel> automoveis = AutomovelRepository.getInstance().findAll();
        List<ModeloInternacional> modelosInternacionais = ModeloRepository.getInstance()
                .findAll()
                .stream()
                .filter(modelo -> modelo instanceof ModeloInternacional)
                .map(modelo -> (ModeloInternacional) modelo)
                .collect(toList());
        List<ModeloNacional> modelosNacionais = ModeloRepository.getInstance()
                .findAll()
                .stream()
                .filter(modelo -> modelo instanceof ModeloNacional)
                .map(modelo -> (ModeloNacional) modelo)
                .collect(toList());
        List<PessoaFisica> clientesPF = ClienteRepository.getInstance()
                .findAll()
                .stream()
                .filter(cliente -> cliente instanceof PessoaFisica)
                .map(cliente -> (PessoaFisica) cliente)
                .collect(toList());
        List<PessoaJuridica> clientesPJ = ClienteRepository.getInstance()
                .findAll()
                .stream()
                .filter(cliente -> cliente instanceof PessoaJuridica)
                .map(cliente -> (PessoaJuridica) cliente)
                .collect(toList());
        List<Locacao> locacoes = LocacaoRepository.getInstance().findAll();
        DataSource dataSource = new DataSource(
                categorias,
                marcas,
                modelosNacionais,
                modelosInternacionais,
                clientesPF,
                clientesPJ,
                locacoes,
                automoveis
        );
        return this.reader.toJSON(dataSource);
    }
}
