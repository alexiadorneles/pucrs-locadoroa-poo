package menu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import domain.automovel.*;
import domain.cliente.Cliente;
import domain.cliente.PessoaFisica;
import domain.cliente.PessoaJuridica;
import repository.*;

import java.util.List;

public class CadastroMenu extends Application {

    private int button = 0;
    private final Repository<Modelo, Integer> modeloRepository;
    private final Repository<Automovel, String> automovelRepository;
    private final Repository<Cliente, String> clienteRepository;
    private final Repository<Marca, Integer> marcaRepository;
    private final Repository<Categoria, Integer> categoriaRepository;

    public CadastroMenu(
            Repository<Modelo, Integer> modeloRepository,
            Repository<Automovel, String> automovelRepository,
            Repository<Cliente, String> clienteRepository,
            Repository<Marca, Integer> marcaRepository,
            Repository<Categoria, Integer> categoriaRepository
    ) {
        this.modeloRepository = modeloRepository;
        this.automovelRepository = automovelRepository;
        this.clienteRepository = clienteRepository;
        this.marcaRepository = marcaRepository;
        this.categoriaRepository = categoriaRepository;
    }


    public void setButton(int i) {
        if (i > 0 && i < 15) button = i;
        else button = 0;
    }

    @Override
    public void start(Stage menuCadastro) throws Exception {
        menuCadastro.setTitle("---------- LOCADORA AJE ----------");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(100, 100, 100, 100));

        switch (button) {
            case 1:
                Text title = new Text("CADASTRAR CLIENTE");
                title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                title.setTextAlignment(TextAlignment.CENTER);
                grid.add(title, 0, 0);

                Text text = new Text("ESCOLHA O TIPO DE CLIENTE");

                Button pf = new Button("PESSOA FISICA");
                HBox button1 = new HBox(10);
                button1.setAlignment(Pos.BOTTOM_LEFT);
                button1.getChildren().add(pf);
                grid.add(button1, 0, 1);
                pf.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        GridPane pf = new GridPane();
                        pf.setAlignment(Pos.CENTER);
                        pf.setHgap(10);
                        pf.setVgap(10);
                        pf.setPadding(new Insets(100, 100, 100, 100));

                        Text title = new Text("DADOS DO CLIENTE");
                        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                        title.setTextAlignment(TextAlignment.CENTER);
                        pf.add(title, 0, 0);

                        Label nome = new Label("Nome: ");
                        pf.add(nome, 0, 1);

                        TextField nomeCliente = new TextField();
                        pf.add(nomeCliente, 1, 1);

                        Label telefone = new Label("Telefone: ");
                        pf.add(telefone, 0, 2);

                        TextField telefoneCliente = new TextField();
                        pf.add(telefoneCliente, 1, 2);

                        Label cpf = new Label("CPF: ");
                        pf.add(cpf, 0, 3);

                        TextField cpfCliente = new TextField();
                        pf.add(cpfCliente, 1, 3);

                        Button confirmarCadastro = new Button("CADASTRAR");
                        HBox btn = new HBox(10);
                        btn.setAlignment(Pos.BOTTOM_RIGHT);
                        btn.getChildren().add(confirmarCadastro);
                        pf.add(btn, 1, 5);

                        final Text actiontarget = new Text();
                        pf.add(actiontarget, 1, 6);
                        actiontarget.setId("actiontarget");

                        confirmarCadastro.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                actiontarget.setFill(Color.FIREBRICK);
                                Cliente c = clienteRepository.findOne(cpfCliente.getText());

                                if (nomeCliente.getText().trim().isEmpty() || telefoneCliente.getText().isEmpty() ||
                                        cpfCliente.getText().isEmpty())
                                    actiontarget.setText("Por favor preencha todos os campos");
                                if (c != null) actiontarget.setText("Usuário ja existente");
                                else {
                                    actiontarget.setText("Cadastro concluido");
                                    Cliente cliente = new PessoaFisica(nomeCliente.getText(), telefoneCliente.getText(), cpfCliente.getText());
                                    clienteRepository.save(cliente);
                                    System.out.println(cliente.toString());
                                }
                            }
                        });
                        Button mmenu = new Button("MENU");
                        HBox hmenu = new HBox(10);
                        hmenu.setAlignment(Pos.BOTTOM_RIGHT);
                        hmenu.getChildren().add(mmenu);
                        pf.add(hmenu, 0, 20);
                        mmenu.setOnAction(actEven -> {
                            Principal principal = new Principal();
                            try {
                                principal.start(menuCadastro);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        menuCadastro.setScene(new Scene(pf));
                        menuCadastro.show();
                    }
                });

                Button pj = new Button("PESSOA JURIDICA");
                HBox button2 = new HBox(10);
                button2.setAlignment(Pos.BOTTOM_LEFT);
                button2.getChildren().add(pj);
                grid.add(button2, 1, 1);
                pj.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        GridPane pj = new GridPane();
                        pj.setAlignment(Pos.CENTER);
                        pj.setHgap(10);
                        pj.setVgap(10);
                        pj.setPadding(new Insets(100, 100, 100, 100));

                        Text title = new Text("DADOS DO CLIENTE");
                        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                        title.setTextAlignment(TextAlignment.CENTER);
                        pj.add(title, 0, 0);

                        Label nome = new Label("Nome: ");
                        pj.add(nome, 0, 1);

                        TextField nomeCliente = new TextField();
                        pj.add(nomeCliente, 1, 1);

                        Label telefone = new Label("Telefone: ");
                        pj.add(telefone, 0, 2);

                        TextField telefoneCliente = new TextField();
                        pj.add(telefoneCliente, 1, 2);

                        Label cnpj = new Label("CNPJ: ");
                        pj.add(cnpj, 0, 3);

                        TextField cnpjCliente = new TextField();
                        pj.add(cnpjCliente, 1, 3);

                        Button confirmarCadastro = new Button("CADASTRAR");
                        HBox btn = new HBox(10);
                        btn.setAlignment(Pos.BOTTOM_RIGHT);
                        btn.getChildren().add(confirmarCadastro);
                        pj.add(btn, 1, 5);

                        final Text actiontarget = new Text();
                        pj.add(actiontarget, 1, 6);
                        actiontarget.setId("actiontarget");

                        confirmarCadastro.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                actiontarget.setFill(Color.FIREBRICK);
                                Cliente c = clienteRepository.findOne(cnpjCliente.getText());
                                if (nomeCliente.getText().trim().isEmpty() || telefoneCliente.getText().isEmpty() ||
                                        cnpjCliente.getText().isEmpty())
                                    actiontarget.setText("Por favor preencha todos os campos com dados validos");
                                if (c != null) actiontarget.setText("Usuário ja existente");
                                else {
                                    actiontarget.setText("Cadastro concluido");
                                    Cliente cliente = new PessoaJuridica(nomeCliente.getText(), telefoneCliente.getText(), cnpjCliente.getText());
                                    clienteRepository.save(cliente);
                                    System.out.println(cliente.toString());
                                }
                            }
                        });
                        Button mmenu = new Button("MENU");
                        HBox hmenu = new HBox(10);
                        hmenu.setAlignment(Pos.BOTTOM_RIGHT);
                        hmenu.getChildren().add(mmenu);
                        pj.add(hmenu, 0, 20);
                        mmenu.setOnAction(actEven -> {
                            Principal principal = new Principal();
                            try {
                                principal.start(menuCadastro);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        menuCadastro.setScene(new Scene(pj));
                        menuCadastro.show();
                    }
                });

                Button mmenu = new Button("MENU");
                HBox hmenu = new HBox(10);
                hmenu.setAlignment(Pos.BOTTOM_RIGHT);
                hmenu.getChildren().add(mmenu);
                grid.add(hmenu, 0, 20);
                mmenu.setOnAction(actEven -> {
                    Principal principal = new Principal();
                    try {
                        principal.start(menuCadastro);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                Scene scene = new Scene(grid);
                menuCadastro.setScene(scene);
                menuCadastro.show();
                break;

            case 2:
                Text title1 = new Text("CADASTRAR NOVA CATEGORIA");
                title1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                title1.setTextAlignment(TextAlignment.CENTER);
                grid.add(title1, 0, 0);

                Label nomeCat = new Label("Nome da Categoria: ");
                grid.add(nomeCat, 0, 1);

                TextField categoriaa = new TextField();
                grid.add(categoriaa, 1, 1);

                Button cadastroCat = new Button("CADASTRAR CATEGORIA");
                HBox button3 = new HBox(10);
                button3.setAlignment(Pos.BOTTOM_LEFT);
                button3.getChildren().add(cadastroCat);
                grid.add(button3, 0, 3);

                final Text actiontarget = new Text();
                grid.add(actiontarget, 1, 6);
                actiontarget.setId("actiontarget");

                cadastroCat.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        actiontarget.setFill(Color.FIREBRICK);
                        List<Categoria> a = categoriaRepository.findAll();

                        if (categoriaa.getText().trim().isEmpty()) actiontarget.setText("Por favor preencha todos os campos");
                        if(a.stream().filter(cat -> cat.getNome().equals(categoriaa.getText())).findAny().isPresent()) actiontarget.setText("Categoria ja existente");
                        else {
                            Categoria categoria1 = new Categoria(categoriaa.getText());
                            categoriaRepository.save(categoria1);
                            actiontarget.setText("Cadastro concluido");
                            System.out.println(categoria1.toString());
                        }
                    }
                });

                Button menu = new Button("MENU");
                HBox hbmenu = new HBox(10);
                hbmenu.setAlignment(Pos.BOTTOM_RIGHT);
                hbmenu.getChildren().add(menu);
                grid.add(hbmenu, 0, 20);
                menu.setOnAction(actEven -> {
                    Principal principal = new Principal();
                    try {
                        principal.start(menuCadastro);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                Scene scene1 = new Scene(grid);
                menuCadastro.setScene(scene1);
                menuCadastro.show();
                break;

            case 3:

                Text title2 = new Text("CADASTRAR NOVA MARCA");
                title2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                title2.setTextAlignment(TextAlignment.CENTER);
                grid.add(title2, 0, 0);

                Label nomeMarca = new Label("Nome da Marca: ");
                grid.add(nomeMarca, 0, 1);

                TextField marcaAdd = new TextField();
                grid.add(marcaAdd, 1, 1);

                Button cadastroMarca = new Button("CADASTRAR MARCA");
                HBox button4 = new HBox(10);
                button4.setAlignment(Pos.BOTTOM_LEFT);
                button4.getChildren().add(cadastroMarca);
                grid.add(button4, 0, 2);

                final Text actiontarget1 = new Text();
                grid.add(actiontarget1, 1, 6);
                actiontarget1.setId("actiontarget");

                cadastroMarca.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        actiontarget1.setFill(Color.FIREBRICK);
                        List<Marca> m = marcaRepository.findAll();

                        if (marcaAdd.getText().trim().isEmpty())
                            actiontarget1.setText("Por favor preencha todos os campos");

                        if(m.stream().filter(marca -> marca.getNome().equals(marcaAdd.getText())).findAny().isPresent()) actiontarget1.setText("Marca ja existente");
                        else {
                            Marca ma = new Marca(marcaAdd.getText());
                            marcaRepository.save(ma);
                            actiontarget1.setText("Cadastro concluido");
                            System.out.println(ma.toString());
                        }
                    }
                });

                Button menu1 = new Button("MENU");
                HBox hbmenu1 = new HBox(10);
                hbmenu1.setAlignment(Pos.BOTTOM_RIGHT);
                hbmenu1.getChildren().add(menu1);
                grid.add(hbmenu1, 0, 20);
                menu1.setOnAction(actEven -> {
                    Principal principal = new Principal();
                    try {
                        principal.start(menuCadastro);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                Scene scene2 = new Scene(grid);
                menuCadastro.setScene(scene2);
                menuCadastro.show();
                break;

            case 4:

                Text title3 = new Text("CADASTRAR NOVA MODELO");
                title3.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                title3.setTextAlignment(TextAlignment.CENTER);
                grid.add(title3, 0, 0);

                Label nome = new Label("Nome do Modelo: ");
                grid.add(nome, 0, 1);

                TextField nomeModelo = new TextField();
                grid.add(nomeModelo, 1, 1);

                Label valor = new Label("Valor do Modelo: ");
                grid.add(valor, 0, 2);

                TextField valorModelo = new TextField();
                grid.add(valorModelo, 1, 2);

                Label categoria = new Label("Escolha a Categoria: ");
                grid.add(categoria, 0, 3);

                final ComboBox<Categoria> categ = new ComboBox<>();
                categ.getItems().addAll(categoriaRepository.findAll());
                grid.add(categ, 1, 3);
                categ.setValue(categoriaRepository.findOne(0));

                Label marca1 = new Label("Escolha o Marca: ");
                grid.add(marca1, 0, 4);

                final ComboBox<Marca> marcaModelo = new ComboBox<>();
                marcaModelo.getItems().addAll(marcaRepository.findAll());
                grid.add(marcaModelo, 1, 4);
                marcaModelo.setValue(marcaRepository.findOne(0));

                Button cadastroModelo = new Button("CADASTRAR MODELO");
                HBox button7 = new HBox(10);
                button7.setAlignment(Pos.BOTTOM_LEFT);
                button7.getChildren().add(cadastroModelo);
                grid.add(cadastroModelo, 0, 10);

                final Text actiontarget2 = new Text();
                grid.add(actiontarget2, 1, 12);
                actiontarget2.setId("actiontarget");

                Label tipoModelo = new Label("Escolha o Tipo de Modelo: ");
                grid.add(tipoModelo, 0, 5);

                Button nacional = new Button("Nacional");
                HBox button5 = new HBox(10);
                button5.setAlignment(Pos.BOTTOM_LEFT);
                button5.getChildren().add(nacional);
                grid.add(nacional, 0, 6);
                nacional.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Label ipi = new Label("Valor do ipi: ");
                        grid.add(ipi, 0, 7);

                        TextField valorIpi = new TextField();
                        grid.add(valorIpi, 1, 7);
                        cadastroModelo.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                actiontarget2.setFill(Color.FIREBRICK);
                                List<Modelo> model = modeloRepository.findAll();
                                if (nome.getText().trim().isEmpty() || valorModelo.getText().trim().isEmpty())
                                    actiontarget2.setText("Por favor preencha todos os campos");

                                if(model.stream().anyMatch(modelo-> modelo.getNome().equals(nomeModelo.getText()))) actiontarget2.setText("Modelo ja existente");
                                else {
                                    Categoria categoria = categ.getValue();
                                    Marca marca = marcaModelo.getValue();
                                    Modelo modelo = new ModeloNacional(nomeModelo.getText(), Double.parseDouble(valorModelo.getText()),
                                            categoria.getCodigo(), marca.getCodigo(), Double.parseDouble(valorIpi.getText()));
                                    modeloRepository.save(modelo);
                                    actiontarget2.setText("Cadastro concluido");
                                }
                            }
                        });

                    }
                });

                Button internacional = new Button("Internacional");
                HBox button6 = new HBox(10);
                button6.setAlignment(Pos.BOTTOM_RIGHT);
                button6.getChildren().add(internacional);
                grid.add(internacional, 0, 8);

                internacional.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Label taxa = new Label("Valor da Taxa de Importanção: ");
                        grid.add(taxa, 0, 9);

                        TextField valorTaxa = new TextField();
                        grid.add(valorTaxa, 1, 9);

                        cadastroModelo.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                actiontarget2.setFill(Color.FIREBRICK);
                                List<Modelo> internacional = modeloRepository.findAll();
                                if (nome.getText().trim().isEmpty() || valorModelo.getText().trim().isEmpty())
                                    actiontarget2.setText("Por favor preencha todos os campos");


                                if(internacional.stream().anyMatch(modelo-> modelo.getNome().equals(nomeModelo.getText()))) actiontarget2.setText("Modelo ja existente");
                                else {
                                    Categoria categoria = categ.getValue();
                                    Marca marca = marcaModelo.getValue();
                                    Modelo modelo = new ModeloInternacional(nomeModelo.getText(), Double.parseDouble(valorModelo.getText()),
                                            categoria.getCodigo(), marca.getCodigo(), Double.parseDouble(valorTaxa.getText()));
                                    modeloRepository.save(modelo);
                                    actiontarget2.setText("Cadastro concluido");
                                }
                            }
                        });
                    }
                });

                cadastroModelo.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        actiontarget2.setFill(Color.FIREBRICK);
                        actiontarget2.setText("Por favor preencha todos os campos");
                    }
                });

                Button menu2 = new Button("MENU");
                HBox hbmenu2 = new HBox(10);
                hbmenu2.setAlignment(Pos.BOTTOM_RIGHT);
                hbmenu2.getChildren().add(menu2);
                grid.add(hbmenu2, 0, 20);
                menu2.setOnAction(actEven -> {
                    Principal principal = new Principal();
                    try {
                        principal.start(menuCadastro);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                Scene scene3 = new Scene(grid);
                menuCadastro.setScene(scene3);
                menuCadastro.show();
                break;

            case 5:

                Text title4 = new Text("CADASTRAR NOVO AUTOMÓVEL");
                title4.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                title4.setTextAlignment(TextAlignment.CENTER);
                grid.add(title4, 0, 0);

                Label placa = new Label("Placa: ");
                grid.add(placa, 0, 1);

                TextField placaAuto = new TextField();
                grid.add(placaAuto, 1, 1);

                Label ano = new Label("Ano: ");
                grid.add(ano, 0, 2);

                TextField anoAuto = new TextField();
                grid.add(anoAuto, 1, 2);

                Label diaria = new Label("Diária: ");
                grid.add(diaria, 0, 3);

                TextField diariaAuto = new TextField();
                grid.add(diariaAuto, 1, 3);

                Label m = new Label("Modelo: ");
                grid.add(m, 0, 4);

                final ComboBox<Modelo> modeloAuto = new ComboBox<>();
                modeloAuto.getItems().addAll(modeloRepository.findAll());
                grid.add(modeloAuto, 1, 4);
                modeloAuto.setValue(modeloRepository.findOne(0));


                Button cadastroAuto = new Button("CADASTRAR AUTOMÓVEL");
                HBox button8 = new HBox(10);
                button8.setAlignment(Pos.BOTTOM_RIGHT);
                button8.getChildren().add(cadastroAuto);
                grid.add(cadastroAuto, 0, 5);

                final Text actiontarget4 = new Text();
                grid.add(actiontarget4, 1, 7);
                actiontarget4.setId("actiontarget");

                cadastroAuto.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        actiontarget4.setFill(Color.FIREBRICK);
                        Automovel auto = automovelRepository.findOne(placaAuto.getText());
                        if (placaAuto.getText().trim().isEmpty() || anoAuto.getText().trim().isEmpty() || diariaAuto.getText().trim().isEmpty())
                            actiontarget4.setText("Por favor preencha todos os campos");
                        if(auto != null) actiontarget4.setText("Modelo ja existente");
                        else {
                            Modelo modelo = modeloAuto.getValue();
                            Automovel automovel = new Automovel(placaAuto.getText(), Integer.parseInt(anoAuto.getText()), Double.parseDouble(diariaAuto.getText()), modelo.getCodigo());
                            automovelRepository.save(automovel);
                            actiontarget4.setText("Cadastro concluido");

                        }
                    }
                });

                Button menu3 = new Button("MENU");
                HBox hbmenu3 = new HBox(10);
                hbmenu3.setAlignment(Pos.BOTTOM_RIGHT);
                hbmenu3.getChildren().add(menu3);
                grid.add(hbmenu3, 0, 20);
                menu3.setOnAction(actEven -> {
                    Principal principal = new Principal();
                    try {
                        principal.start(menuCadastro);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                Scene scene4 = new Scene(grid);
                menuCadastro.setScene(scene4);
                menuCadastro.show();
                break;
        }
    }
}
