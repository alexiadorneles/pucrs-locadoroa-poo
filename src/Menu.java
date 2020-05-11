import domain.PessoaJ;
import domain.automovel.*;
import domain.PessoaF;
import repository.AutomovelRepository;
import repository.CategoriaRepository;
import repository.MarcaRepository;
import repository.ModeloRepository;
import repository.ClienteRepository;
import domain.Cliente;

import java.util.List;
import java.util.Scanner;

public class Menu {
    public Menu() {
    }

    public void mostrar() {
        Scanner in = new Scanner(System.in);

        System.out.println("---------- LOCADORA AJE ----------");

        System.out.println("1 - Atendendente");
        System.out.println("2 - Gerente");
        int opcao1 = in.nextInt();
        in.nextLine();
        int opcao2;


        if (opcao1 == 1) {
            do {
                System.out.println("-------------------------------------------");
                this.mostrarOpcoesAtendente();
                opcao2 = in.nextInt();
                in.nextLine();
                switch (opcao2) {
//                    1 - Cadastrar nomo domain.Cliente
                    case 1:
                        cadastrarCliente(in);
                        break;
//                    2-Consultar Disponibilidade de Automóvel por Categoria
                    case 2:
                        consultaDisponibilidadeCategoria(in);
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }
            } while (opcao2 != 99);
        } else {
            do {
                System.out.println("-------------------------------------------");
                this.mostrarOpcoesGerente();
                opcao2 = in.nextInt();
                in.nextLine();
                switch (opcao2) {
                    case 1:
                        cadastrarCliente(in);
                        break;
//                    2-Consultar Disponibilidade de Automóvel por Categoria
                    case 2:
                        consultaDisponibilidadeCategoria(in);
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
//            6 - Cadastrar Nova Categoria Automóvel
                    case 6:
                        this.cadastrarCategoria(in);
                        break;
//            7 - Cadastrar Nova Marca do Automóvel
                    case 7:
                        this.cadastrarMarca(in);
                        break;
//            8 - Cadastrar Novo Modelo do Automóvel
                    case 8:
                        this.cadastrarModelo(in);
                        break;
//            9 - Cadastrar Novo Automóvel
                    case 9:
                        this.cadastrarAutomovel(in);
                        break;
                    case 10:
                        break;
//            10 - Consultar Clientes Cadastrados
                    case 11:
                        this.consultarClientesCadastrados();
                        break;
                    case 12:
                        break;
                }
            } while (opcao2 != 99);
        }

    }


    private void mostrarOpcoesGerente() {
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Consultar Disponibilidade de Automóvel por Categoria");
        System.out.println("3 - Consultar o Valor de uma Locaçao ");
        System.out.println("4 - Realizar Locação");
        System.out.println("5 - Finalizar Locação ");
        System.out.println("6 - Cadastrar Nova Categoria Automóvel");
        System.out.println("7 - Cadastrar Nova Marca do Automóvel");
        System.out.println("8 - Cadastrar Novo Modelo do Automóvel");
        System.out.println("9 - Cadastrar Novo Automóvel");
        System.out.println("10 - Consultar Locações");
        System.out.println("11 - Consultar Clientes");
        System.out.println("12 - Consultar Automóveis Cadastrados");
        System.out.println("99 - Sair");
    }

    private void mostrarOpcoesAtendente() {
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Consultar Disponibilidade de Automóvel por Categoria");
        System.out.println("3 - Consultar o Valor de uma Locaçao ");
        System.out.println("4 - Realizar Locação");
        System.out.println("5 - Finalizar Locação ");
        System.out.println("99 - Sair ");
    }

    private void cadastrarAutomovel(Scanner in) {
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
                placa, ano, valorDiaria, true,
                ModeloRepository.getInstance().findOne(nomeModelo)
        );
        AutomovelRepository.getInstance().save(automovel);
    }

    private void cadastrarCliente(Scanner in) {
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
            cliente = new PessoaF(nome, telefone, cpf);
        } else {
            System.out.println("CNPJ: ");
            String cnpj = in.nextLine();
            cliente = new PessoaJ(nome, telefone, cnpj);
        }

        ClienteRepository.getInstance().save(cliente);
        System.out.println("Cadastro concluído.");
    }

    private void consultaDisponibilidadeCategoria(Scanner in) {
        System.out.println("Digite a categoria: ");
        Categoria categoria = CategoriaRepository.getInstance().findOne(in.nextLine());
        List<Automovel> automoveisDestaCategoria = AutomovelRepository.getInstance()
                .filter(automovel -> automovel.getModelo().getCategoria().equals(categoria));
        if (automoveisDestaCategoria.isEmpty())
            System.out.println("Não há automoveis dessa categoria");
        else
            System.out.println(automoveisDestaCategoria);
    }

    private void consultarClientesCadastrados() {
        List<Cliente> clientes = ClienteRepository.getInstance().findAll();
        clientes.forEach(cliente -> System.out.println(cliente.getNome()));
    }

    private void cadastrarCategoria(Scanner in) {
        System.out.println("Digite o nome da categoria: ");
        String nomeCategoria = in.nextLine();
        CategoriaRepository.getInstance().save(new Categoria(nomeCategoria));
    }

    private void cadastrarMarca(Scanner in) {
        System.out.println("Digite o nome da marca: ");
        MarcaRepository.getInstance().save(new Marca(in.nextLine()));
    }

    private void cadastrarModelo(Scanner in) {
        System.out.println("Digite o nome do modelo: ");
        String nome = in.nextLine();
        System.out.println("Digite o valor: ");
        double valor = in.nextDouble();
        in.nextLine();

        System.out.println("Escolha a categoria");
        Categoria categoria = CategoriaRepository.getInstance().findOne(in.nextLine());

        System.out.println("Escolha a marca");
        Marca marca = MarcaRepository.getInstance().findOne(in.nextLine());


        System.out.println("Escolha o tipo de modelo: \n 1 - Nacional \t 2 - Internacional");
        int tipoModelo = in.nextInt();
        if (tipoModelo == 1) {
            System.out.println("Digite a porcentagem de ipi");
            Modelo modelo = new ModeloNacional(nome, valor, categoria, marca, in.nextDouble());
            ModeloRepository.getInstance().save(modelo);
        } else {
            System.out.println("Digite a porcentagem de taxa de importação");
            Modelo modelo = new ModeloInternacional(nome, valor, categoria, marca, in.nextDouble());
            ModeloRepository.getInstance().save(modelo);
        }
    }
}
