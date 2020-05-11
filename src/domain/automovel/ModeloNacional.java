package domain.automovel;

public class ModeloNacional extends Modelo {
    private final double ipi;

    public ModeloNacional(String nome, double valor, Categoria categoria, Marca marca, double ipi) {
        super(nome, valor, categoria, marca);
        this.ipi = ipi;
    }

    @Override
    public double calcularValorModelo() {
        return (this.getValor() * (1 + ((this.ipi+10) / 100)))/1000;
    }


    @Override
    public String toString() {
        return  "  IPI: " + ipi+ '\''
                + "  Modelo: " + this.getNome()+ '\''
                + "  Valor: " + this.getValor()+ '\''
                + "  Categoria: " + this.getCategoria()
                ;
    }
}
