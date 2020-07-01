package domain.automovel;

public class ModeloNacional extends Modelo {
    private final double ipi;

    public ModeloNacional(String nome, double valor, Integer categoria, Integer marca, double ipi) {
        super(nome, valor, categoria, marca);
        this.ipi = ipi;
    }

    public ModeloNacional(Integer codigo, String nome, double valor, Integer categoria, Integer marca, double ipi) {
        super(codigo, nome, valor, categoria, marca);
        this.ipi = ipi;
    }

    public double getIpi() {
        return ipi;
    }

    @Override
    public double calcularValorAdicao() {
        return this.getValor() * ((this.ipi) / 100.0);
    }

    @Override
    public double getModificador() {
        return 0.1;
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
