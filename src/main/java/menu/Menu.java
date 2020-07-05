package menu;
import domain.automovel.*;
import domain.cliente.PessoaFisica;
import domain.cliente.PessoaJuridica;
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
import javafx.scene.text.*;
import javafx.stage.Stage;

import domain.cliente.Cliente;
import domain.locacao.Locacao;
import reader.TxtReader;
import repository.*;

import javax.swing.*;
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
            CategoriaRepository instance, Repository<Locacao, Integer> locacaoRepository,
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

        Text sceneTitle = new Text("MENU");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0);

        Button atendente = new Button("ATENDENTE");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(atendente);
        grid.add(hbBtn, 1, 4);
        atendente.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GridPane opcoeAtendente = new GridPane();
                opcoeAtendente.setAlignment(Pos.CENTER);
                opcoeAtendente.setHgap(10);
                opcoeAtendente.setVgap(10);
                opcoeAtendente.setPadding(new Insets(50, 100, 100, 100));


                Text title = new Text("MENU ATENDENTE");
                title.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                title.setTextAlignment(TextAlignment.CENTER);
                opcoeAtendente.add(title,0,0);

                Button cadastroCliente = new Button("CADASTRAR CLIENTE");
                HBox button1 = new HBox(10);
                button1.setAlignment(Pos.BOTTOM_LEFT);
                button1.getChildren().add(cadastroCliente);
                opcoeAtendente.add(button1,0,1);
                cadastroCliente.setOnAction(actionEvent1 -> {
                    try {
                        cadastroMenu.setButton(1);
                        cadastroMenu.start(menuStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });


                Button consulta = new Button("CONSULTAR DISPONIBILIDADE DE AUTOMOVEL POR CATEGORIA");
                HBox button2 = new HBox(10);
                button2.setAlignment(Pos.BOTTOM_LEFT);
                button2.getChildren().add(consulta);
                opcoeAtendente.add(button2,0,2);
                consulta.setOnAction(actionEvent1 -> {
                    try {
                        consultaMenu.setButton(2);
                        consultaMenu.start(menuStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                Button consultaLocacao = new Button("CONSULTAR O VALOR DE UM LOCAÇÃO");
                HBox button3 = new HBox(10);
                button3.setAlignment(Pos.BOTTOM_LEFT);
                button3.getChildren().add(consultaLocacao);
                opcoeAtendente.add(button3,0,3);
                consultaLocacao.setOnAction(actionEvent1 -> {
                    try {
                        consultaMenu.setButton(3);
                        consultaMenu.start(menuStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });


                Button realizarLocacao = new Button("REALIZAR LOCAÇÃO");
                HBox button4 = new HBox(10);
                button4.setAlignment(Pos.BOTTOM_LEFT);
                button4.getChildren().add(realizarLocacao);
                opcoeAtendente.add(button4,0,4);
                realizarLocacao.setOnAction(actionEvent1 -> {
                    GridPane locacao = new GridPane();
                    locacao.setAlignment(Pos.CENTER);
                    locacao.setHgap(10);
                    locacao.setVgap(10);
                    locacao.setPadding(new Insets(100, 100, 100, 100));

                    Text realizaLocacao = new Text("REALIZAR A LOCACÃO");
                    realizaLocacao.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                    realizaLocacao.setTextAlignment(TextAlignment.CENTER);
                    locacao.add(realizaLocacao,0,0);

                    Label dataInicial = new Label("Data inicial (DD/MM/AAA) ");
                    locacao.add(dataInicial,0,1);

                    TextField datainitial = new TextField();
                    locacao.add(datainitial,1,1);

                    Label dataFinal = new Label("Data final (DD/MM/AAA): ");
                    locacao.add(dataFinal,0,2);

                    TextField datafinal = new TextField();
                    locacao.add(datafinal,1,2);

                    Label category = new Label("Codigo da Categoria: ");
                    locacao.add(category,0,3);

                    TextField codigoCategoria = new TextField();
                    locacao.add(codigoCategoria,1,3);

                    Button confirmarCadastro = new Button("CONTINUAR");
                    HBox btn = new HBox(10);
                    btn.setAlignment(Pos.BOTTOM_RIGHT);
                    btn.getChildren().add(confirmarCadastro);
                    locacao.add(btn,1,5);

                    final Text actiontarget = new Text();
                    locacao.add(actiontarget, 1, 6);
                    actiontarget.setId("actiontarget");

                    confirmarCadastro.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            actiontarget.setFill(Color.FIREBRICK);
                            if(datainitial.getText().trim().isEmpty() || datafinal.getText().isEmpty() ||
                                    codigoCategoria.getText().isEmpty()) actiontarget.setText("Por favor preencha todos os campos");
                            else {
                                boolean possuiDestaCategoria = consultaMenu.consultaDisponibilidadeCategoria(codigoCategoria.getText());
                                if (!possuiDestaCategoria) actiontarget.setText("Não possui automoveis dessa categoria");
                                else {
                                    GridPane newLocacao = new GridPane();
                                    newLocacao.setAlignment(Pos.CENTER);
                                    newLocacao.setHgap(10);
                                    newLocacao.setVgap(10);
                                    newLocacao.setPadding(new Insets(100, 100, 100, 100));

                                    Text locacaoDisponivel = new Text("Automoveis disponiveis");
                                    locacaoDisponivel.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                                    locacaoDisponivel.setTextAlignment(TextAlignment.CENTER);
                                    newLocacao.add(locacaoDisponivel,0,0);

                                    Text locacaod = new Text("");
                                    locacaod.setFont(Font.font("Tahoma",FontWeight.NORMAL,14));
                                    automovelRepository.findAll().forEach(str -> locacaod.setText(str.toString()));
                                    newLocacao.add(locacaod,0,1);

                                    Label label = new Label("Digite uma placa");
                                    newLocacao.add(label,0,4);

                                    TextField placa = new TextField();
                                    newLocacao.add(placa, 1, 4);

                                    Button confirma = new Button("LOCAR");
                                    HBox btn = new HBox(10);
                                    btn.setAlignment(Pos.BOTTOM_RIGHT);
                                    btn.getChildren().add(confirma);
                                    newLocacao.add(btn,1,6);

                                    final Text action = new Text();
                                    newLocacao.add(action, 1, 7);
                                    action.setId("action");

                                    menuStage.setScene(new Scene(newLocacao));
                                    menuStage.show();

                                    confirma.setOnAction(actionEvent2 -> {
                                        GridPane cli = new GridPane();
                                        cli.setAlignment(Pos.CENTER);
                                        cli.setHgap(10);
                                        cli.setVgap(10);
                                        cli.setPadding(new Insets(100, 100, 100, 100));
                                        action.setFill(Color.FIREBRICK);
                                        if (placa.getText().isEmpty()) action.setText("Preencha os campos");
                                        if (automovelRepository.findOne(placa.getText())==null) action.setText("Por favor coloque uma placa valida");
                                        else {
                                            Text clienteDisponivel = new Text("Clientes Cadastrados");
                                            clienteDisponivel.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                                            clienteDisponivel.setTextAlignment(TextAlignment.CENTER);
                                            cli.add(clienteDisponivel,0,0);

                                            final Automovel automovel;
                                            automovel = automovelRepository.findOne(placa.getText());
                                            List<Cliente> clientes = ClienteRepository.getInstance().findAll();
                                            Text loc = new Text("");
                                            loc.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                                            clientes.forEach(cliente1 -> loc.setText(cliente1.getNome()));
                                            cli.add(loc,0,1);

                                            Label client = new Label("Escolha um cliente");
                                            cli.add(client,0,2);

                                            TextField clienteEscolhido = new TextField();
                                            cli.add(clienteEscolhido,1,2);

                                            final Text actiontarget = new Text();
                                            cli.add(actiontarget, 1, 7);
                                            actiontarget.setId("actiontarget");

                                            Button concluir = new Button("CONCLUIR");
                                            HBox bttn = new HBox(10);
                                            bttn.setAlignment(Pos.BOTTOM_RIGHT);
                                            bttn.getChildren().add(concluir);

                                            cli.add(concluir,1,5);
                                            concluir.setOnAction(actionEvent3 -> {
                                                actiontarget.setFill(Color.FIREBRICK);
                                                if(clienteEscolhido.getText().isEmpty())
                                                    actiontarget.setText("Preencha com um nome valido");
                                                else {
                                                    final Cliente cliente;
                                                    actiontarget.setText("Cadastro concluído");
                                                    cliente = clienteRepository.findOne(clienteEscolhido.getText());
                                                    Locacao locacao = new Locacao(cliente.getCPFCNPJ(), datainitial.getText(),
                                                            datafinal.getText(), automovel.getPlaca());

                                                    Text t2 = new Text("O valor da locação é " + locacao.calcularValorLocacao());
                                                    t2.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                                                    cli.add(t2,0,8);
                                                    locacaoRepository.save(locacao);
                                                }
                                            });
                                            menuStage.setScene(new Scene(cli));
                                            menuStage.show();
                                        }
                                    });
                                }
                            }
                        }
                    });
                    menuStage.setScene(new Scene(locacao));
                    menuStage.show();
                });

                Button  finalizarLocacao= new Button("FINALIZAR LOCAÇÃO");
                HBox button5 = new HBox(10);
                button5.setAlignment(Pos.BOTTOM_LEFT);
                button5.getChildren().add(finalizarLocacao);
                opcoeAtendente.add(button5,0,5);

                Scene atendentesOpcao = new Scene(opcoeAtendente);
                menuStage.setScene(atendentesOpcao);
                menuStage.show();
            }
        });

        Button gerente = new Button("GERENTE");
        HBox gerentBt = new HBox(10);
        gerentBt.setAlignment(Pos.BOTTOM_RIGHT);
        gerentBt.getChildren().add(gerente);
        grid.add(gerentBt, 0, 4);
        gerente.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GridPane opcoeGerente = new GridPane();
                opcoeGerente.setAlignment(Pos.CENTER);
                opcoeGerente.setHgap(10);
                opcoeGerente.setVgap(10);
                opcoeGerente.setPadding(new Insets(50, 100, 100, 100));

                Text title = new Text("MENU GERENTE");
                title.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                title.setTextAlignment(TextAlignment.CENTER);
                opcoeGerente.add(title,0,0);

                Button cadastroCliente = new Button("CADASTRAR CLIENTE");
                HBox button1 = new HBox(10);
                button1.setAlignment(Pos.BOTTOM_LEFT);
                button1.getChildren().add(cadastroCliente);
                opcoeGerente.add(button1,0,1);
                cadastroCliente.setOnAction(actionEvent1 -> {
                    try {
                        cadastroMenu.setButton(1);
                        cadastroMenu.start(menuStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                Button consulta = new Button("CONSULTAR DISPONIBILIDADE DE AUTOMOVEL POR CATEGORIA");
                HBox button2 = new HBox(10);
                button2.setAlignment(Pos.BOTTOM_LEFT);
                button2.getChildren().add(consulta);
                opcoeGerente.add(button2,0,2);
                consulta.setOnAction(actionEvent1 -> {
                    try {
                        consultaMenu.setButton(2);
                        consultaMenu.start(menuStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                Button consultaLocacao = new Button("CONSULTAR O VALOR DE UM LOCAÇÃO");
                HBox button3 = new HBox(10);
                button3.setAlignment(Pos.BOTTOM_LEFT);
                button3.getChildren().add(consultaLocacao);
                opcoeGerente.add(button3,0,3);
                consultaLocacao.setOnAction(actionEvent1 -> {
                    try {
                        consultaMenu.setButton(3);
                        consultaMenu.start(menuStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                Button realizarLocacao = new Button("REALIZAR LOCAÇÃO");
                HBox button4 = new HBox(10);
                button4.setAlignment(Pos.BOTTOM_LEFT);
                button4.getChildren().add(realizarLocacao);
                opcoeGerente.add(button4,0,4);
                realizarLocacao.setOnAction(actionEvent1 -> {
                    GridPane locacao = new GridPane();
                    locacao.setAlignment(Pos.CENTER);
                    locacao.setHgap(10);
                    locacao.setVgap(10);
                    locacao.setPadding(new Insets(100, 100, 100, 100));

                    Text realizaLocacao = new Text("REALIZAR A LOCACÃO");
                    realizaLocacao.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                    realizaLocacao.setTextAlignment(TextAlignment.CENTER);
                    locacao.add(realizaLocacao,0,0);

                    Label dataInicial = new Label("Data inicial (DD/MM/AAA) ");
                    locacao.add(dataInicial,0,1);

                    TextField datainitial = new TextField();
                    locacao.add(datainitial,1,1);

                    Label dataFinal = new Label("Data final (DD/MM/AAA): ");
                    locacao.add(dataFinal,0,2);

                    TextField datafinal = new TextField();
                    locacao.add(datafinal,1,2);

                    Label category = new Label("Codigo da Categoria: ");
                    locacao.add(category,0,3);

                    TextField codigoCategoria = new TextField();
                    locacao.add(codigoCategoria,1,3);

                    Button confirmarCadastro = new Button("CONTINUAR");
                    HBox btn = new HBox(10);
                    btn.setAlignment(Pos.BOTTOM_RIGHT);
                    btn.getChildren().add(confirmarCadastro);
                    locacao.add(btn,1,5);

                    final Text actiontarget = new Text();
                    locacao.add(actiontarget, 1, 6);
                    actiontarget.setId("actiontarget");

                    confirmarCadastro.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            actiontarget.setFill(Color.FIREBRICK);
                            if(datainitial.getText().trim().isEmpty() || datafinal.getText().isEmpty() ||
                                    codigoCategoria.getText().isEmpty()) actiontarget.setText("Por favor preencha todos os campos");
                            else {
                                boolean possuiDestaCategoria = consultaMenu.consultaDisponibilidadeCategoria(codigoCategoria.getText());
                            if (!possuiDestaCategoria) actiontarget.setText("Não possui automoveis dessa categoria");
                                else {
                                    GridPane newLocacao = new GridPane();
                                    newLocacao.setAlignment(Pos.CENTER);
                                    newLocacao.setHgap(10);
                                    newLocacao.setVgap(10);
                                    newLocacao.setPadding(new Insets(100, 100, 100, 100));

                                    Text locacaoDisponivel = new Text("Automoveis disponiveis");
                                    locacaoDisponivel.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                                    locacaoDisponivel.setTextAlignment(TextAlignment.CENTER);
                                    newLocacao.add(locacaoDisponivel,0,0);

                                    Text locacaod = new Text("");
                                    locacaod.setFont(Font.font("Tahoma",FontWeight.NORMAL,14));
                                    automovelRepository.findAll().forEach(str -> locacaod.setText(str.toString()));
                                    newLocacao.add(locacaod,0,1);

                                    Label label = new Label("Digite uma placa");
                                    newLocacao.add(label,0,4);

                                    TextField placa = new TextField();
                                    newLocacao.add(placa, 1, 4);

                                    Button confirma = new Button("LOCAR");
                                    HBox btn = new HBox(10);
                                    btn.setAlignment(Pos.BOTTOM_RIGHT);
                                    btn.getChildren().add(confirma);
                                    newLocacao.add(btn,1,6);

                                    final Text action = new Text();
                                    newLocacao.add(action, 1, 7);
                                    action.setId("action");

                                    menuStage.setScene(new Scene(newLocacao));
                                    menuStage.show();

                                    confirma.setOnAction(actionEvent2 -> {
                                        GridPane cli = new GridPane();
                                        cli.setAlignment(Pos.CENTER);
                                        cli.setHgap(10);
                                        cli.setVgap(10);
                                        cli.setPadding(new Insets(100, 100, 100, 100));
                                        action.setFill(Color.FIREBRICK);
                                        if (placa.getText().isEmpty()) action.setText("Preencha os campos");
                                        if (automovelRepository.findOne(placa.getText())==null) action.setText("Por favor coloque uma placa valida");
                                        else {
                                            Text clienteDisponivel = new Text("Clientes Cadastrados");
                                            clienteDisponivel.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                                            clienteDisponivel.setTextAlignment(TextAlignment.CENTER);
                                            cli.add(clienteDisponivel,0,0);

                                            final Automovel automovel;
                                            automovel = automovelRepository.findOne(placa.getText());
                                            List<Cliente> clientes = ClienteRepository.getInstance().findAll();
                                            Text loc = new Text("");
                                            loc.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                                            clientes.forEach(cliente1 -> loc.setText(cliente1.getNome()));
                                            cli.add(loc,0,1);

                                            Label client = new Label("Escolha um cliente");
                                            cli.add(client,0,2);

                                            TextField clienteEscolhido = new TextField();
                                            cli.add(clienteEscolhido,1,2);

                                            final Text actiontarget = new Text();
                                            cli.add(actiontarget, 1, 7);
                                            actiontarget.setId("actiontarget");

                                            Button concluir = new Button("CONCLUIR");
                                            HBox bttn = new HBox(10);
                                            bttn.setAlignment(Pos.BOTTOM_RIGHT);
                                            bttn.getChildren().add(concluir);

                                            cli.add(concluir,1,5);
                                            concluir.setOnAction(actionEvent3 -> {
                                                actiontarget.setFill(Color.FIREBRICK);
                                                if(clienteEscolhido.getText().isEmpty())
                                                    actiontarget.setText("Preencha com um nome valido");
                                                else {
                                                    final Cliente cliente;
                                                    actiontarget.setText("Cadastro concluído");
                                                    cliente = clienteRepository.findOne(clienteEscolhido.getText());
                                                    Locacao locacao = new Locacao(cliente.getCPFCNPJ(), datainitial.getText(),
                                                            datafinal.getText(), automovel.getPlaca());

                                                    Text t2 = new Text("O valor da locação é " + locacao.calcularValorLocacao());
                                                    t2.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                                                    cli.add(t2,0,8);
                                                    locacaoRepository.save(locacao);
                                                }
                                            });
                                            menuStage.setScene(new Scene(cli));
                                            menuStage.show();
                                        }
                                    });
                                }
                            }
                        }
                    });
                    menuStage.setScene(new Scene(locacao));
                    menuStage.show();
                });

                Button  finalizarLocacao= new Button("FINALIZAR LOCAÇÃO");
                HBox button5 = new HBox(10);
                button5.setAlignment(Pos.BOTTOM_LEFT);
                button5.getChildren().add(finalizarLocacao);
                opcoeGerente.add(button5,0,5);
                finalizarLocacao.setOnAction(actionEvent1 -> {

                    Cliente cliente = new PessoaFisica("Maria", "0900", "029307");
                    ClienteRepository.getInstance().save(cliente);

                    Categoria categoria = new Categoria(1, "Qualquer");
                    CategoriaRepository.getInstance().save(categoria);

                    Marca marca = new Marca(1, "Honda");
                    MarcaRepository.getInstance().save(marca);

                    Modelo modelo = new ModeloNacional(1, "Civic", 100000, 1,
                            1, 10);
                    ModeloRepository.getInstance().save(modelo);

                    Automovel automovel = new Automovel("AAA-2A22", 2019, 10, modelo.getCodigo());
                    AutomovelRepository.getInstance().save(automovel);

                    Locacao locar = new Locacao(cliente.getCPFCNPJ(), "02/01/2020", "12/01/2020", automovel.getPlaca());
                    locacaoRepository.save(locar);

                    GridPane finalizar = new GridPane();
                    finalizar.setAlignment(Pos.CENTER);
                    finalizar.setHgap(10);
                    finalizar.setVgap(10);
                    finalizar.setPadding(new Insets(50, 100, 100, 100));

                    Text finalizaLocacao = new Text("FINALIZAR LOCACÃO");
                    finalizaLocacao.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                    finalizaLocacao.setTextAlignment(TextAlignment.CENTER);
                    finalizar.add(finalizaLocacao,0,0);

                    Text action = new Text();
                    action.setId("action");
                    action.setFill(Color.FIREBRICK);
                    finalizar.add(action,0,6);

                    List<Locacao> locacoes = locacaoRepository.filter(locacao -> !locacao.isFinalizada());
                    if (locacoes.isEmpty()) {
                        action.setText("Nenhuma locação para ser finalizada");
                    }else {

                        Text locacao = new Text("Locações disponiveis no sistema");
                        locacao.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                        locacao.setTextAlignment(TextAlignment.CENTER);
                        finalizar.add(locacao,0,1);

                        Text loc = new Text();
                        loc.setFont(Font.font("Tahoma",FontWeight.NORMAL,14));
                        loc.setTextAlignment(TextAlignment.CENTER);
                        locacoes.forEach(str-> loc.setText(str.toString()));
                        finalizar.add(loc,0,2);

                        Label cod = new Label("Digite o codigo para finalizar:");
                        finalizar.add(cod,0,4);

                        TextField codigo = new TextField();
                        finalizar.add(codigo,1,4);
                        Button finalLocacao = new Button("FINALIZAR");
                        HBox hfinal = new HBox(10);
                        hfinal.setAlignment(Pos.BOTTOM_LEFT);
                        hfinal.getChildren().add(finalLocacao);
                        finalizar.add(finalLocacao,0,5);
                        Text action1=new Text();
                        action.setId("act1");
                        finalizar.add(action1,0,5);
                        finalLocacao.setOnAction(act -> {

                            int codigos = Integer.parseInt(codigo.getText());
                            Locacao locacao1 = locacoes.stream().filter(locacao2 -> locacao2.getCodigo().equals(codigos)).findFirst().orElse(null);
                            if (codigo.getText().isEmpty()||locacao1==null) action1.setText("Preencha com um codigo valido");
                            else {
                                GridPane finalizando = new GridPane();
                                finalizando.setAlignment(Pos.CENTER);
                                finalizando.setHgap(10);
                                finalizando.setVgap(10);
                                finalizando.setPadding(new Insets(50, 100, 100, 100));

                                Text acidente = new Text("Ocorreram acidentes com esse veiculo durante a locação?");
                                acidente.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                                acidente.setTextAlignment(TextAlignment.CENTER);
                                finalizando.add(acidente,0,0);

                                Text removido = new Text();
                                removido.setFont(Font.font("Tahoma",FontWeight.NORMAL,14));
                                removido.setTextAlignment(TextAlignment.CENTER);
                                finalizando.add(removido,0,3);

                                Text actiontarget = new Text();
                                actiontarget.setId("actiontarget");
                                actiontarget.setFill(Color.FIREBRICK);
                                finalizando.add(actiontarget,0,6);
                                locacao1.finalizar();

                                Button sim = new Button("SIM");
                                HBox hsim = new HBox(10);
                                hsim.setAlignment(Pos.BOTTOM_LEFT);
                                hsim.getChildren().add(sim);
                                finalizando.add(sim,0,1);

                                sim.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                        finalizando.add(removido,0,3);
                                        if(locacao1.getAuto().isVelhoDemaisParaAFrota()){
                                            removido.setText("O veículo não está mais em condições de operar e foi removido da frota\n" +
                                                    "Pelo motivo de ser velho demais.\nE por ter sofrido um acidente");
                                        } else{
                                            removido.setText("O veículo não está mais em condições de operar e foi removido da frota\n" +
                                                    "E por ter sofrido um acidente");
                                        }

                                        actiontarget.setText("Locação finalizada");
                                        menuStage.setScene(new Scene(finalizando));
                                    }
                                });

                                Button nao = new Button("NÃO");
                                HBox hnao = new HBox(10);
                                hnao.setAlignment(Pos.BOTTOM_LEFT);
                                hnao.getChildren().add(nao);
                                finalizando.add(nao,1,1);
                                nao.setOnAction(actionEvent3 -> {
                                    finalizando.add(removido,0,2);
                                    if(locacao1.getAuto().isVelhoDemaisParaAFrota()){
                                        removido.setText("O veículo não está mais em condições de operar e foi removido da frota\n" +
                                                "Por ter sofrido um acidente");
                                        finalizando.add(removido,0,2);

                                    }
                                    actiontarget.setText("Locação finalizada");
                                    menuStage.setScene(new Scene(finalizando));
                                    menuStage.show();
                                });
                                menuStage.setScene(new Scene(finalizando));
                            }
                        });

                    }
                    menuStage.setScene(new Scene(finalizar));
                    menuStage.show();
                });

                Button cadastrarCategoria = new Button("CADASTRAR NOVA CATEGORIA DE AUTOMOVEL");
                HBox button6 = new HBox(10);
                button6.setAlignment(Pos.BOTTOM_LEFT);
                button6.getChildren().add(cadastrarCategoria);
                opcoeGerente.add(button6,0,6);

                Button  cadastrarMarca= new Button("CADASTRAR NOVA MARCA DE AUTOMOVEL");
                HBox button7 = new HBox(10);
                button7.setAlignment(Pos.BOTTOM_LEFT);
                button7.getChildren().add(cadastrarMarca);
                opcoeGerente.add(button7,0,7);

                Button cadastrarModelo= new Button("CADASTRAR NOVO MODELO DE AUTOMOVEL");
                HBox button8 = new HBox(10);
                button8.setAlignment(Pos.BOTTOM_LEFT);
                button8.getChildren().add(cadastrarModelo);
                opcoeGerente.add(button8,0,8);

                Button  cadastrarAutomovel= new Button("CADASTRAR NOVO AUTOMOVEL");
                HBox button9 = new HBox(10);
                button9.setAlignment(Pos.BOTTOM_LEFT);
                button9.getChildren().add(cadastrarAutomovel);
                opcoeGerente.add(button9,0,9);

                Button  removerAutomovel= new Button("REMOVER AUTOMÓVEL");
                HBox button10 = new HBox(10);
                button10.setAlignment(Pos.BOTTOM_LEFT);
                button10.getChildren().add(removerAutomovel);
                opcoeGerente.add(button10,0,10);

                Button  consultarLocacoes= new Button("CONSULTAR LOCAÇÕES");
                HBox button11 = new HBox(10);
                button11.setAlignment(Pos.BOTTOM_LEFT);
                button11.getChildren().add(consultarLocacoes);
                opcoeGerente.add(button11,0,11);

                Button  consultarClientes= new Button("CONSULTAR CLIENTES");
                HBox button12 = new HBox(10);
                button12.setAlignment(Pos.BOTTOM_LEFT);
                button12.getChildren().add(consultarClientes);
                opcoeGerente.add(button12,0,12);

                Button  consultarAutomoveis= new Button("CONSULTAR AUTOMOVEIS CADASTRADOS");
                HBox button13 = new HBox(10);
                button13.setAlignment(Pos.BOTTOM_LEFT);
                button13.getChildren().add(consultarAutomoveis);
                opcoeGerente.add(button13,0,13);

                Button cargaDeDados = new Button("CARGA DE DADOS");
                HBox button14 = new HBox(10);
                button14.setAlignment(Pos.BOTTOM_LEFT);
                button14.getChildren().add(cargaDeDados);
                opcoeGerente.add(button14,0,14);

                Scene gerenteOpcao = new Scene(opcoeGerente);
                menuStage.setScene(gerenteOpcao);
                menuStage.show();

            }
        });

        Scene scene = new Scene(grid);
        menuStage.setScene(scene);
        menuStage.show();
    }
//
//    public void mostrar(String [] arg) {
//        launch(arg);
//        System.out.println("1 - Atendendente");
//        System.out.println("2 - Gerente");
//        int tipoUsuario = in.nextInt();
//        in.nextLine();
//        int opcao2;
//
//        do {
//            System.out.println("-------------------------------------------");
//            if (tipoUsuario == 1) this.mostrarOpcoesAtendente();
//      if (tipoUsuario == 2) this.mostrarOpcoesGerente();
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
//    }

    private void realizarCargaDeDados(Scanner in) {
        System.out.println("Por favor, digite o nome do arquivo (ele deve estar em resources)");
        String fileName = in.nextLine();
        reader.read(fileName);
    }

    private void removerAutomovel(Scanner in) {
        System.out.println("Digite a placa do automóvel que deseja remover");
        boolean result = this.automovelRepository.removeByPlaca(in.nextLine());
        System.out.println(result ? "Removido com sucesso!" : "Um automóvel com essa placa não existe");
    }
}
