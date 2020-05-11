import domain.locacao.Locacao;
import domain.cliente.PessoaJuridica;
import domain.automovel.*;
import domain.cliente.PessoaFisica;
import repository.*;
import domain.cliente.Cliente;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    public Menu() {
    }

    public void mostrar() {
        Scanner in = new Scanner(System.in);

        System.out.println("---------- LOCADORA AJE ----------");

        System.out.println("1 - Atendendente");
        System.out.println("2 - Gerente");
        int tipoUsuario = in.nextInt();
        in.nextLine();
        int opcao2;

        do {
            System.out.println("-------------------------------------------");
            if (tipoUsuario == 1) this.mostrarOpcoesAtendente();
            if (tipoUsuario == 2) this.mostrarOpcoesGerente();
            opcao2 = in.nextInt();
            in.nextLine();
            switch (opcao2) {
//       1 - Cadastrar cliente
                case 1:
                    cadastrarCliente(in);
                    break;
//       2 - Consultar Disponibilidade de Automóvel por Categoria
                case 2:
                    consultaDisponibilidadeCategoria(in);
                    break;
//       3 - Consultar o Valor de uma Locaçao
                case 3:
                    this.consultarValorLocacao(in);
                    break;
//       4 - Realizar locação
                case 4:
                    this.realizarLocacao(in);
                    break;
//       5 - Finalizar Locação
                case 5:
                    this.finalizarLocacao(in);
                    break;
//       6 - Cadastrar Nova Categoria Automóvel
                case 6:
                    this.cadastrarCategoria(in);
                    break;
//       7 - Cadastrar Nova Marca do Automóvel
                case 7:
                    this.cadastrarMarca(in);
                    break;
//       8 - Cadastrar Novo Modelo do Automóvel
                case 8:
                    this.cadastrarModelo(in);
                    break;
//       9 - Cadastrar Novo Automóvel
                case 9:
                    this.cadastrarAutomovel(in);
                    break;
//       10 - Remover Automóvel
                case 10:
                    this.removerAutomovel(in);
                    break;
//       11 - Consultar Locações
                case 11:
                    this.consultarLocacoes();
                    break;
//       12 - Consultar Clientes Cadastrados
                case 12:
                    this.consultarClientesCadastrados();
                    break;
                case 13:
                    this.consultarAutomoveis();
                    break;
            }
        } while (opcao2 != 99);
    }

    private void consultarValorLocacao(Scanner in) {
        System.out.println("Essas são as locações disponíveis no sistema: ");
        this.consultarLocacoes();
        Locacao locacao;
        do {
            System.out.println("Por favor digite o código de uma locação pra consultar seu valor total: ");
            locacao = LocacaoRepository.getInstance().findOne(in.nextLine());
        } while (Objects.isNull(locacao));

        System.out.println("O valor da sua locação é: " + locacao.calcularValorLocacao());
    }

    private void consultarLocacoes() {
        LocacaoRepository.getInstance().findAll().forEach(System.out::println);
    }

    private void finalizarLocacao(Scanner in) {
        List<Locacao> locacoes = LocacaoRepository.getInstance().filter(locacao -> !locacao.isFinalizada());
        if (locacoes.isEmpty()) {
            System.out.println("Nenhuma locação para ser finalizada");
        }
        System.out.println("Essas são a locações disponíveis do sistema: ");
        locacoes.forEach(System.out::println);
        System.out.println();
        System.out.println("Por favor digite o código da que deseja finalizar: ");
        String codigo = in.nextLine();
        Locacao locacao = locacoes.stream().filter(loc -> loc.getCodigo().equals(codigo)).findFirst().orElse(null);
        System.out.println("Houve algum acidente com o veiculo desta locação?");
        System.out.println("1 - Sim. \t 2 - Não");
        int acidente = in.nextInt();
        if (acidente == 1 || locacao.getAuto().isVelhoDemaisParaAFrota()) {
            AutomovelRepository.getInstance().removeByPlaca(locacao.getAuto().getPlaca());
            System.out.println("O veículo não está mais em condições de operar e foi removido da frota");
            System.out.println("Motivo: " + (acidente == 1 ? "acidente." : "veículo velho demais para a frota"));
        }
        locacao.finalizar();
    }

    /*
     *   Cada vez que um cliente deseja locar um automóvel ele deve indicar a data inicial, data final, a categoria do
     *   automóvel para a locação; se houver automóveis disponíveis da categoria desejada para a locação, o cliente
     *   seleciona um automóvel, e uma locação é realizada e o valor final da locação é informado ao cliente.
     */
    private void realizarLocacao(Scanner in) {
        System.out.println("Digite a data inicial da locação (no formado dia/mês/ano. Ex: 02/05/2020)");
        String dataInicial = in.next();
        System.out.println("Digite a data final da locação (no formado dia/mês/ano. Ex: 02/05/2020)");
        String dataFinal = in.next();
        in.nextLine();
        boolean possuiDestaCategoria = this.consultaDisponibilidadeCategoria(in);
        if (!possuiDestaCategoria) return;
        Automovel automovel;
        Cliente cliente;

        do {
            System.out.println("Por favor selecione um automóvel acima, digitando sua placa");
            String placa = in.nextLine();
            automovel = AutomovelRepository.getInstance().findOne(placa);
        } while (Objects.isNull(automovel));

        System.out.println("Esses são nossos clientes cadastrados: ");
        this.consultarClientesCadastrados();
        do {
            System.out.println();
            System.out.println("Por favor digite o nome do cliente");
            cliente = ClienteRepository.getInstance().findOne(in.nextLine());
        } while (Objects.isNull(cliente));

        Locacao locacao = new Locacao(cliente, dataInicial, dataFinal, automovel);
        System.out.println("Valor total da locação: " + locacao.calcularValorLocacao());
        LocacaoRepository.getInstance().save(locacao);
    }

    private void consultarAutomoveis() {
        List<Automovel> automoveis = AutomovelRepository.getInstance().findAll();
        automoveis.forEach(System.out::println);
    }

    private void removerAutomovel(Scanner in) {
        System.out.println("Digite a placa do automóvel que deseja remover");
        boolean result = AutomovelRepository.getInstance().removeByPlaca(in.nextLine());
        System.out.println(result ? "Removido com sucesso!" : "Um automóvel com essa placa não existe");
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
        System.out.println("10 - Remover Automóvel");
        System.out.println("11 - Consultar Locações");
        System.out.println("12 - Consultar Clientes");
        System.out.println("13 - Consultar Automóveis Cadastrados");
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
            cliente = new PessoaFisica(nome, telefone, cpf);
        } else {
            System.out.println("CNPJ: ");
            String cnpj = in.nextLine();
            cliente = new PessoaJuridica(nome, telefone, cnpj);
        }

        ClienteRepository.getInstance().save(cliente);
        System.out.println("Cadastro concluído.");
    }

    private boolean consultaDisponibilidadeCategoria(Scanner in) {
        System.out.println("Digite a categoria: ");
        Categoria categoria = CategoriaRepository.getInstance().findOne(in.nextLine());
        List<Automovel> automoveisDisponiveisDestaCategoria = AutomovelRepository.getInstance()
                .filter(automovel -> automovel.getModelo().getCategoria().equals(categoria) && automovel.isDisponivel());
        if (automoveisDisponiveisDestaCategoria.isEmpty()) {
            System.out.println("Não há automoveis dessa categoria");
            return false;
        }

        automoveisDisponiveisDestaCategoria.forEach(System.out::println);
        return true;
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
