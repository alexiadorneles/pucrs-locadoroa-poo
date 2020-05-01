package automovel;

public class ModeloNacional extends Modelo {
    private double ipi;

    public ModeloNacional(String nome, double valor, Categoria categoria, Marca marca, double ipi) {
        super(nome, valor, categoria, marca);
        this.ipi = ipi;
    }

    @Override
    public double calcularValorModelo() {
        return this.getValor() * (1 + (this.ipi / 100));
    }
}
