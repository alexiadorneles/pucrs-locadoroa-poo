package domain.cliente;

public class PessoaJuridica extends Cliente {
    private static final int PORCENTAGEM_DESCONTO_PJ = 5;
    private final String cnpj;

    public PessoaJuridica(String nome, String telefone, String cnpj) {
        super(nome, telefone);
        this.cnpj = cnpj;
    }

    @Override
    public String getCPFCNPJ() {
        return this.cnpj;
    }

    @Override
    public double aplicarDesconto(double valor) {
        return valor * (1 - (PORCENTAGEM_DESCONTO_PJ / 100.0));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
