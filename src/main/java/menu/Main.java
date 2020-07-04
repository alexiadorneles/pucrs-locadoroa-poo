package menu;

import factory.*;
import javafx.application.Application;
import javafx.stage.Stage;
import menu.CadastroMenu;
import menu.ConsultaMenu;
import menu.Menu;
import reader.TxtReader;
import repository.*;

import java.util.Arrays;

public class Main extends Application {
    public static Menu menu;
    public static void main(String[] args) {
        CadastroMenu cadastroMenu = new CadastroMenu(ModeloRepository.getInstance(), AutomovelRepository.getInstance(), ClienteRepository.getInstance(),
                MarcaRepository.getInstance(), CategoriaRepository.getInstance()
        );

        ConsultaMenu consultaMenu = new ConsultaMenu(AutomovelRepository.getInstance(), LocacaoRepository.getInstance());
        TxtReader reader = new TxtReader(Arrays.asList(
                new AutomovelFactory(),
                new CategoriaFactory(),
                new ClienteFactory(),
                new LocacaoFactory(),
                new MarcaFactory(),
                new ModeloFactory()
        ));
        menu = new Menu(cadastroMenu, consultaMenu, AutomovelRepository.getInstance(), LocacaoRepository.getInstance(), ClienteRepository.getInstance(), reader);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        menu.start(stage);
    }
}
