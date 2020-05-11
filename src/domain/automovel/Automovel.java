package domain.automovel;

public class Automovel {
    private String placa;
    private int ano;
    private double valorDiaria;
    private boolean disponivel;
    private Modelo modelo;

    public double calcularValorFixo() {
        return this.modelo.calcularValorModelo();
    }

    public Automovel(String placa, int ano, double valorDiaria, boolean disponivel, Modelo modelo) {
        this.placa = placa;
        this.ano = ano;
        this.valorDiaria = valorDiaria;
        this.disponivel = disponivel;
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public int getAno() {
        return ano;

    }

    protected double getValorDiaria() {
        return valorDiaria;
    }

    protected boolean isDisponivel() {
        return disponivel;
    }

    public Modelo getModelo() {
        return modelo;
    }
}
