import automovel.Automovel;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Locacao {
    private Cliente cliente;
    private String dataInicial;
    private String dataFinal;
    private Automovel auto;

    public Locacao(Cliente cliente, String dataInicial, String dataFinal, Automovel auto) {
        this.cliente = cliente;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.auto = auto;
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

    public long calculaDiasDiaria() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate inicial = LocalDate.parse(getDataInicial(), formatter);
        LocalDate dataFinal = LocalDate.parse(getDataFinal(), formatter);
        Period periodo = Period.between(inicial, dataFinal);
        return ChronoUnit.DAYS.between(inicial, dataFinal);
    }

}
