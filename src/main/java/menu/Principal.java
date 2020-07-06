package menu;

import domain.DataSource;
import factory.*;
import javafx.application.Application;
import javafx.stage.Stage;
import reader.JSONReader;
import reader.TxtReader;
import repository.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
public class Principal extends Application {
    public static Menu menu;

    public static void main(String[] args) {
        CadastroMenu cadastroMenu = new CadastroMenu(ModeloRepository.getInstance(), AutomovelRepository.getInstance(), ClienteRepository.getInstance(),
                MarcaRepository.getInstance(), CategoriaRepository.getInstance()
        );

        JSONReader<DataSource> jsonReader = new JSONReader<>();
        DataSourceFactory dataSourceFactory = new DataSourceFactory(jsonReader);

        ConsultaMenu consultaMenu = new ConsultaMenu(AutomovelRepository.getInstance(), LocacaoRepository.getInstance());
        TxtReader reader = new TxtReader(Arrays.asList(
                new AutomovelFactory(),
                new CategoriaFactory(),
                new ClienteFactory(),
                new LocacaoFactory(),
                new MarcaFactory(),
                new ModeloFactory()
        ));
        menu = new Menu(cadastroMenu, consultaMenu, AutomovelRepository.getInstance(), CategoriaRepository.getInstance(), LocacaoRepository.getInstance(), ClienteRepository.getInstance(), reader, jsonReader, dataSourceFactory);

        try {
            dataSourceFactory.createFromJSON("resources/db.json");
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("Sem base de dados presente");
            } else {
                System.out.println("Erro ao ler arquivos de dados");
            }
        }
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        menu.start(stage);
    }
}

