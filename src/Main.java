import builder.*;
import menu.CadastroMenu;
import menu.ConsultaMenu;
import menu.Menu;
import reader.TxtReader;
import repository.*;

import java.util.Arrays;

public class Main {
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
        Menu menu = new Menu(cadastroMenu, consultaMenu, AutomovelRepository.getInstance(), LocacaoRepository.getInstance(), ClienteRepository.getInstance(), reader);

        menu.mostrar();

    }
}
