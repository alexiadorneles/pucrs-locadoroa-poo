import menu.CadastroMenu;
import menu.ConsultaMenu;
import menu.Menu;
import repository.*;

public class Main {
    public static void main(String[] args) {
        CadastroMenu cadastroMenu = new CadastroMenu(ModeloRepository.getInstance(), AutomovelRepository.getInstance(), ClienteRepository.getInstance(),
                MarcaRepository.getInstance(), CategoriaRepository.getInstance()
        );

        ConsultaMenu consultaMenu = new ConsultaMenu(AutomovelRepository.getInstance(), LocacaoRepository.getInstance());

        Menu menu = new Menu(cadastroMenu, consultaMenu, AutomovelRepository.getInstance(), LocacaoRepository.getInstance(), ClienteRepository.getInstance());

        menu.mostrar();

    }
}
