package menu;

import domain.automovel.Automovel;
import domain.automovel.Categoria;
import domain.cliente.Cliente;
import domain.cliente.PessoaFisica;
import domain.cliente.PessoaJuridica;
import domain.locacao.Locacao;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import repository.AutomovelRepository;
import repository.CategoriaRepository;
import repository.ClienteRepository;
import repository.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

public class ConsultaMenu extends Application {
    public int button = 0;
    private final Repository<Automovel, String> automovelRepository;
    private final Repository<Locacao, Integer> locacaoRepository;

    public ConsultaMenu(Repository<Automovel, String> automovelRepository, Repository<Locacao, Integer> locacaoRepository) {
        this.automovelRepository = automovelRepository;
        this.locacaoRepository = locacaoRepository;
    }

    public void setButton(int i) {
        if (i > 0 && i < 15) button = i;
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

        switch (button) {
            case 2:
                Text title = new Text("CONSULTA DISPONIBILIDADE DE AUTOMOVEL POR CATEGORIA");
                title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                title.setTextAlignment(TextAlignment.CENTER);
                grid.add(title, 0, 0);

                Label categoria = new Label("Escolha a Categoria: ");
                grid.add(categoria, 0, 3);

                final ComboBox<Categoria> categoriaComboBox = new ComboBox<>();
                categoriaComboBox.getItems().addAll(CategoriaRepository.getInstance().findAll());
                grid.add(categoriaComboBox, 1, 3);
                categoriaComboBox.setValue(CategoriaRepository.getInstance().findOne(0));

                Button verificar = new Button("VERIFICAR");
                HBox button1 = new HBox(10);
                button1.setAlignment(Pos.BOTTOM_LEFT);
                button1.getChildren().add(verificar);
                grid.add(button1, 1, 2);

                Text actiontarget = new Text();
                grid.add(actiontarget, 1, 6);
                actiontarget.setId("actiontarget");


                verificar.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        actiontarget.setFill(Color.FIREBRICK);
                        if (categoria.getText().isEmpty()) actiontarget.setText("Por favor preencha todos os campos");
                        else {
                            Categoria chosenCategoria = categoriaComboBox.getValue();
                            List<Automovel> autoDisponiveisCategoria = automovelRepository.filter(auto -> getAutomovelByCategoriaAndDisponivel(chosenCategoria, auto));
                            if (autoDisponiveisCategoria.isEmpty()) {
                                grid.getChildren().removeIf(child -> child.getId() != null && child.getId().equals("id"));
                                actiontarget.setText("Não há automoveis dessa categoria");
                            }

                            Text categoriaDisponivel = new Text("Automoveis disponiveis");
                            categoriaDisponivel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                            categoriaDisponivel.setTextAlignment(TextAlignment.CENTER);
                            grid.add(categoriaDisponivel, 0, 5);

                            int count = 6;
                            for (Automovel automovel : autoDisponiveisCategoria) {
                                Text txt = new Text(automovel.toString());
                                txt.setId("id");
                                txt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
                                grid.add(txt, 0, count++);
                            }
                        }
                    }
                });
                Button menu = new Button("MENU");
                HBox hmenu = new HBox(10);
                hmenu.setAlignment(Pos.BOTTOM_RIGHT);
                hmenu.getChildren().add(menu);
                grid.add(hmenu, 0, 20);
                menu.setOnAction(actEven -> {
                    Principal principal = new Principal();
                    try {
                        principal.start(menuConsulta);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                Scene scene = new Scene(grid);
                menuConsulta.setScene(scene);
                menuConsulta.show();

                break;
            case 3:
                Text title2 = new Text("CONSULTAR O VALOR DE UMA LOCAÇÃO");
                title2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                title2.setTextAlignment(TextAlignment.CENTER);
                grid.add(title2, 0, 0);

                Label categoriaLabel = new Label("Categoria: ");
                grid.add(categoriaLabel, 0, 5);

                ComboBox<Categoria> categoriaComboBox1 = new ComboBox<>();
                categoriaComboBox1.getItems().addAll(CategoriaRepository.getInstance().findAll());
                grid.add(categoriaComboBox1, 1, 5);

                Button selecionarCategoria = new Button("Selecionar Categoria");
                grid.add(selecionarCategoria, 2, 5);

                actiontarget = new Text();
                grid.add(actiontarget, 0, 12);
                actiontarget.setId("action");

                Button consult = new Button("VERIFICAR");
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
                            grid.getChildren().removeIf(child -> child.getId() != null && child.getId().equals("id"));
                        } else {
                            actiontarget.setText("");
                            Label autoLabel = new Label("Automóveis Disponíveis");
                            autoLabel.setId("id");
                            grid.add(autoLabel, 0, 6);
                            ComboBox<Automovel> automovelComboBox = new ComboBox<>();
                            automovelComboBox.getItems().addAll(fromThisCategoria);
                            automovelComboBox.setId("id");
                            grid.add(automovelComboBox, 1, 6);

                            Label dataInicial = new Label("Data inicial (DD/MM/AAA) ");
                            grid.add(dataInicial, 0, 7);
                            dataInicial.setId("id");

                            TextField dataInicialInput = new TextField();
                            grid.add(dataInicialInput, 1, 7);
                            dataInicialInput.setId("id");

                            Label dataFinal = new Label("Data final (DD/MM/AAA): ");
                            grid.add(dataFinal, 0, 8);
                            dataFinal.setId("id");

                            TextField dataFinalInput = new TextField();
                            grid.add(dataFinalInput, 1, 8);
                            dataFinalInput.setId("id");

                            Label tipoClienteLabel = new Label("Selecione o tipo de cliente");
                            tipoClienteLabel.setId("id");
                            grid.add(tipoClienteLabel, 0, 9);

                            ComboBox<String> tipoClienteCombobox = new ComboBox<>();
                            tipoClienteCombobox.setId("id");
                            tipoClienteCombobox.getItems().addAll(Arrays.asList("PF", "PJ"));
                            grid.add(tipoClienteCombobox, 1, 9);

                            consult.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    if (dataFinalInput.getText().isEmpty() || dataInicialInput.getText().isEmpty()
                                            || automovelComboBox.getValue() == null || tipoClienteCombobox.getValue() == null
                                    ) {
                                        actiontarget.setText("Por favor preencha os dados");
                                    } else {
                                        actiontarget.setText("");
                                        String tipoCliente = tipoClienteCombobox.getValue();
                                        Cliente cliente = null;
                                        if (tipoCliente.equals("PF")) cliente = new PessoaFisica("", "", "MOCK");
                                        if (tipoCliente.equals("PJ")) cliente = new PessoaJuridica("", "", "MOCK");
                                        ClienteRepository.getInstance().save(cliente);
                                        Locacao locacao = new Locacao(cliente.getCPFCNPJ(), dataInicialInput.getText(), dataFinalInput.getText(), automovelComboBox.getValue().getPlaca());
                                        try {
                                            double valorLocacao = locacao.calcularValorLocacao();
                                            ClienteRepository.getInstance().remove(cliente.getCPFCNPJ());
                                            grid.getChildren().removeIf(child -> child.getId() != null && child.getId().equals("valorLocacao"));
                                            Text text = new Text("O valor da locação é: " + valorLocacao);
                                            text.setId("valorLocacao");
                                            grid.add(text, 1, 10);
                                        } catch (Exception e) {
                                            actiontarget.setText("Dados inválidos. Por favor, corrija os dados e tente novamente.");
                                        }
                                    }
                                }
                            });
                        }
                    }
                });

                HBox button2 = new HBox(10);
                button2.setAlignment(Pos.BOTTOM_LEFT);
                button2.getChildren().add(consult);
                grid.add(button2, 1, 12);

                actiontarget.setId("action");

                consult.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        actiontarget.setFill(Color.FIREBRICK);
                        actiontarget.setText("Por favor preencha os dados");
                    }
                });

                break;
            case 4:
                Text title3 = new Text("CONSULTAR LOCACOES");
                title3.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                title3.setTextAlignment(TextAlignment.CENTER);
                grid.add(title3, 0, 0);

                Label text1 = new Label("Locações disponiveis");
                grid.add(text1, 0, 1);

                Text action1 = new Text();
                action1.setId("action");
                action1.setFill(Color.FIREBRICK);
                grid.add(action1, 0, 6);

                List<Locacao> locacoes = locacaoRepository.filter(locacao -> !locacao.isFinalizada());
                if (locacoes.isEmpty()) {
                    action1.setText("Nenhuma locação disponivel");
                } else {
                    this.showInGrid(grid, locacoes, 6);
                }
                break;
            case 5:
                Text title4 = new Text("CONSULTAR CLIENTES");
                title4.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                title4.setTextAlignment(TextAlignment.CENTER);
                grid.add(title4, 0, 0);

                Label text3 = new Label("Clientes disponiveis");
                grid.add(text3, 0, 1);

                Text action2 = new Text();
                action2.setId("action");
                action2.setFill(Color.FIREBRICK);
                grid.add(action2, 0, 6);

                List<Cliente> clientes = ClienteRepository.getInstance().findAll();
                if (clientes.isEmpty()) {
                    action2.setText("Nenhum cliente disponivel");
                } else {
                    this.showInGrid(grid, clientes, 6);
                }
                break;

            case 6:
                Text title5 = new Text("CONSULTAR AUTOMOVEIS");
                title5.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                title5.setTextAlignment(TextAlignment.CENTER);
                grid.add(title5, 0, 0);

                Label text4 = new Label("Automoveis cadastrados");
                grid.add(text4, 0, 1);

                Text action3 = new Text();
                action3.setId("action");
                action3.setFill(Color.FIREBRICK);
                grid.add(action3, 0, 6);

                List<Automovel> automoveis = this.automovelRepository.findAll();
                if (automoveis.isEmpty()) {
                    action3.setText("Nenhum automovel cadastrado");
                } else {
                    this.showInGrid(grid, automoveis, 6);
                }
                menuConsulta.setScene(new Scene(grid));
                menuConsulta.show();
                break;
        }

        Button menu1 = new Button("MENU");
        HBox hmenu1 = new HBox(10);
        hmenu1.setAlignment(Pos.BOTTOM_RIGHT);
        hmenu1.getChildren().add(menu1);
        grid.add(hmenu1, 0, 20);
        menu1.setOnAction(actEven -> {
            Principal principal = new Principal();
            try {
                principal.start(menuConsulta);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Scene scene2 = new Scene(grid);
        menuConsulta.setScene(scene2);
        menuConsulta.show();
    }

    private <T> void showInGrid(GridPane grid, List<T> objects, int init) {
        AtomicInteger count = new AtomicInteger(init);
        objects.forEach(str -> {
            Text loc = new Text();
            loc.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
            loc.setTextAlignment(TextAlignment.CENTER);
            loc.setText(str.toString());
            grid.add(loc, 0, count.getAndIncrement());
        });
    }

    public boolean consultaDisponibilidadeCategoria(String codigo) {
        Categoria categoria = CategoriaRepository.getInstance().findOne(Integer.valueOf(codigo));
        List<Automovel> autoDisponiveisCategoria = this.automovelRepository.filter(auto -> this.getAutomovelByCategoriaAndDisponivel(categoria, auto));
        if (autoDisponiveisCategoria.isEmpty()) {
            return false;
        }
        autoDisponiveisCategoria.forEach(System.out::println);
        return true;
    }

    private boolean getAutomovelByCategoriaAndDisponivel(Categoria categoria, Automovel automovel) {
        return automovel.getModelo().getCategoria().equals(categoria) && automovel.isDisponivel();
    }

}
