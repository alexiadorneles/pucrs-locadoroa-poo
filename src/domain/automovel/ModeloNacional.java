package domain.automovel;

public class ModeloNacional extends Modelo {
    private final double ipi;

    public ModeloNacional(String nome, double valor, Categoria categoria, Marca marca, double ipi) {
        super(nome, valor, categoria, marca);
        this.ipi = ipi;
    }

    @Override
    public double calcularValorAdicao() {
        return this.getValor() * ((this.ipi) / 100.0);
    }


    @Override
    public String toString() {
        return "  IPI: " + ipi + '\n'
                + "  Modelo: " + this.getNome() + '\n'
                + "  Valor: " + this.getValor() + '\n'
                + "  Categoria: " + this.getCategoria()
                ;
    }
}
