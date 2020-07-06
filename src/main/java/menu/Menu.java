package menu;

import domain.DataSource;
import domain.automovel.*;
import domain.cliente.PessoaFisica;
import domain.cliente.PessoaJuridica;
import factory.DataSourceFactory;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import domain.cliente.Cliente;
import domain.locacao.Locacao;
import javafx.stage.WindowEvent;
import reader.JSONReader;
import reader.TxtReader;
import repository.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class Menu extends Application {
    private final Scanner in;
    private final Repository<Locacao, Integer> locacaoRepository;
    private final Repository<Cliente, String> clienteRepository;
    private final AutomovelRepository automovelRepository;
    private final ConsultaMenu consultaMenu;
    private final CadastroMenu cadastroMenu;
    private final TxtReader reader;
    private final JSONReader jsonReader;
    private final DataSourceFactory dataSourceFactory;

    public Menu(
            CadastroMenu cadastroMenu,
            ConsultaMenu consultaMenu,
            AutomovelRepository automovelRepository,
            CategoriaRepository instance, Repository<Locacao, Integer> locacaoRepository,
            Repository<Cliente, String> clienteRepository,
            TxtReader reader,
            JSONReader jsonReader, DataSourceFactory dataSourceFactory) {
        this.jsonReader = jsonReader;
        this.in = new Scanner(System.in);
        this.cadastroMenu = cadastroMenu;
        this.consultaMenu = consultaMenu;
        this.automovelRepository = automovelRepository;
        this.locacaoRepository = locacaoRepository;
        this.clienteRepository = clienteRepository;
        this.reader = reader;
        this.dataSourceFactory = dataSourceFactory;
    }

    @Override
    public void start(Stage menuStage) throws Exception {
        menuStage.setTitle("---------- LOCADORA AJE ----------");
        DataSourceFactory factory = this.dataSourceFactory;
        JSONReader<DataSource> jsonReader = this.jsonReader;
        menuStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                DataSource dataSource = factory.createToJSON();
                try {
                    jsonReader.write("resources/db.json", dataSource);
                } catch (IOException e) {
                    System.out.println("Erro ao salvar dados");
                }
            }
        });
        GridPane telaCarga = new GridPane();
        telaCarga.setAlignment(Pos.CENTER);
        telaCarga.setHgap(10);
        telaCarga.setVgap(10);
        telaCarga.setPadding(new Insets(100, 100, 100, 100));

        Text sceneTitle = new Text("MENU");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        telaCarga.add(sceneTitle, 0, 0);

        Button atendente = new Button("ATENDENTE");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(atendente);
        telaCarga.add(hbBtn, 1, 4);
        atendente.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GridPane opcoeAtendente = new GridPane();
                opcoeAtendente.setAlignment(Pos.CENTER);
                opcoeAtendente.setHgap(10);
                opcoeAtendente.setVgap(10);
                opcoeAtendente.setPadding(new Insets(50, 100, 100, 100));


                Text title = new Text("MENU ATENDENTE");
                title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                title.setTextAlignment(TextAlignment.CENTER);
                opcoeAtendente.add(title, 0, 0);

                Button cadastroCliente = new Button("CADASTRAR CLIENTE");
                HBox button1 = new HBox(10);
                button1.setAlignment(Pos.BOTTOM_LEFT);
                button1.getChildren().add(cadastroCliente);
                opcoeAtendente.add(button1, 0, 1);
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
                opcoeAtendente.add(button2, 0, 2);
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
                opcoeAtendente.add(button3, 0, 3);
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
                opcoeAtendente.add(button4, 0, 4);
                realizarLocacao.setOnAction(actionEvent1 -> {
                    GridPane locacao = new GridPane();
                    locacao.setAlignment(Pos.CENTER);
                    locacao.setHgap(10);
                    locacao.setVgap(10);
                    locacao.setPadding(new Insets(100, 100, 100, 100));

                    Text title2 = new Text("REALIZAR LOCAÇÃO");
                    title2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                    title2.setTextAlignment(TextAlignment.CENTER);
                    locacao.add(title2, 0, 0);

                    Label tipoClienteLabel = new Label("Selecione o tipo de cliente");
                    tipoClienteLabel.setId("id");
                    locacao.add(tipoClienteLabel, 0, 1);

                    ComboBox<String> tipoClienteCombobox = new ComboBox<>();
                    tipoClienteCombobox.setId("id");
                    tipoClienteCombobox.getItems().addAll(Arrays.asList("PF", "PJ"));
                    locacao.add(tipoClienteCombobox, 1, 1);


                    Text actiontarget = new Text();
                    locacao.add(actiontarget, 0, 14);
                    actiontarget.setId("action");

                    Button consult = new Button("VERIFICAR");
                    HBox buttonc = new HBox(10);
                    buttonc.setAlignment(Pos.BOTTOM_LEFT);
                    buttonc.getChildren().add(consult);
                    locacao.add(buttonc, 0, 12);

                    Button selectClient = new Button("Selecionar tipo de cliente");
                    locacao.add(selectClient, 2, 1);

                    selectClient.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            actiontarget.setText("");
                            String tipoCliente = tipoClienteCombobox.getValue();

                            Label clientTipo = new Label("Escolha o cliente");
                            locacao.add(clientTipo,0,2);

                            ComboBox<Cliente> cliente = new ComboBox<>();
                            locacao.add(cliente,1,2);


                            if (clientTipo.equals("PF")) {
                                cliente.getItems().addAll((Cliente) ClienteRepository.getInstance().findAll().stream().filter(cliente1 -> cliente1 instanceof PessoaFisica));
                            }
                            if (clientTipo.equals("PJ")) {
                                cliente.getItems().addAll((Cliente) ClienteRepository.getInstance().findAll().stream().filter(cliente1 -> cliente1 instanceof PessoaJuridica));
                            }

                            Label categoriaLabel = new Label("Categoria: ");
                            locacao.add(categoriaLabel, 0, 4);

                            ComboBox<Categoria> categoriaComboBox1 = new ComboBox<>();
                            categoriaComboBox1.getItems().addAll(CategoriaRepository.getInstance().findAll());
                            locacao.add(categoriaComboBox1, 1, 4);

                            Button selecionarCategoria = new Button("Selecionar Categoria");
                            locacao.add(selecionarCategoria, 2, 4);
                            selecionarCategoria.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    Categoria categoria1 = categoriaComboBox1.getValue();
                                    List<Automovel> all = AutomovelRepository.getInstance().findAll();
                                    List<Automovel> fromThisCategoria = all.stream()
                                            .filter(auto -> auto.getModelo().getCategoria().getCodigo().equals(categoria1.getCodigo()))
                                            .filter(Automovel::isDisponivel)
                                            .collect(toList());
                                    if (fromThisCategoria.isEmpty()) {
                                        actiontarget.setText("Nenhum automovel disponível para esta categoria");
                                        locacao.getChildren().removeIf(child -> child.getId() != null && child.getId().equals("id"));
                                    } else {
                                        actiontarget.setText("");
                                        Label autoLabel = new Label("Automóveis Disponíveis");
                                        autoLabel.setId("id");

                                        locacao.add(autoLabel, 0, 6);
                                        ComboBox<Automovel> automovelComboBox = new ComboBox<>();
                                        automovelComboBox.getItems().addAll(fromThisCategoria);
                                        automovelComboBox.setId("id");
                                        locacao.add(automovelComboBox, 1, 6);

                                        Label dataInicial = new Label("Data inicial (DD/MM/AAA) ");
                                        locacao.add(dataInicial, 0, 7);
                                        dataInicial.setId("id");

                                        TextField dataInicialInput = new TextField();
                                        locacao.add(dataInicialInput, 1, 7);
                                        dataInicialInput.setId("id");

                                        Label dataFinal = new Label("Data final (DD/MM/AAA): ");
                                        locacao.add(dataFinal, 0, 8);
                                        dataFinal.setId("id");

                                        TextField dataFinalInput = new TextField();
                                        locacao.add(dataFinalInput, 1, 8);
                                        dataFinalInput.setId("id");

                                        consult.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent actionEvent) {
                                                Cliente cli = cliente.getValue();
                                                Locacao locacao1 = new Locacao(cli.getCPFCNPJ(), dataInicialInput.getText(), dataFinalInput.getText(), automovelComboBox.getValue().getPlaca());
                                                locacaoRepository.save(locacao1);
                                                try {
                                                    double valorLocacao = locacao1.calcularValorLocacao();
                                                    buttonc.getChildren().removeIf(child -> child.getId() != null && child.getId().equals("valorLocacao"));
                                                    Text text = new Text("O valor da locação é: " + valorLocacao);
                                                    text.setId("valorLocacao");
                                                    locacao.add(text, 1, 9);
                                                } catch (Exception e) {
                                                    actiontarget.setText("Dados inválidos. Por favor, corrija os dados e tente novamente.");
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    });

                    Button mmenu = new Button("MENU");
                    HBox hmenu = new HBox(10);
                    hmenu.setAlignment(Pos.BOTTOM_RIGHT);
                    hmenu.getChildren().add(mmenu);
                    locacao.add(hmenu, 0, 20);
                    mmenu.setOnAction(actEven -> {
                        Principal principal = new Principal();
                        try {
                            principal.start(menuStage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    menuStage.setScene(new Scene(locacao));
                    menuStage.show();
                });


                Button finalizarLocacao = new Button("FINALIZAR LOCAÇÃO");
                HBox button5 = new HBox(10);
                button5.setAlignment(Pos.BOTTOM_LEFT);
                button5.getChildren().add(finalizarLocacao);
                opcoeAtendente.add(button5, 0, 5);
                finalizarLocacao.setOnAction(actionEvent1 -> {
                    GridPane finalizar = new GridPane();
                    finalizar.setAlignment(Pos.CENTER);
                    finalizar.setHgap(10);
                    finalizar.setVgap(10);
                    finalizar.setPadding(new Insets(50, 100, 100, 100));

                    Text finalizaLocacao = new Text("FINALIZAR LOCACÃO");
                    finalizaLocacao.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                    finalizaLocacao.setTextAlignment(TextAlignment.CENTER);
                    finalizar.add(finalizaLocacao, 0, 0);

                    Text action = new Text();
                    action.setId("action");
                    action.setFill(Color.FIREBRICK);
                    finalizar.add(action, 0, 6);

                    List<Locacao> locacoes = locacaoRepository.filter(locacao -> !locacao.isFinalizada());
                    if (locacoes.isEmpty()) {
                        action.setText("Nenhuma locação para ser finalizada");
                    } else {

                        Text locacao = new Text("Locações disponiveis no sistema");
                        locacao.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                        locacao.setTextAlignment(TextAlignment.CENTER);
                        finalizar.add(locacao, 0, 1);

                        Text loc = new Text();
                        loc.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
                        loc.setTextAlignment(TextAlignment.CENTER);
                        locacoes.forEach(str -> loc.setText(str.toString()));
                        finalizar.add(loc, 0, 2);

                        Label cod = new Label("Digite o codigo para finalizar:");
                        finalizar.add(cod, 0, 4);

                        TextField codigo = new TextField();
                        finalizar.add(codigo, 1, 4);
                        Button finalLocacao = new Button("FINALIZAR");
                        HBox hfinal = new HBox(10);
                        hfinal.setAlignment(Pos.BOTTOM_LEFT);
                        hfinal.getChildren().add(finalLocacao);
                        finalizar.add(finalLocacao, 0, 5);
                        Text action1 = new Text();
                        action.setId("act1");
                        finalizar.add(action1, 0, 5);
                        finalLocacao.setOnAction(act -> {

                            int codigos = Integer.parseInt(codigo.getText());
                            Locacao locacao1 = locacoes.stream().filter(locacao2 -> locacao2.getCodigo().equals(codigos)).findFirst().orElse(null);
                            if (codigo.getText().isEmpty() || locacao1 == null)
                                action1.setText("Preencha com um codigo valido");
                            else {
                                GridPane finalizando = new GridPane();
                                finalizando.setAlignment(Pos.CENTER);
                                finalizando.setHgap(10);
                                finalizando.setVgap(10);
                                finalizando.setPadding(new Insets(50, 100, 100, 100));

                                Text acidente = new Text("Ocorreram acidentes com esse veiculo durante a locação?");
                                acidente.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                                acidente.setTextAlignment(TextAlignment.CENTER);
                                finalizando.add(acidente, 0, 0);

                                Text removido = new Text();
                                removido.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
                                removido.setTextAlignment(TextAlignment.CENTER);
                                finalizando.add(removido, 0, 3);

                                Text actiontarget = new Text();
                                actiontarget.setId("actiontarget");
                                actiontarget.setFill(Color.FIREBRICK);
                                finalizando.add(actiontarget, 0, 6);
                                locacao1.finalizar();

                                Button sim = new Button("SIM");
                                HBox hsim = new HBox(10);
                                hsim.setAlignment(Pos.BOTTOM_LEFT);
                                hsim.getChildren().add(sim);
                                finalizando.add(sim, 0, 1);

                                sim.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                        finalizando.add(removido, 0, 3);
                                        if (locacao1.getAuto().isVelhoDemaisParaAFrota()) {
                                            removido.setText("O veículo não está mais em condições de operar e foi removido da frota\n" +
                                                    "Pelo motivo de ser velho demais.\nE por ter sofrido um acidente");
                                        } else {
                                            removido.setText("O veículo não está mais em condições de operar e foi removido da frota\n" +
                                                    "E por ter sofrido um acidente");
                                        }

                                        actiontarget.setText("Locação finalizada");
                                    }
                                });

                                Button nao = new Button("NÃO");
                                HBox hnao = new HBox(10);
                                hnao.setAlignment(Pos.BOTTOM_LEFT);
                                hnao.getChildren().add(nao);
                                finalizando.add(nao, 1, 1);
                                nao.setOnAction(actionEvent3 -> {
                                    finalizando.add(removido, 0, 2);
                                    if (locacao1.getAuto().isVelhoDemaisParaAFrota()) {
                                        removido.setText("O veículo não está mais em condições de operar e foi removido da frota\n" +
                                                "Por ter sofrido um acidente");
                                        finalizando.add(removido, 0, 2);

                                    }
                                    actiontarget.setText("Locação finalizada");
                                });
                                Button mmenu = new Button("MENU");
                                HBox hmenu = new HBox(10);
                                hmenu.setAlignment(Pos.BOTTOM_RIGHT);
                                hmenu.getChildren().add(mmenu);
                                finalizando.add(hmenu, 0, 20);
                                mmenu.setOnAction(actEven -> {
                                    Principal principal = new Principal();
                                    try {
                                        principal.start(menuStage);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                });
                                menuStage.setScene(new Scene(finalizando));
                                menuStage.show();
                            }
                        });

                    }
                    Button mmenu = new Button("MENU");
                    HBox hmenu = new HBox(10);
                    hmenu.setAlignment(Pos.BOTTOM_RIGHT);
                    hmenu.getChildren().add(mmenu);
                    finalizar.add(hmenu, 0, 20);
                    mmenu.setOnAction(actEven -> {
                        Principal principal = new Principal();
                        try {
                            principal.start(menuStage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    menuStage.setScene(new Scene(finalizar));
                    menuStage.show();
                });

                Button mmenu = new Button("MENU");
                HBox hmenu = new HBox(10);
                hmenu.setAlignment(Pos.BOTTOM_RIGHT);
                hmenu.getChildren().add(mmenu);
                opcoeAtendente.add(hmenu, 0, 20);
                mmenu.setOnAction(actEven -> {
                    Principal principal = new Principal();
                    try {
                        principal.start(menuStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                Scene atendentesOpcao = new Scene(opcoeAtendente);
                menuStage.setScene(atendentesOpcao);
                menuStage.show();
            }
        });

        Button gerente = new Button("GERENTE");
        HBox gerentBt = new HBox(10);
        gerentBt.setAlignment(Pos.BOTTOM_RIGHT);
        gerentBt.getChildren().add(gerente);
        telaCarga.add(gerentBt, 0, 4);
        gerente.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GridPane opcoeGerente = new GridPane();
                opcoeGerente.setAlignment(Pos.CENTER);
                opcoeGerente.setHgap(10);
                opcoeGerente.setVgap(10);
                opcoeGerente.setPadding(new Insets(50, 100, 100, 100));

                Text title = new Text("MENU GERENTE");
                title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                title.setTextAlignment(TextAlignment.CENTER);
                opcoeGerente.add(title, 0, 0);

                Button cadastroCliente = new Button("CADASTRAR CLIENTE");
                HBox button1 = new HBox(10);
                button1.setAlignment(Pos.BOTTOM_LEFT);
                button1.getChildren().add(cadastroCliente);
                opcoeGerente.add(button1, 0, 1);
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
                opcoeGerente.add(button2, 0, 2);
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
                opcoeGerente.add(button3, 0, 3);
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
                opcoeGerente.add(button4, 0, 4);

                realizarLocacao.setOnAction(actionEvent1 -> {
                    GridPane locacao = new GridPane();
                    locacao.setAlignment(Pos.CENTER);
                    locacao.setHgap(10);
                    locacao.setVgap(10);
                    locacao.setPadding(new Insets(100, 100, 100, 100));

                    Text title2 = new Text("REALIZAR LOCAÇÃO");
                    title2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                    title2.setTextAlignment(TextAlignment.CENTER);
                    locacao.add(title2, 0, 0);

                    Label tipoClienteLabel = new Label("Selecione o tipo de cliente");
                    tipoClienteLabel.setId("id");
                    locacao.add(tipoClienteLabel, 0, 1);

                    ComboBox<String> tipoClienteCombobox = new ComboBox<>();
                    tipoClienteCombobox.setId("id");
                    tipoClienteCombobox.getItems().addAll(Arrays.asList("PF", "PJ"));
                    locacao.add(tipoClienteCombobox, 1, 1);


                    Text actiontarget = new Text();
                    locacao.add(actiontarget, 0, 14);
                    actiontarget.setId("action");

                    Button consult = new Button("VERIFICAR");
                    HBox buttonc = new HBox(10);
                    buttonc.setAlignment(Pos.BOTTOM_LEFT);
                    buttonc.getChildren().add(consult);
                    locacao.add(buttonc, 0, 12);

                    Button selectClient = new Button("Selecionar tipo de cliente");
                    locacao.add(selectClient, 2, 1);

                    selectClient.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            actiontarget.setText("");
                            String tipoCliente = tipoClienteCombobox.getValue();

                            Label clientTipo = new Label("Escolha o cliente");
                            locacao.add(clientTipo,0,2);

                            ComboBox<Cliente> cliente = new ComboBox<>();
                            locacao.add(cliente,1,2);


                            if (clientTipo.equals("PF")) {
                                cliente.getItems().addAll((Cliente) ClienteRepository.getInstance().findAll().stream().filter(cliente1 -> cliente1 instanceof PessoaFisica));
                            }
                            if (clientTipo.equals("PJ")) {
                                cliente.getItems().addAll((Cliente) ClienteRepository.getInstance().findAll().stream().filter(cliente1 -> cliente1 instanceof PessoaJuridica));
                            }

                            Label categoriaLabel = new Label("Categoria: ");
                            locacao.add(categoriaLabel, 0, 4);

                            ComboBox<Categoria> categoriaComboBox1 = new ComboBox<>();
                            categoriaComboBox1.getItems().addAll(CategoriaRepository.getInstance().findAll());
                            locacao.add(categoriaComboBox1, 1, 4);

                            Button selecionarCategoria = new Button("Selecionar Categoria");
                            locacao.add(selecionarCategoria, 2, 4);
                            selecionarCategoria.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    Categoria categoria1 = categoriaComboBox1.getValue();
                                    List<Automovel> all = AutomovelRepository.getInstance().findAll();
                                    List<Automovel> fromThisCategoria = all.stream()
                                            .filter(auto -> auto.getModelo().getCategoria().getCodigo().equals(categoria1.getCodigo()))
                                            .filter(Automovel::isDisponivel)
                                            .collect(toList());
                                    if (fromThisCategoria.isEmpty()) {
                                        actiontarget.setText("Nenhum automovel disponível para esta categoria");
                                        locacao.getChildren().removeIf(child -> child.getId() != null && child.getId().equals("id"));
                                    } else {
                                        actiontarget.setText("");
                                        Label autoLabel = new Label("Automóveis Disponíveis");
                                        autoLabel.setId("id");

                                        locacao.add(autoLabel, 0, 6);
                                        ComboBox<Automovel> automovelComboBox = new ComboBox<>();
                                        automovelComboBox.getItems().addAll(fromThisCategoria);
                                        automovelComboBox.setId("id");
                                        locacao.add(automovelComboBox, 1, 6);

                                        Label dataInicial = new Label("Data inicial (DD/MM/AAA) ");
                                        locacao.add(dataInicial, 0, 7);
                                        dataInicial.setId("id");

                                        TextField dataInicialInput = new TextField();
                                        locacao.add(dataInicialInput, 1, 7);
                                        dataInicialInput.setId("id");

                                        Label dataFinal = new Label("Data final (DD/MM/AAA): ");
                                        locacao.add(dataFinal, 0, 8);
                                        dataFinal.setId("id");

                                        TextField dataFinalInput = new TextField();
                                        locacao.add(dataFinalInput, 1, 8);
                                        dataFinalInput.setId("id");

                                        consult.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent actionEvent) {
                                                Cliente cli = cliente.getValue();
                                                Locacao locacao1 = new Locacao(cli.getCPFCNPJ(), dataInicialInput.getText(), dataFinalInput.getText(), automovelComboBox.getValue().getPlaca());
                                                locacaoRepository.save(locacao1);
                                                try {
                                                    double valorLocacao = locacao1.calcularValorLocacao();
                                                    buttonc.getChildren().removeIf(child -> child.getId() != null && child.getId().equals("valorLocacao"));
                                                    Text text = new Text("O valor da locação é: " + valorLocacao);
                                                    text.setId("valorLocacao");
                                                    locacao.add(text, 1, 9);
                                                } catch (Exception e) {
                                                    actiontarget.setText("Dados inválidos. Por favor, corrija os dados e tente novamente.");
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    });

                    Button mmenu = new Button("MENU");
                    HBox hmenu = new HBox(10);
                    hmenu.setAlignment(Pos.BOTTOM_RIGHT);
                    hmenu.getChildren().add(mmenu);
                    locacao.add(hmenu, 0, 20);
                    mmenu.setOnAction(actEven -> {
                        Principal principal = new Principal();
                        try {
                            principal.start(menuStage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    menuStage.setScene(new Scene(locacao));
                    menuStage.show();
                });

                Button finalizarLocacao = new Button("FINALIZAR LOCAÇÃO");
                HBox button5 = new HBox(10);
                button5.setAlignment(Pos.BOTTOM_LEFT);
                button5.getChildren().add(finalizarLocacao);
                opcoeGerente.add(button5, 0, 5);
                finalizarLocacao.setOnAction(actionEvent1 -> {
                    GridPane finalizar = new GridPane();
                    finalizar.setAlignment(Pos.CENTER);
                    finalizar.setHgap(10);
                    finalizar.setVgap(10);
                    finalizar.setPadding(new Insets(50, 100, 100, 100));

                    Text finalizaLocacao = new Text("FINALIZAR LOCACÃO");
                    finalizaLocacao.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                    finalizaLocacao.setTextAlignment(TextAlignment.CENTER);
                    finalizar.add(finalizaLocacao, 0, 0);

                    Text action = new Text();
                    action.setId("action");
                    action.setFill(Color.FIREBRICK);
                    finalizar.add(action, 0, 6);

                    List<Locacao> locacoes = locacaoRepository.filter(locacao -> !locacao.isFinalizada());
                    if (locacoes.isEmpty()) {
                        action.setText("Nenhuma locação para ser finalizada");
                    } else {

                        Text locacao = new Text("Locações disponiveis no sistema");
                        locacao.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                        locacao.setTextAlignment(TextAlignment.CENTER);
                        finalizar.add(locacao, 0, 1);

                        Text loc = new Text();
                        loc.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
                        loc.setTextAlignment(TextAlignment.CENTER);
                        locacoes.forEach(str -> loc.setText(str.toString()));
                        finalizar.add(loc, 0, 2);

                        Label cod = new Label("Digite o codigo para finalizar:");
                        finalizar.add(cod, 0, 4);

                        TextField codigo = new TextField();
                        finalizar.add(codigo, 1, 4);
                        Button finalLocacao = new Button("FINALIZAR");
                        HBox hfinal = new HBox(10);
                        hfinal.setAlignment(Pos.BOTTOM_LEFT);
                        hfinal.getChildren().add(finalLocacao);
                        finalizar.add(finalLocacao, 0, 5);
                        Text action1 = new Text();
                        action.setId("act1");
                        finalizar.add(action1, 0, 5);
                        finalLocacao.setOnAction(act -> {

                            int codigos = Integer.parseInt(codigo.getText());
                            Locacao locacao1 = locacoes.stream().filter(locacao2 -> locacao2.getCodigo().equals(codigos)).findFirst().orElse(null);
                            if (codigo.getText().isEmpty() || locacao1 == null)
                                action1.setText("Preencha com um codigo valido");
                            else {
                                GridPane finalizando = new GridPane();
                                finalizando.setAlignment(Pos.CENTER);
                                finalizando.setHgap(10);
                                finalizando.setVgap(10);
                                finalizando.setPadding(new Insets(50, 100, 100, 100));

                                Text acidente = new Text("Ocorreram acidentes com esse veiculo durante a locação?");
                                acidente.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                                acidente.setTextAlignment(TextAlignment.CENTER);
                                finalizando.add(acidente, 0, 0);

                                Text removido = new Text();
                                removido.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
                                removido.setTextAlignment(TextAlignment.CENTER);
                                finalizando.add(removido, 0, 3);

                                Text actiontarget = new Text();
                                actiontarget.setId("actiontarget");
                                actiontarget.setFill(Color.FIREBRICK);
                                finalizando.add(actiontarget, 0, 6);
                                locacao1.finalizar();

                                Button sim = new Button("SIM");
                                HBox hsim = new HBox(10);
                                hsim.setAlignment(Pos.BOTTOM_LEFT);
                                hsim.getChildren().add(sim);
                                finalizando.add(sim, 0, 1);

                                sim.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                        finalizando.add(removido, 0, 3);
                                        if (locacao1.getAuto().isVelhoDemaisParaAFrota()) {
                                            removido.setText("O veículo não está mais em condições de operar e foi removido da frota\n" +
                                                    "Pelo motivo de ser velho demais.\nE por ter sofrido um acidente");
                                        } else {
                                            removido.setText("O veículo não está mais em condições de operar e foi removido da frota\n" +
                                                    "E por ter sofrido um acidente");
                                        }

                                        actiontarget.setText("Locação finalizada");
                                    }
                                });

                                Button nao = new Button("NÃO");
                                HBox hnao = new HBox(10);
                                hnao.setAlignment(Pos.BOTTOM_LEFT);
                                hnao.getChildren().add(nao);
                                finalizando.add(nao, 1, 1);
                                nao.setOnAction(actionEvent3 -> {
                                    finalizando.add(removido, 0, 2);
                                    if (locacao1.getAuto().isVelhoDemaisParaAFrota()) {
                                        removido.setText("O veículo não está mais em condições de operar e foi removido da frota\n" +
                                                "Por ter sofrido um acidente");
                                        finalizando.add(removido, 0, 2);

                                    }
                                    actiontarget.setText("Locação finalizada");

                                });
                                Button mmenu = new Button("MENU");
                                HBox hmenu = new HBox(10);
                                hmenu.setAlignment(Pos.BOTTOM_RIGHT);
                                hmenu.getChildren().add(mmenu);
                                finalizando.add(hmenu, 0, 20);
                                mmenu.setOnAction(actEven -> {
                                    Principal principal = new Principal();
                                    try {
                                        principal.start(menuStage);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                });
                                menuStage.setScene(new Scene(finalizando));
                                menuStage.show();
                            }
                        });
                    }
                    Button mmenu = new Button("MENU");
                    HBox hmenu = new HBox(10);
                    hmenu.setAlignment(Pos.BOTTOM_RIGHT);
                    hmenu.getChildren().add(mmenu);
                    finalizar.add(hmenu, 0, 20);
                    mmenu.setOnAction(actEven -> {
                        Principal principal = new Principal();
                        try {
                            principal.start(menuStage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    menuStage.setScene(new Scene(finalizar));
                    menuStage.show();
                });

                Button cadastrarCategoria = new Button("CADASTRAR NOVA CATEGORIA DE AUTOMOVEL");
                HBox button6 = new HBox(10);
                button6.setAlignment(Pos.BOTTOM_LEFT);
                button6.getChildren().add(cadastrarCategoria);
                opcoeGerente.add(button6, 0, 6);
                cadastrarCategoria.setOnAction(actionEvent1 -> {
                    try {
                        cadastroMenu.setButton(2);
                        cadastroMenu.start(menuStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                Button cadastrarMarca = new Button("CADASTRAR NOVA MARCA DE AUTOMOVEL");
                HBox button7 = new HBox(10);
                button7.setAlignment(Pos.BOTTOM_LEFT);
                button7.getChildren().add(cadastrarMarca);
                opcoeGerente.add(button7, 0, 7);
                cadastrarMarca.setOnAction(actionEvent1 -> {
                    try {
                        cadastroMenu.setButton(3);
                        cadastroMenu.start(menuStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                Button cadastrarModelo = new Button("CADASTRAR NOVO MODELO DE AUTOMOVEL");
                HBox button8 = new HBox(10);
                button8.setAlignment(Pos.BOTTOM_LEFT);
                button8.getChildren().add(cadastrarModelo);
                opcoeGerente.add(button8, 0, 8);
                cadastrarModelo.setOnAction(actionEvent1 -> {
                    try {
                        cadastroMenu.setButton(4);
                        cadastroMenu.start(menuStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                Button cadastrarAutomovel = new Button("CADASTRAR NOVO AUTOMOVEL");
                HBox button9 = new HBox(10);
                button9.setAlignment(Pos.BOTTOM_LEFT);
                button9.getChildren().add(cadastrarAutomovel);
                opcoeGerente.add(button9, 0, 9);
                cadastrarAutomovel.setOnAction(actionEvent1 -> {
                    try {
                        cadastroMenu.setButton(5);
                        cadastroMenu.start(menuStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                Button removerAutomovel = new Button("REMOVER AUTOMÓVEL");
                HBox button10 = new HBox(10);
                button10.setAlignment(Pos.BOTTOM_LEFT);
                button10.getChildren().add(removerAutomovel);
                opcoeGerente.add(button10, 0, 10);
                removerAutomovel.setOnAction(actionEvent1 -> {
                    GridPane removerAuto = new GridPane();
                    removerAuto.setAlignment(Pos.CENTER);
                    removerAuto.setHgap(10);
                    removerAuto.setVgap(10);
                    removerAuto.setPadding(new Insets(50, 100, 100, 100));

                    Text title3 = new Text("REMOVER AUTOMOVEL");
                    title3.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                    title3.setTextAlignment(TextAlignment.CENTER);
                    removerAuto.add(title3,0,0);

                    Label text3 = new Label("Placa: ");
                    removerAuto.add(text3,0,1);

                    TextField placa = new TextField();
                    removerAuto.add(placa,1,1);

                    Text action2 = new Text();
                    action2.setId("action");
                    action2.setFill(Color.FIREBRICK);
                    removerAuto.add(action2,0,6);

                    Button remover = new Button("REMOVER");
                    HBox remov = new HBox(10);
                    remov.setAlignment(Pos.BOTTOM_LEFT);
                    remov.getChildren().add(remover);
                    removerAuto.add(remov, 0, 3);
                    remover.setOnAction(actionEvent2 -> {
                        if (placa.getText().isEmpty()) action2.setText("Por favor, preencha o campo");
                        else {
                            boolean result = automovelRepository.removeByPlaca(placa.getText());
                            action2.setText(result ? "Removido com sucesso!" : "Um automóvel com essa placa não existe");
                        }
                    });

                    Button mmenu = new Button("MENU");
                    HBox hmenu = new HBox(10);
                    hmenu.setAlignment(Pos.BOTTOM_RIGHT);
                    hmenu.getChildren().add(mmenu);
                    removerAuto.add(hmenu, 0, 20);
                    mmenu.setOnAction(actEven -> {
                        Principal principal = new Principal();
                        try {
                            principal.start(menuStage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    menuStage.setScene(new Scene(removerAuto));
                    menuStage.show();
                });

                Button consultarLocacoes = new Button("CONSULTAR LOCAÇÕES");
                HBox button11 = new HBox(10);
                button11.setAlignment(Pos.BOTTOM_LEFT);
                button11.getChildren().add(consultarLocacoes);
                opcoeGerente.add(button11, 0, 11);
                consultarLocacoes.setOnAction(actionE -> {
                    try {
                        consultaMenu.setButton(4);
                        consultaMenu.start(menuStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                Button consultarClientes = new Button("CONSULTAR CLIENTES");
                HBox button12 = new HBox(10);
                button12.setAlignment(Pos.BOTTOM_LEFT);
                button12.getChildren().add(consultarClientes);
                opcoeGerente.add(button12, 0, 12);
                consultarClientes.setOnAction(actionE1 -> {
                    try {
                        consultaMenu.setButton(5);
                        consultaMenu.start(menuStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                Button consultarAutomoveis = new Button("CONSULTAR AUTOMOVEIS CADASTRADOS");
                HBox button = new HBox(10);
                button.setAlignment(Pos.BOTTOM_LEFT);
                button.getChildren().add(consultarAutomoveis);
                opcoeGerente.add(button, 0, 13);
                consultarAutomoveis.setOnAction(actionE -> {
                    try {
                        consultaMenu.setButton(6);
                        consultaMenu.start(menuStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });


                Button cargaDeDados = new Button("CARGA DE DADOS");
                HBox button14 = new HBox(10);
                button14.setAlignment(Pos.BOTTOM_LEFT);
                button14.getChildren().add(cargaDeDados);
                opcoeGerente.add(button14, 0, 14);
                cargaDeDados.setOnAction(actionE -> {
                    GridPane telaCarga = new GridPane();
                    telaCarga.setAlignment(Pos.CENTER);
                    telaCarga.setHgap(10);
                    telaCarga.setVgap(10);
                    telaCarga.setPadding(new Insets(50, 100, 100, 100));

                    Text title1 = new Text("CARGA DE DADOS");
                    title1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                    title1.setTextAlignment(TextAlignment.CENTER);
                    telaCarga.add(title1, 0, 0);

                    Label text = new Label("Nome do arquivo: ");
                    telaCarga.add(text, 0, 1);

                    TextField nomeArquivo = new TextField();
                    telaCarga.add(nomeArquivo, 1, 1);

                    Text action = new Text();
                    action.setId("action");
                    action.setFill(Color.FIREBRICK);
                    telaCarga.add(action, 0, 6);

                    Button carga = new Button("CONFIRMAR");
                    HBox hbutton = new HBox(10);
                    hbutton.setAlignment(Pos.BOTTOM_LEFT);
                    hbutton.getChildren().add(carga);
                    telaCarga.add(hbutton, 0, 5);
                    carga.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            GridPane telaCarga = new GridPane();
                            telaCarga.setAlignment(Pos.CENTER);
                            telaCarga.setHgap(10);
                            telaCarga.setVgap(10);
                            telaCarga.setPadding(new Insets(50, 100, 100, 100));

                            Text title4 = new Text("CARGA DE DADOS");
                            title4.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                            title4.setTextAlignment(TextAlignment.CENTER);
                            telaCarga.add(title4, 0, 0);

                            Path path = Paths.get("resources/" + nomeArquivo.getText() + ".txt");
                            try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())) {
                                String linha = null;
                                int cont = 0;
                                while ((linha = br.readLine()) != null) {
                                    Label nome = new Label(linha);
                                    telaCarga.add(nome, 0, cont);
                                    cont++;
                                }
                                action.setText("Arquivo Lido");
                            }
                            catch (IOException e) {
                                System.err.format("Erro de E/S: %s%n", e);
                                action.setText("Arro na lietura do arquivo");
                            }
                            menuStage.setScene(new Scene(telaCarga));
                            menuStage.show();
                        }
                    });

                    Button mmenu = new Button("MENU");
                    HBox hmenu = new HBox(10);
                    hmenu.setAlignment(Pos.BOTTOM_RIGHT);
                    hmenu.getChildren().add(mmenu);
                    telaCarga.add(hmenu, 0, 20);
                    mmenu.setOnAction(actEven -> {
                        Principal principal = new Principal();
                        try {
                            principal.start(menuStage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    menuStage.setScene(new Scene(telaCarga));
                    menuStage.show();
                });

                Button mmenu = new Button("MENU");
                HBox hmenu = new HBox(10);
                hmenu.setAlignment(Pos.BOTTOM_RIGHT);
                hmenu.getChildren().add(mmenu);
                opcoeGerente.add(hmenu, 0, 20);
                mmenu.setOnAction(actEven -> {
                    Principal principal = new Principal();
                    try {
                        principal.start(menuStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                Scene gerenteOpcao = new Scene(opcoeGerente);
                menuStage.setScene(gerenteOpcao);
                menuStage.show();

            }
        });

        Scene scene = new Scene(telaCarga);
        menuStage.setScene(scene);
        menuStage.show();

    }
}
