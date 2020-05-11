package domain.automovel;

import java.time.LocalDate;

public class Automovel {
    private static final int LIMITE_ANOS_FROTA = 5;
    private final String placa;
    private final int ano;
    private final double valorDiaria;
    private boolean disponivel;
    private final Modelo modelo;

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

    public boolean isVelhoDemaisParaAFrota() {
        return LocalDate.now().getYear() - this.ano >= LIMITE_ANOS_FROTA;
    }

    public String getPlaca() {
        return placa;
    }

    protected int getAno() {
        return ano;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Placa: " + placa + '\n' +
                "Ano: " + ano + '\n' +
                "Valor diaria: " + valorDiaria + '\n'+
                "Disponibilidade: " + disponivel + '\n'+
                "Modelo: " + modelo;
    }
}
