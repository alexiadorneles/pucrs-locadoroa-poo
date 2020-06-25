package domain.locacao;

import domain.automovel.Automovel;
import domain.automovel.Categoria;
import domain.cliente.Cliente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;


public class Locacao {
    private final Integer codigo;
    private final Cliente cliente;
    private Categoria categoria;
    private final String dataInicial;
    private final String dataFinal;
    private Automovel auto;
    private boolean finalizada;

    public Locacao(Cliente cliente, String dataInicial, String dataFinal, Automovel auto) {
        this.codigo = new Random().nextInt();
        this.cliente = cliente;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.auto = auto;
        this.auto.setDisponivel(false);
    }

    public Locacao(Integer codigo, Cliente cliente, String dataInicial, String dataFinal, Categoria categoria) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.categoria = categoria;
    }

    public double calcularValorLocacao() {
        double valorDiaria = this.cliente.aplicarDesconto(this.auto.getValorDiaria());
        return this.auto.calcularValorFixo() + (this.calcularNumeroDeDiarias() * valorDiaria);
    }

    public void finalizar() {
        this.finalizada = true;
        this.auto.setDisponivel(true);
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public Automovel getAuto() {
        return auto;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    private long calcularNumeroDeDiarias() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate inicial = LocalDate.parse(getDataInicial(), formatter);
        LocalDate dataFinal = LocalDate.parse(getDataFinal(), formatter);
        return ChronoUnit.DAYS.between(inicial, dataFinal);
    }

    @Override
    public String toString() {
        return "Locacao: " +
                " Codigo: " + codigo + '\'' +
                " Cliente" + cliente +
                " Data inicio da locação: " + dataInicial + '\'' +
                " Data final da locação" + dataFinal + '\'' +
                " Automovel: " + auto + '\'' +
                " Status: " + finalizada;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }
}
