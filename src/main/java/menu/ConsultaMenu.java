package menu;

import domain.automovel.Automovel;
import domain.automovel.Categoria;
import domain.cliente.Cliente;
import domain.locacao.Locacao;
import repository.*;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ConsultaMenu {

    private final Repository<Automovel, String> automovelRepository;
    private final Repository<Locacao, Integer> locacaoRepository;

    public ConsultaMenu(Repository<Automovel, String> automovelRepository, Repository<Locacao, Integer> locacaoRepository) {
        this.automovelRepository = automovelRepository;
        this.locacaoRepository = locacaoRepository;
    }

    public void consultarValorLocacao(Scanner in) {
        System.out.println("Essas são as locações disponíveis no sistema: ");
        this.consultarLocacoes();
        Locacao locacao;
        do {
            System.out.println("Por favor digite o código de uma locação pra consultar seu valor total: ");
            locacao = this.locacaoRepository.findOne(Integer.valueOf(in.nextLine()));
        } while (Objects.isNull(locacao));

        System.out.println("O valor da sua locação é: " + locacao.calcularValorLocacao());
    }

    public void consultarLocacoes() {
        this.locacaoRepository.findAll().forEach(System.out::println);
    }

    public void consultarAutomoveis() {
        List<Automovel> automoveis = this.automovelRepository.findAll();
        automoveis.forEach(System.out::println);
    }

    public boolean consultaDisponibilidadeCategoria(Scanner in) {
        System.out.println("Digite a categoria: ");
        Categoria categoria = CategoriaRepository.getInstance().findOne(Integer.valueOf(in.nextLine()));
        List<Automovel> autoDisponiveisCategoria = this.automovelRepository.filter(auto -> this.getAutomovelByCategoriaAndDisponivel(categoria, auto));
        if (autoDisponiveisCategoria.isEmpty()) {
            System.out.println("Não há automoveis dessa categoria");
            return false;
        }

        autoDisponiveisCategoria.forEach(System.out::println);
        return true;
    }

    public void consultarClientesCadastrados() {
        List<Cliente> clientes = ClienteRepository.getInstance().findAll();
        clientes.forEach(cliente -> System.out.println(cliente.getNome()));
    }

    private boolean getAutomovelByCategoriaAndDisponivel(Categoria categoria, Automovel automovel) {
        return automovel.getModelo().getCategoria().equals(categoria) && automovel.isDisponivel();
    }

}
