package domain.automovel;

public class ModeloInternacional extends Modelo {
    private final double taxaImportacao;

    public ModeloInternacional(String nome, double valor, Categoria categoria, Marca marca, double taxaImportacao) {
        super(nome, valor, categoria, marca);
        this.taxaImportacao = taxaImportacao;
    }

    @Override
    public double calcularValorModelo() {
        return this.getValor() * (1 + (this.taxaImportacao / 100));
    }

    @Override
    public String toString() {
        return "Internacional: " +
                "   taxaImportacao=" + taxaImportacao
                + "   nome=" + this.getNome()
                + "   valor=" + this.getValor()
                + "   categoria=" + this.getCategoria()
                ;
    }
}