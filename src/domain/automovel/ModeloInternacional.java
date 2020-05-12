package domain.automovel;

public class ModeloInternacional extends Modelo {
    private final double taxaImportacao;

    public ModeloInternacional(String nome, double valor, Categoria categoria, Marca marca, double taxaImportacao) {
        super(nome, valor, categoria, marca);
        this.taxaImportacao = taxaImportacao;
    }

    @Override
    public double calcularValorAdicao() {
        return this.getValor() * ((this.taxaImportacao) / 100.0);
    }

    @Override
    public double getModificador() {
        return 0.25;
    }

    @Override
    public String toString() {
        return "Taxa de importacao: " + taxaImportacao + '\n'
                + "   Modelo: " + this.getNome() + '\n'
                + "   Valor: " + this.getValor() + '\n'
                + "   Categoria: " + this.getCategoria()
                ;
    }
}
