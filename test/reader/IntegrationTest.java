package reader;

import domain.DataSource;
import domain.TestHelper;
import factory.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.*;

import java.io.IOException;
import java.util.Arrays;

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
        assertEquals(MarcaRepository.getInstance().findAll().size(), result.getMarcas().size());
        assertEquals(AutomovelRepository.getInstance().findAll().size(), result.getAutomoveis().size());
        assertEquals(ModeloRepository.getInstance().findAll().size(), result.getModelosInternacionais().size() + result.getModelosNacionais().size());
        assertEquals(LocacaoRepository.getInstance().findAll().size(), result.getLocacoes().size());
        assertEquals(ClienteRepository.getInstance().findAll().size(), result.getClientePF().size() + result.getClientePJ().size());
    }
}
