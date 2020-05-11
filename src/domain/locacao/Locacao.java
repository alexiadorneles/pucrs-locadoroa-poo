package domain.locacao;

import domain.automovel.Automovel;
import domain.cliente.Cliente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;


public class Locacao {
    private final String codigo;
    private final Cliente cliente;
    private final String dataInicial;
    private final String dataFinal;
    private final Automovel auto;
    private boolean finalizada;

    public Locacao(Cliente cliente, String dataInicial, String dataFinal, Automovel auto) {
        this.codigo = String.valueOf(new Random().nextInt());
        this.cliente = cliente;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.auto = auto;
        this.auto.setDisponivel(false);
    }

    public double calcularValorLocacao() {
        double valorDiaria = this.cliente.aplicarDesconto(this.auto.getValorDiaria());
        return this.auto.calcularValorFixo() + (this.calcularNumeroDeDiarias() * valorDiaria);
    }

    public void finalizar() {
        this.finalizada = true;
        this.auto.setDisponivel(true);
    }

    public String getCodigo() {
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
        return "Locacao{" +
                "codigo='" + codigo + '\'' +
                ", cliente=" + cliente +
                ", dataInicial='" + dataInicial + '\'' +
                ", dataFinal='" + dataFinal + '\'' +
                ", auto=" + auto +
                ", finalizada=" + finalizada +
                '}';
    }
}