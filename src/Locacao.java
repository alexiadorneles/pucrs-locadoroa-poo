import automovel.Automovel;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;


public class Locacao {
    private Cliente cliente;
    private String dataInicial;
    private String dataFinal;
    private Automovel auto;

    public Locacao(Cliente cliente, String dataInicial, String dataFinal, Automovel auto){
        this.cliente = cliente;
        this.dataInicial = new StringBuilder(dataInicial).reverse().toString();
        this.dataFinal = new StringBuilder(dataFinal).reverse().toString();
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

    public int calculaDiasDiaria(){
        LocalDate inicial = getDataInicial();
        LocalDate final = getDataFinal()
        Period periodo = Period.between(inicial,final);
        int dias = periodo.getDays();
        return dias;
    }

}
