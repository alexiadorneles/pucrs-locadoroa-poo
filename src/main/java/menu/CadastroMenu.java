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
import javafx.scene.text.*;
import javafx.stage.Stage;

import domain.automovel.*;
import domain.cliente.Cliente;
import domain.cliente.PessoaFisica;
import domain.cliente.PessoaJuridica;
import repository.*;

import java.util.Scanner;

public class CadastroMenu extends Application{

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
                                if(nomeCliente.getText().trim().isEmpty() || telefoneCliente.getText().isEmpty() ||
                                        cpfCliente.getText().isEmpty()) actiontarget.setText("Por favor preencha todos os campos");
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
                                if(nomeCliente.getText().trim().isEmpty() || telefoneCliente.getText().isEmpty() ||
                                        cnpjCliente.getText().isEmpty()) actiontarget.setText("Por favor preencha todos os campos");
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


                Scene scene = new Scene(grid);
                menuCadastro.setScene(scene);
                menuCadastro.show();
                break;
            case 2:

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
    }

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