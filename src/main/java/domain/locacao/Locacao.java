package domain.locacao;

import domain.automovel.Automovel;
import domain.automovel.Categoria;
import domain.cliente.Cliente;
import repository.AutomovelRepository;
import repository.CategoriaRepository;
import repository.ClienteRepository;
import repository.IDGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;


public class Locacao {
    private final Integer codigo;
    private final String clienteId;
    private Integer categoriaId;
    private final String dataInicial;
    private final String dataFinal;
    private String autoPlaca;
    private boolean finalizada;

    public Locacao(String cliente, String dataInicial, String dataFinal, String auto) {
        this.codigo = IDGenerator.getInstance().getIdFor(this.getClass().getName());
        this.clienteId = cliente;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.autoPlaca = auto;
    }

    public Locacao(Integer codigo, String cliente, String dataInicial, String dataFinal, Integer categoria) {
        this.codigo = codigo;
        IDGenerator.getInstance().registerTopFor(this.getClass().getName(), codigo);
        this.clienteId = cliente;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.categoriaId = categoria;
    }

    public double calcularValorLocacao() {
        double valorDiaria = this.getCliente().aplicarDesconto(this.getAuto().getValorDiaria());
        return this.getAuto().calcularValorFixo() + (this.calcularNumeroDeDiarias() * valorDiaria);
    }

    public void finalizar() {
        this.finalizada = true;
        this.getAuto().setDisponivel(true);
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return ClienteRepository.getInstance().findOne(this.clienteId);
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public Automovel getAuto() {
        return AutomovelRepository.getInstance().findOne(this.autoPlaca);
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
                " Cliente" + clienteId +
                " Data inicio da locação: " + dataInicial + '\'' +
                " Data final da locação" + dataFinal + '\'' +
                " Automovel: " + autoPlaca + '\'' +
                " Status: " + finalizada;
    }

    public Categoria getCategoria() {
        return CategoriaRepository.getInstance().findOne(this.categoriaId);
    }
}
