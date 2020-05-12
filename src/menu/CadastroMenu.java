package menu;

import domain.automovel.*;
import domain.cliente.Cliente;
import domain.cliente.PessoaFisica;
import domain.cliente.PessoaJuridica;
import repository.*;

import java.util.Scanner;

public class CadastroMenu {

    private final Repository<Modelo> modeloRepository;
    private final Repository<Automovel> automovelRepository;
    private final Repository<Cliente> clienteRepository;
    private final Repository<Marca> marcaRepository;
    private final Repository<Categoria> categoriaRepository;

    public CadastroMenu(
            Repository<Modelo> modeloRepository,
            Repository<Automovel> automovelRepository,
            Repository<Cliente> clienteRepository,
            Repository<Marca> marcaRepository,
            Repository<Categoria> categoriaRepository
    ) {
        this.modeloRepository = modeloRepository;
        this.automovelRepository = automovelRepository;
        this.clienteRepository = clienteRepository;
        this.marcaRepository = marcaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public void cadastrarAutomovel(Scanner in) {
        System.out.println("Digite a placa: ");
        String placa = in.nextLine();
        System.out.println("Digite o ano: ");
        int ano = in.nextInt();
        in.nextLine();
        System.out.println("Digite o valor diária: ");
        double valorDiaria = in.nextDouble();
        in.nextLine();
        System.out.println("Digite o nome do modelo: ");
        String nomeModelo = in.nextLine();
        Automovel automovel = new Automovel(
                placa, ano, valorDiaria,
                this.modeloRepository.findOne(nomeModelo)
        );
        this.automovelRepository.save(automovel);
    }

    public void cadastrarCliente(Scanner in) {
        int escolha;
        do {
            System.out.println("Escolha o tipo de cliente: ");
            System.out.println("1- Pessoa fisica ");
            System.out.println("2- Pessoa juridica");
            escolha = in.nextInt();
        } while (escolha > 2 || escolha < 1);

        Cliente cliente;
        System.out.println("Digite os dados do cliente: ");
        in.nextLine();
        System.out.println("Nome: ");
        String nome = in.nextLine();
        System.out.println("Telefone: ");
        String telefone = in.nextLine();

        if (escolha == 1) {
            System.out.println("CPF: ");
            String cpf = in.nextLine();
            cliente = new PessoaFisica(nome, telefone, cpf);
        } else {
            System.out.println("CNPJ: ");
            String cnpj = in.nextLine();
            cliente = new PessoaJuridica(nome, telefone, cnpj);
        }

        this.clienteRepository.save(cliente);
        System.out.println("Cadastro concluído.");
    }

    public void cadastrarCategoria(Scanner in) {
        System.out.println("Digite o nome da categoria: ");
        String nomeCategoria = in.nextLine();
        this.categoriaRepository.save(new Categoria(nomeCategoria));
    }

    public void cadastrarMarca(Scanner in) {
        System.out.println("Digite o nome da marca: ");
        this.marcaRepository.save(new Marca(in.nextLine()));
    }

    public void cadastrarModelo(Scanner in) {
        System.out.println("Digite o nome do modelo: ");
        String nome = in.nextLine();
        System.out.println("Digite o valor: ");
        double valor = in.nextDouble();
        in.nextLine();

        System.out.println("Escolha a categoria");
        Categoria categoria = this.categoriaRepository.findOne(in.nextLine());

        System.out.println("Escolha a marca");
        Marca marca = this.marcaRepository.findOne(in.nextLine());


        System.out.println("Escolha o tipo de modelo: \n 1 - Nacional \t 2 - Internacional");
        int tipoModelo = in.nextInt();
        if (tipoModelo == 1) {
            System.out.println("Digite a porcentagem de ipi");
            Modelo modelo = new ModeloNacional(nome, valor, categoria, marca, in.nextDouble());
            this.modeloRepository.save(modelo);
        } else {
            System.out.println("Digite a porcentagem de taxa de importação");
            Modelo modelo = new ModeloInternacional(nome, valor, categoria, marca, in.nextDouble());
            this.modeloRepository.save(modelo);
        }
    }

}
