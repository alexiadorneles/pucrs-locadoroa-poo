import automovel.*;
import repository.AutomovelRepository;
import repository.CategoriaRepository;
import repository.MarcaRepository;
import repository.ModeloRepository;

import java.util.Scanner;

public class Menu {
    public Menu() {
    }

    public void mostrar() {
        Scanner in = new Scanner(System.in);

        System.out.println("////////// LOCADORA AJE //////////");

        System.out.println("1 - Atendendente");
        System.out.println("2 - Gerente");
        int opcao1 = in.nextInt();
        in.nextLine();
        int opcao2;


        if (opcao1 == 1) {
            do {
                this.mostrarOpcoesAtendente();
                opcao2 = in.nextInt();
                in.nextLine();
                switch (opcao2) {
                    case 1:
                        break;
                    case 2:
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
                this.mostrarOpcoesGerente();
                opcao2 = in.nextInt();
                in.nextLine();
                switch (opcao2) {
//            1 - Cadastrar Nova Categoria Automóvel
                    case 1:
                        this.cadastrarCategoria(in);
                        break;
//            2 - Cadastrar Nova Marca do Automóvel
                    case 2:
                        this.cadastrarMarca(in);
                        break;
//            3 - Cadastrar Novo Modelo do Automóvel
                    case 3:
                        this.cadastrarModelo(in);
                        break;
//            4 - Cadastrar Novo Automóvel
                    case 4:
                        this.cadastrarAutomovel(in);
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                }
            } while (opcao2 != 99);
        }
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

    private void mostrarOpcoesGerente() {
        System.out.println("1 - Cadastrar Nova Categoria Automóvel");
        System.out.println("2 - Cadastrar Nova Marca do Automóvel");
        System.out.println("3 - Cadastrar Novo Modelo do Automóvel");
        System.out.println("4 - Cadastrar Novo Automóvel");
        System.out.println("5 - Consultar Locações");
        System.out.println("6 - Consultar Clientes");
        System.out.println("7 - Consultar Automóveis Cadastrados");
        System.out.println("99 - Sair");
    }

    private void mostrarOpcoesAtendente() {
        System.out.println("1 - Cadastrar nomo Cliente");
        System.out.println("2 - Consultar Disponibilidade de Automóvel por Categoria");
        System.out.println("3 - Consultar o Valor de uma Locaçao ");
        System.out.println("4 - Realizar Locação");
        System.out.println("5 - Finalizar Locação ");
        System.out.println("99 - Sair ");
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
