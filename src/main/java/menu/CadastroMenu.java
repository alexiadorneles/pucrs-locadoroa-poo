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

import javax.security.auth.callback.Callback;
import java.util.Scanner;

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


    public void setButton(int i){
        if(i>0 && i<15) button=i;
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

        switch (button){
            case 1:
                Text title = new Text("CADASTRAR CLIENTE");
                title.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                title.setTextAlignment(TextAlignment.CENTER);
                grid.add(title,0,0);

                Text text = new Text("ESCOLHA O TIPO DE CLIENTE");

                Button pf = new Button("PESSOA FISICA");
                HBox button1 = new HBox(10);
                button1.setAlignment(Pos.BOTTOM_LEFT);
                button1.getChildren().add(pf);
                grid.add(button1,0,1);
                pf.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        GridPane pf = new GridPane();
                        pf.setAlignment(Pos.CENTER);
                        pf.setHgap(10);
                        pf.setVgap(10);
                        pf.setPadding(new Insets(100, 100, 100, 100));

                        Text title = new Text("DADOS DO CLIENTE");
                        title.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                        title.setTextAlignment(TextAlignment.CENTER);
                        pf.add(title,0,0);

                        Label nome = new Label("Nome: ");
                        pf.add(nome,0,1);

                        TextField nomeCliente = new TextField();
                        pf.add(nomeCliente,1,1);

                        Label telefone = new Label("Telefone: ");
                        pf.add(telefone,0,2);

                        TextField telefoneCliente = new TextField();
                        pf.add(telefoneCliente,1,2);

                        Label cpf = new Label("CPF: ");
                        pf.add(cpf,0,3);

                        TextField cpfCliente = new TextField();
                        pf.add(cpfCliente,1,3);

                        Button confirmarCadastro = new Button("CADASTRAR");
                        HBox btn = new HBox(10);
                        btn.setAlignment(Pos.BOTTOM_RIGHT);
                        btn.getChildren().add(confirmarCadastro);
                        pf.add(btn,1,5);

                        final Text actiontarget = new Text();
                        pf.add(actiontarget, 1, 6);
                        actiontarget.setId("actiontarget");

                        confirmarCadastro.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                actiontarget.setFill(Color.FIREBRICK);
                                Cliente c = clienteRepository.findOne(cpfCliente.getText());

                                if(nomeCliente.getText().trim().isEmpty() || telefoneCliente.getText().isEmpty() ||
                                        cpfCliente.getText().isEmpty()) actiontarget.setText("Por favor preencha todos os campos");
                                if(c!=null)actiontarget.setText("Usuário ja existente");
                                else {
                                    actiontarget.setText("Cadastro concluido");
                                    Cliente cliente = new PessoaFisica(nomeCliente.getText(), telefoneCliente.getText(), cpfCliente.getText());
                                    clienteRepository.save(cliente);
                                    System.out.println(cliente.toString());
                                }
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
                grid.add(button2,1,1);
                pj.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        GridPane pj = new GridPane();
                        pj.setAlignment(Pos.CENTER);
                        pj.setHgap(10);
                        pj.setVgap(10);
                        pj.setPadding(new Insets(100, 100, 100, 100));

                        Text title = new Text("DADOS DO CLIENTE");
                        title.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                        title.setTextAlignment(TextAlignment.CENTER);
                        pj.add(title,0,0);

                        Label nome = new Label("Nome: ");
                        pj.add(nome,0,1);

                        TextField nomeCliente = new TextField();
                        pj.add(nomeCliente,1,1);

                        Label telefone = new Label("Telefone: ");
                        pj.add(telefone,0,2);

                        TextField telefoneCliente = new TextField();
                        pj.add(telefoneCliente,1,2);

                        Label cnpj = new Label("CNPJ: ");
                        pj.add(cnpj,0,3);

                        TextField cnpjCliente = new TextField();
                        pj.add(cnpjCliente,1,3);

                        Button confirmarCadastro = new Button("CADASTRAR");
                        HBox btn = new HBox(10);
                        btn.setAlignment(Pos.BOTTOM_RIGHT);
                        btn.getChildren().add(confirmarCadastro);
                        pj.add(btn,1,5);

                        final Text actiontarget = new Text();
                        pj.add(actiontarget, 1, 6);
                        actiontarget.setId("actiontarget");

                        confirmarCadastro.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                actiontarget.setFill(Color.FIREBRICK);
                                Cliente c = clienteRepository.findOne(cnpjCliente.getText());
                                if(nomeCliente.getText().trim().isEmpty() || telefoneCliente.getText().isEmpty() ||
                                        cnpjCliente.getText().isEmpty()) actiontarget.setText("Por favor preencha todos os campos com dados validos");
                                if( c!=null) actiontarget.setText("Usuário ja existente");
                                else {
                                    actiontarget.setText("Cadastro concluido");
                                    Cliente cliente = new PessoaJuridica(nomeCliente.getText(), telefoneCliente.getText(), cnpjCliente.getText());
                                    clienteRepository.save(cliente);
                                    System.out.println(cliente.toString());
                                }
                            }
                        });
                        menuCadastro.setScene(new Scene(pj));
                        menuCadastro.show();
                    }
                });
                break;

                case 2:
                    Scene scene1 = new Scene(grid);
                    menuCadastro.setScene(scene1);
                    menuCadastro.show();

                    Text title1 = new Text("CADASTRAR NOVA CATEGORIA");
                    title1.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                    title1.setTextAlignment(TextAlignment.CENTER);
                    grid.add(title1,0,0);

                    Label nomeCat = new Label("Nome da Categoria: ");
                    grid.add(nomeCat,0,1);

                    TextField cat = new TextField();
                    grid.add(cat,1,1);

                    Button cadastroCat = new Button("CADASTRAR CATEGORIA");
                    HBox button3 = new HBox(10);
                    button3.setAlignment(Pos.BOTTOM_LEFT);
                    button3.getChildren().add(cadastroCat);
                    grid.add(button3,0,2);

                    final Text actiontarget = new Text();
                    grid.add(actiontarget, 1, 6);
                    actiontarget.setId("actiontarget");

                    cadastroCat.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            actiontarget.setFill(Color.FIREBRICK);
                            if(cat.getText().trim().isEmpty()) actiontarget.setText("Por favor preencha todos os campos");
                            else {
                                actiontarget.setText("Cadastro concluido");
                                Categoria categoria = new Categoria(cat.getText());
                                categoriaRepository.save(categoria);
                                System.out.println(categoria.toString());
                            }
                        }
                    });
                break;

            case 3:

                Scene scene2 = new Scene(grid);
                menuCadastro.setScene(scene2);
                menuCadastro.show();

                Text title2 = new Text("CADASTRAR NOVA MARCA");
                title2.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                title2.setTextAlignment(TextAlignment.CENTER);
                grid.add(title2,0,0);

                Label nomeMarca = new Label("Nome da Marca: ");
                grid.add(nomeMarca,0,1);

                TextField marca = new TextField();
                grid.add(marca,1,1);

                Button cadastroMarca = new Button("CADASTRAR MARCA");
                HBox button4 = new HBox(10);
                button4.setAlignment(Pos.BOTTOM_LEFT);
                button4.getChildren().add(cadastroMarca);
                grid.add(button4,0,2);

                final Text actiontarget1 = new Text();
                grid.add(actiontarget1, 1, 6);
                actiontarget1.setId("actiontarget");

                cadastroMarca.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        actiontarget1.setFill(Color.FIREBRICK);
                        if(marca.getText().trim().isEmpty()) actiontarget1.setText("Por favor preencha todos os campos");
                        else {
                            actiontarget1.setText("Cadastro concluido");
                            Marca m = new Marca(marca.getText());
                            marcaRepository.save(m);
                            System.out.println(marca.toString());
                        }
                    }
                });
                break;

            case 4:
                Scene scene3 = new Scene(grid);
                menuCadastro.setScene(scene3);
                menuCadastro.show();

                Text title3 = new Text("CADASTRAR NOVA MODELO");
                title3.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                title3.setTextAlignment(TextAlignment.CENTER);
                grid.add(title3,0,0);

                Label nome = new Label("Nome do Modelo: ");
                grid.add(nome,0,1);

                TextField nomeModelo = new TextField();
                grid.add(nomeModelo,1,1);

                Label valor = new Label("Valor do Modelo: ");
                grid.add(valor,0,2);

                TextField valorModelo = new TextField();
                grid.add(valorModelo,1,2);

                Label categoria = new Label("Escolha a Categoria: ");
                grid.add(categoria,0,3);

                final ComboBox categ = new ComboBox();
                categ.getItems().addAll(categoriaRepository.findAll());
                grid.add(categ, 1,3);

                Label model = new Label("Escolha o Modelo: ");
                grid.add(model,0,4);

                final ComboBox modelo = new ComboBox();
                modelo.getItems().addAll(modeloRepository.findAll());
                grid.add(modelo, 1,4);

                Button cadastroModelo = new Button("CADASTRAR MODELO");
                HBox button7 = new HBox(10);
                button7.setAlignment(Pos.BOTTOM_LEFT);
                button7.getChildren().add(cadastroModelo);
                grid.add(cadastroModelo,0,10);

                final Text actiontarget2 = new Text();
                grid.add(actiontarget2, 1, 7);
                actiontarget2.setId("actiontarget");

                Label tipoModelo = new Label("Escolha o Tipo de Modelo: ");
                grid.add(tipoModelo,0,5);

                Button nacional = new Button("Nacional");
                HBox button5 = new HBox(10);
                button5.setAlignment(Pos.BOTTOM_LEFT);
                button5.getChildren().add(nacional);
                grid.add(nacional,0,6);
                nacional.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Label ipi = new Label("Valor do ipi: ");
                        grid.add(ipi,0,7);

                        TextField valorIpi = new TextField();
                        grid.add(valorIpi,1,7);
                        cadastroModelo.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                actiontarget2.setFill(Color.FIREBRICK);
                                if(nome.getText().trim().isEmpty() || valorModelo.getText().trim().isEmpty()) actiontarget2.setText("Por favor preencha todos os campos");
                                else {
                                    actiontarget2.setText("Cadastro concluido");
                                   //Modelo novoModelo = new Modelo();
                                    //marcaRepository.save(m);
                                    //System.out.println(marca.toString());
                                }
                            }
                        });

                    }
                });

                Button internacional = new Button("Internacional");
                HBox button6 = new HBox(10);
                button6.setAlignment(Pos.BOTTOM_RIGHT);
                button6.getChildren().add(internacional);
                grid.add(internacional,0,8);

                internacional.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Label taxa = new Label("Valor da Taxa de Importanção: ");
                        grid.add(taxa,0,9);

                        TextField valorTaxa = new TextField();
                        grid.add(valorTaxa,1,9);
                    }
                });

                cadastroModelo.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        actiontarget2.setFill(Color.FIREBRICK);
                        if(nome.getText().trim().isEmpty() || valorModelo.getText().trim().isEmpty()) actiontarget2.setText("Por favor preencha todos os campos");
                        else {
                            actiontarget2.setText("Cadastro concluido");
                           // Marca m = new Marca(nomeModelo.getText(), valorModelo.getText(), categ.getValue(),modelo.getValue());
                            //marcaRepository.save(m);
                            //System.out.println(marca.toString());
                        }
                    }
                });
                break;


        }
    }
//
//
//    public void cadastrarCliente(Scanner in) {
//        int escolha;
//        do {
//            System.out.println("Escolha o tipo de cliente: ");
//            System.out.println("1- Pessoa fisica ");
//            System.out.println("2- Pessoa juridica");
//            escolha = in.nextInt();
//        } while (escolha > 2 || escolha < 1);
//
//        Cliente cliente;
//        System.out.println("Digite os dados do cliente: ");
//        in.nextLine();
//        System.out.println("Nome: ");
//        String nome = in.nextLine();
//        System.out.println("Telefone: ");
//        String telefone = in.nextLine();
//
//        if (escolha == 1) {
//            System.out.println("CPF: ");
//            String cpf = in.nextLine();
//            cliente = new PessoaFisica(nome, telefone, cpf);
//        } else {
//            System.out.println("CNPJ: ");
//            String cnpj = in.nextLine();
//            cliente = new PessoaJuridica(nome, telefone, cnpj);
//        }
//        this.clienteRepository.save(cliente);
//        System.out.println("Cadastro concluído.");
//    }


    /*public void cadastrarAutomovel(Scanner in) {
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
                Integer.valueOf(nomeModelo)
        );
        this.automovelRepository.save(automovel);
    }



    public void cadastrarCategoria(String in) {
        System.out.println("Digite o nome da categoria: ");
        this.categoriaRepository.save(new Categoria(in));
    }

    public void cadastrarMarca(Scanner in) {
        System.out.println("Digite o nome da marca: ");
        this.marcaRepository.save(new Marca(in.nextLine()));
    }*/

    public void cadastrarModelo(Scanner in) {
        System.out.println("Digite o nome do modelo: ");
        String nome = in.nextLine();
        System.out.println("Digite o valor: ");
        double valor = in.nextDouble();
        in.nextLine();

        System.out.println("Escolha a categoria");
        Categoria categoria = this.categoriaRepository.findOne(Integer.valueOf(in.nextLine()));

        System.out.println("Escolha a marca");
        Marca marca = this.marcaRepository.findOne(Integer.valueOf(in.nextLine()));


        System.out.println("Escolha o tipo de modelo: \n 1 - Nacional \t 2 - Internacional");
        int tipoModelo = in.nextInt();
        if (tipoModelo == 1) {
            System.out.println("Digite a porcentagem de ipi");
            Modelo modelo = new ModeloNacional(nome, valor, categoria.getCodigo(), marca.getCodigo(), in.nextDouble());
            this.modeloRepository.save(modelo);
        } else {
            System.out.println("Digite a porcentagem de taxa de importação");
            Modelo modelo = new ModeloInternacional(nome, valor, categoria.getCodigo(), marca.getCodigo(), in.nextDouble());
            this.modeloRepository.save(modelo);
        }
    }
}
