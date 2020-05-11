import domain.cliente.Cliente;
import domain.automovel.Automovel;
import domain.automovel.Categoria;
import domain.locacao.Locacao;
import domain.cliente.PessoaFisica;
import domain.cliente.PessoaJuridica;
import menu.CadastroMenu;
import menu.ConsultaMenu;
import menu.Menu;
import repository.*;

import java.util.List;

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
