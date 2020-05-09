package domain.automovel;

public class ModeloInternacional extends Modelo {
    private double taxaImportacao;

    public ModeloInternacional(String nome, double valor, Categoria categoria, Marca marca, double taxaImportacao) {
        super(nome, valor, categoria, marca);
        this.taxaImportacao = taxaImportacao;
    }

    @Override
    public double calcularValorModelo() {
        return this.getValor() * (1 + (this.taxaImportacao / 100));
    }
}
