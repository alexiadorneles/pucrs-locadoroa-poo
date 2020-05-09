import domain.automovel.Automovel;
import domain.automovel.Categoria;
import domain.Locacao;
import domain.PessoaF;
import domain.PessoaJ;
import repository.AutomovelRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.mostrar();

       PessoaF pf = new PessoaF("Pedro", "99793723", "089.765.456-78");       // 7
       PessoaJ pj = new PessoaJ("ACME", "3867-2345", "42.318.949/0001-84 "); // 8


        Locacao locacao = new Locacao(pf, "14/02/2020", "25/03/2020", null);
        System.out.println(locacao.calculaDiasDiaria());

        Categoria categoriaX = new Categoria("X");
        List<Automovel> automoveis = AutomovelRepository.getInstance()
                .filter(automovel -> automovel.getModelo().getCategoria().equals(categoriaX));

    }
}
