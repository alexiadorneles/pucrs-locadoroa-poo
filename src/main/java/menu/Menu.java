package menu;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import domain.automovel.Automovel;
import domain.cliente.Cliente;
import domain.locacao.Locacao;
import reader.TxtReader;
import repository.AutomovelRepository;
import repository.Repository;

import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Menu extends Application{
    private final Scanner in;
    private final Repository<Locacao, Integer> locacaoRepository;
    private final Repository<Cliente, String> clienteRepository;
    private final AutomovelRepository automovelRepository;
    private final ConsultaMenu consultaMenu;
    private final CadastroMenu cadastroMenu;
    private final TxtReader reader;

    public Menu(
            CadastroMenu cadastroMenu,
            ConsultaMenu consultaMenu,
            AutomovelRepository automovelRepository,
            Repository<Locacao, Integer> locacaoRepository,
            Repository<Cliente, String> clienteRepository,
            TxtReader reader
    ) {
        this.in = new Scanner(System.in);
        this.cadastroMenu = cadastroMenu;
        this.consultaMenu = consultaMenu;
        this.automovelRepository = automovelRepository;
        this.locacaoRepository = locacaoRepository;
        this.clienteRepository = clienteRepository;
        this.reader = reader;
    }
    @Override
    public void start(Stage menuStage) throws Exception {
        menuStage.setTitle("---------- LOCADORA AJE ----------");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(100, 100, 100, 100));

        Text sceneTitle = new Text("Menu");
        sceneTitle.setId("text");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0);

        Button atendente = new Button("ATENDENTE");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(atendente);
        grid.add(hbBtn, 1, 4);

        Button gerente = new Button("GERENTE");
        HBox gerentBt = new HBox(10);
        gerentBt.setAlignment(Pos.BOTTOM_LEFT);
        gerentBt.getChildren().add(gerente);
        grid.add(gerentBt, 0, 4);
        Scene scene = new Scene(grid);
        menuStage.setScene(scene);
        menuStage.show();
    }

    public void mostrar(String [] arg) {
        launch(arg);
//        System.out.println("1 - Atendendente");
//        System.out.println("2 - Gerente");
//        int tipoUsuario = in.nextInt();
//        in.nextLine();
//        int opcao2;
//
//        do {
//            System.out.println("-------------------------------------------");
//            if (tipoUsuario == 1) this.mostrarOpcoesAtendente();
//            if (tipoUsuario == 2) this.mostrarOpcoesGerente();
//            opcao2 = in.nextInt();
//            in.nextLine();
//            switch (opcao2) {
////       1 - Cadastrar cliente
//                case 1:
//                    this.cadastroMenu.cadastrarCliente(in);
//                    break;
////       2 - Consultar Disponibilidade de Automóvel por Categoria
//                case 2:
//                    this.consultaMenu.consultaDisponibilidadeCategoria(in);
//                    break;
////       3 - Consultar o Valor de uma Locaçao
//                case 3:
//                    this.consultaMenu.consultarValorLocacao(in);
//                    break;
////       4 - Realizar locação
//                case 4:
//                    this.realizarLocacao(in);
//                    break;
////       5 - Finalizar Locação
//                case 5:
//                    this.finalizarLocacao(in);
//                    break;
////       6 - Cadastrar Nova Categoria Automóvel
//                case 6:
//                    this.cadastroMenu.cadastrarCategoria(in);
//                    break;
////       7 - Cadastrar Nova Marca do Automóvel
//                case 7:
//                    this.cadastroMenu.cadastrarMarca(in);
//                    break;
////       8 - Cadastrar Novo Modelo do Automóvel
//                case 8:
//                    this.cadastroMenu.cadastrarModelo(in);
//                    break;
////       9 - Cadastrar Novo Automóvel
//                case 9:
//                    this.cadastroMenu.cadastrarAutomovel(in);
//                    break;
////       10 - Remover Automóvel
//                case 10:
//                    this.removerAutomovel(in);
//                    break;
////       11 - Consultar Locações
//                case 11:
//                    this.consultaMenu.consultarLocacoes();
//                    break;
////       12 - Consultar Clientes Cadastrados
//                case 12:
//                    this.consultaMenu.consultarClientesCadastrados();
//                    break;
////      13 - Consultar Automóveis Cadastrados
//                case 13:
//                    this.consultaMenu.consultarAutomoveis();
//                    break;
//                case 14:
//                    this.realizarCargaDeDados(in);
//                    break;
//            }
//        } while (opcao2 != 99);
    }

    private void realizarCargaDeDados(Scanner in) {
        System.out.println("Por favor, digite o nome do arquivo (ele deve estar em resources)");
        String fileName = in.nextLine();
        reader.read(fileName);
    }


    private void finalizarLocacao(Scanner in) {
        List<Locacao> locacoes = this.locacaoRepository.filter(locacao -> !locacao.isFinalizada());
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
            this.automovelRepository.removeByPlaca(locacao.getAuto().getPlaca());
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
        boolean possuiDestaCategoria = this.consultaMenu.consultaDisponibilidadeCategoria(in);
        if (!possuiDestaCategoria) return;
        Automovel automovel;
        Cliente cliente;

        do {
            System.out.println("Por favor selecione um automóvel acima, digitando sua placa");
            String placa = in.nextLine();
            automovel = this.automovelRepository.findOne(placa);
        } while (Objects.isNull(automovel));

        System.out.println("Esses são nossos clientes cadastrados: ");
        this.consultaMenu.consultarClientesCadastrados();
        do {
            System.out.println();
            System.out.println("Por favor digite o nome do cliente");
            cliente = this.clienteRepository.findOne(in.nextLine());
        } while (Objects.isNull(cliente));

        Locacao locacao = new Locacao(cliente.getCPFCNPJ(), dataInicial, dataFinal, automovel.getPlaca());
        System.out.println("Valor total da locação: " + locacao.calcularValorLocacao());
        this.locacaoRepository.save(locacao);
    }


    private void removerAutomovel(Scanner in) {
        System.out.println("Digite a placa do automóvel que deseja remover");
        boolean result = this.automovelRepository.removeByPlaca(in.nextLine());
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
        System.out.println("14 - Carga de dados");
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
}
