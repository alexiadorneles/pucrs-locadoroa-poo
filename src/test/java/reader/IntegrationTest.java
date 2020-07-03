package reader;

import domain.DataSource;
import domain.TestHelper;
import domain.automovel.Categoria;
import domain.automovel.Marca;
import domain.automovel.Modelo;
import domain.locacao.Locacao;
import factory.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {

    @BeforeEach
    public void setup() {
        TestHelper.getAllRepositories().forEach(Repository::clear);
    }

    @Test
    public void integradeLoadAndJSON() throws IOException {
        // arrange
        TxtReader txtReader = new TxtReader(Arrays.asList(
                new AutomovelFactory(),
                new CategoriaFactory(),
                new ClienteFactory(),
                new LocacaoFactory(),
                new MarcaFactory(),
                new ModeloFactory()
        ));

        JSONReader<DataSource> jsonReader = new JSONReader<>();
        DataSourceFactory dataSourceFactory = new DataSourceFactory(jsonReader);

        // act
        txtReader.read("carga");
        DataSource json = dataSourceFactory.createToJSON();
        jsonReader.write("resources/teste.json", json);
        TestHelper.getAllRepositories().forEach(Repository::clear);
        DataSource result = dataSourceFactory.createFromJSON("resources/teste.json");

        // assert
        assertEquals(CategoriaRepository.getInstance().findAll().size(), result.getCategorias().size());
        assertEquals(IDGenerator.getInstance().getIdFor(Categoria.class.getName()), CategoriaRepository.getInstance().findAll().size());
        assertEquals(MarcaRepository.getInstance().findAll().size(), result.getMarcas().size());
        assertEquals(IDGenerator.getInstance().getIdFor(Marca.class.getName()), MarcaRepository.getInstance().findAll().size());
        assertEquals(AutomovelRepository.getInstance().findAll().size(), result.getAutomoveis().size());
        assertEquals(ModeloRepository.getInstance().findAll().size(), result.getModelosInternacionais().size() + result.getModelosNacionais().size());
        assertEquals(IDGenerator.getInstance().getIdFor(Modelo.class.getName()), ModeloRepository.getInstance().findAll().size());
        assertEquals(LocacaoRepository.getInstance().findAll().size(), result.getLocacoes().size());
        assertEquals(IDGenerator.getInstance().getIdFor(Locacao.class.getName()), LocacaoRepository.getInstance().findAll().size());
        assertEquals(ClienteRepository.getInstance().findAll().size(), result.getClientePF().size() + result.getClientePJ().size());
    }
}
