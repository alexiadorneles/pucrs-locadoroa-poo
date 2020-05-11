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

        PessoaFisica pf = new PessoaFisica("Pedro", "99793723", "089.765.456-78");       // 7
        PessoaJuridica pj = new PessoaJuridica("ACME", "3867-2345", "42.318.949/0001-84 "); // 8

        List<Cliente> clientes = ClienteRepository.getInstance().filter(cliente -> cliente.equals(pf));
        List<Cliente> clientes1 = ClienteRepository.getInstance().filter(cliente -> cliente.equals(pj));


        Locacao locacao = new Locacao(pf, "14/02/2020", "25/03/2020", null); // 9

        Categoria categoriaX = new Categoria("X");
        List<Automovel> automoveis = AutomovelRepository.getInstance()
                .filter(automovel -> automovel.getModelo().getCategoria().equals(categoriaX));


    }
}
