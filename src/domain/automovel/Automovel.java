package domain.automovel;

import repository.ModeloRepository;

import java.time.LocalDate;

public class Automovel {
    private static final int LIMITE_ANOS_FROTA = 5;
    private final String placa;
    private final int ano;
    private final double valorDiaria;
    private boolean disponivel;
    private Integer codModelo;

    public double calcularValorFixo() {
        double adicao = this.getModelo().calcularValorAdicao();
        return (this.getModelo().getValor() + (adicao * this.getModelo().getModificador())) / 1000;
    }

    public Automovel(String placa, int ano, double valorDiaria, Integer modelo) {
        this.placa = placa;
        this.ano = ano;
        this.valorDiaria = valorDiaria;
        this.codModelo = modelo;
        this.disponivel = true;
    }

    public Automovel(String placa, int ano, double valorDiaria, Integer modelo, boolean isDisponivel) {
        this.placa = placa;
        this.ano = ano;
        this.valorDiaria = valorDiaria;
        this.codModelo = modelo;
        this.disponivel = isDisponivel;
    }

    public boolean isVelhoDemaisParaAFrota() {
        return LocalDate.now().getYear() - this.ano >= LIMITE_ANOS_FROTA;
    }

    public String getPlaca() {
        return placa;
    }

    public int getAno() {
        return ano;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public Modelo getModelo() {
        return ModeloRepository.getInstance().findOne(this.codModelo);
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Placa: " + placa + '\n' +
                "Ano: " + ano + '\n' +
                "Valor diaria: " + valorDiaria + '\n' +
                "Disponibilidade: " + disponivel + '\n' +
                "Modelo: " + this.getModelo().getNome();
    }
}
