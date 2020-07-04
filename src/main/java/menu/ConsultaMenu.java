package menu;
import domain.cliente.PessoaFisica;
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

import domain.automovel.Automovel;
import domain.automovel.Categoria;
import domain.cliente.Cliente;
import domain.locacao.Locacao;
import repository.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ConsultaMenu extends Application{
    public int button=0;
    private final Repository<Automovel, String> automovelRepository;
    private final Repository<Locacao, Integer> locacaoRepository;

    public ConsultaMenu(Repository<Automovel, String> automovelRepository, Repository<Locacao, Integer> locacaoRepository) {
        this.automovelRepository = automovelRepository;
        this.locacaoRepository = locacaoRepository;
    }

    public void setButton(int i){
        if(i>0 && i<15) button=i;
        else button = 0;
    }

    @Override
    public void start(Stage menuConsulta) throws Exception {
        menuConsulta.setTitle("---------- LOCADORA AJE ----------");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(100, 100, 100, 100));

        switch (button){
            case 2:
                Text title = new Text("CONSULTA DISPONIBILIDADE DE AUTOMOVEL POR CATEGORIA");
                title.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                title.setTextAlignment(TextAlignment.CENTER);
                grid.add(title,0,0);

                Label text = new Label("Nome: ");
                grid.add(text,0,1);

                TextField categoria = new TextField();
                grid.add(categoria,1,1);


                Button verificar = new Button("VERIFICAR");
                HBox button1 = new HBox(10);
                button1.setAlignment(Pos.BOTTOM_LEFT);
                button1.getChildren().add(verificar);
                grid.add(button1,0,1);

                final Text actiontarget = new Text();
                grid.add(actiontarget, 1, 6);
                actiontarget.setId("actiontarget");


                verificar.setOnAction(new EventHandler<ActionEvent>()  {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        actiontarget.setFill(Color.FIREBRICK);
                        if (categoria.getText().isEmpty()) actiontarget.setText("Por favor preencha todos os campos");
                        else {
                            Categoria categoria1 = CategoriaRepository.getInstance().findOne(Integer.valueOf(categoria.getText()));
                            List<Automovel> autoDisponiveisCategoria = automovelRepository.filter(auto -> getAutomovelByCategoriaAndDisponivel(categoria1, auto));
                            if (autoDisponiveisCategoria.isEmpty()) {
                                actiontarget.setText("Não há automoveis dessa categoria");

                            }

                            Text categoriaDisponivel = new Text("Automoveis disponiveis");
                            categoriaDisponivel.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                            categoriaDisponivel.setTextAlignment(TextAlignment.CENTER);
                            grid.add(categoriaDisponivel,0,5);
                            autoDisponiveisCategoria.forEach(str -> categoriaDisponivel.setText(str.toString()));
                            autoDisponiveisCategoria.forEach(System.out::println);

                        }
                    }
                }) ;
                Scene scene = new Scene(grid);
                menuConsulta.setScene(scene);
                menuConsulta.show();

                break;
        }
    }

    public boolean consultaDisponibilidadeCategoria(Scanner in){
//        System.out.println("Digite a categoria: ");
        Categoria categoria = CategoriaRepository.getInstance().findOne(Integer.valueOf(in.nextLine()));
        List<Automovel> autoDisponiveisCategoria = this.automovelRepository.filter(auto -> this.getAutomovelByCategoriaAndDisponivel(categoria, auto));
        if (autoDisponiveisCategoria.isEmpty()) {
            System.out.println("Não há automoveis dessa categoria");
            return false;
        }

        autoDisponiveisCategoria.forEach(System.out::println);
        return true;
    }
    public void consultarValorLocacao(Scanner in){
        System.out.println("Essas são as locações disponíveis no sistema: ");
        this.consultarLocacoes();
        Locacao locacao;
        do {
            System.out.println("Por favor digite o código de uma locação pra consultar seu valor total: ");
            locacao = this.locacaoRepository.findOne(Integer.valueOf(in.nextLine()));
        } while (Objects.isNull(locacao));

        System.out.println("O valor da sua locação é: " + locacao.calcularValorLocacao());
    }

    public void consultarLocacoes() {
        this.locacaoRepository.findAll().forEach(System.out::println);
    }

    public void consultarAutomoveis() {
        List<Automovel> automoveis = this.automovelRepository.findAll();
        automoveis.forEach(System.out::println);
    }


    public void consultarClientesCadastrados() {
        List<Cliente> clientes = ClienteRepository.getInstance().findAll();
        clientes.forEach(cliente -> System.out.println(cliente.getNome()));
    }

    private boolean getAutomovelByCategoriaAndDisponivel(Categoria categoria, Automovel automovel) {
        return automovel.getModelo().getCategoria().equals(categoria) && automovel.isDisponivel();
    }

}
