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

                Label text = new Label("Codigo (números): ");
                grid.add(text,0,1);

                TextField categoria = new TextField();
                grid.add(categoria,1,1);


                Button verificar = new Button("VERIFICAR");
                HBox button1 = new HBox(10);
                button1.setAlignment(Pos.BOTTOM_LEFT);
                button1.getChildren().add(verificar);
                grid.add(button1,1,2);

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
                            Text txt = new Text();
                            txt.setFont(Font.font("Tahoma",FontWeight.NORMAL,14));
                            autoDisponiveisCategoria.forEach(str -> txt.setText(str.toString()));
                            grid.add(txt,0,6);
                        }
                    }
                }) ;
                Scene scene = new Scene(grid);
                menuConsulta.setScene(scene);
                menuConsulta.show();

                break;
            case 3:
                Text title2 = new Text("CONSULTAR O VALOR DE UMA LOCAÇÃO");
                title2.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                title2.setTextAlignment(TextAlignment.CENTER);
                grid.add(title2,0,0);

                Text locacaoDisponivel = new Text("Locações disponiveis");
                locacaoDisponivel.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                locacaoDisponivel.setTextAlignment(TextAlignment.CENTER);
                grid.add(locacaoDisponivel,0,5);
                Text locacaod = new Text("");
                locacaod.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                locacaoRepository.findAll().forEach(str -> locacaod.setText(str.toString()));
                grid.add(locacaod,0,6);

                Label text2 = new Label("Codigo: ");
                grid.add(text2,0,1);

                TextField codigo = new TextField();
                grid.add(codigo,1,1);


                Button consult = new Button("VERIFICAR");
                HBox button2 = new HBox(10);
                button2.setAlignment(Pos.BOTTOM_LEFT);
                button2.getChildren().add(consult);
                grid.add(button2,1,2);

                final Text action = new Text();
                grid.add(action, 1, 6);
                action.setId("action");


                consult.setOnAction(new EventHandler<ActionEvent>()  {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        action.setFill(Color.FIREBRICK);
                        if (codigo.getText().isEmpty()) action.setText("Por favor preencha todos os campos");
                        else {
                            Locacao locacao;
                            locacao = locacaoRepository.findOne(Integer.valueOf(codigo.getText()));
                            Text t2 = new Text("O valor da locação é " + locacao.calcularValorLocacao());
                            t2.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
                            grid.add(t2,0,2);
                        }
                    }
                }) ;
                Scene scene2 = new Scene(grid);
                menuConsulta.setScene(scene2);
                menuConsulta.show();

                break;
        }
    }
    public boolean consultaDisponibilidadeCategoria(String codigo){
        Categoria categoria = CategoriaRepository.getInstance().findOne(Integer.valueOf(codigo));
        List<Automovel> autoDisponiveisCategoria = this.automovelRepository.filter(auto -> this.getAutomovelByCategoriaAndDisponivel(categoria, auto));
        if (autoDisponiveisCategoria.isEmpty()) {
            return false;
        }
        autoDisponiveisCategoria.forEach(System.out::println);
        return true;
    }
//    public void consultarValorLocacao(Scanner in){
//        System.out.println("Essas são as locações disponíveis no sistema: ");
//        this.consultarLocacoes();
//        Locacao locacao;
//            do {
//            System.out.println("Por favor digite o código de uma locação pra consultar seu valor total: ");
//                locacao = locacaoRepository.findOne(Integer.valueOf(in.nextLine()));
//            } while (Objects.isNull(locacao));
//
//        System.out.println("O valor da sua locação é: " + locacao.calcularValorLocacao());
//    }

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
