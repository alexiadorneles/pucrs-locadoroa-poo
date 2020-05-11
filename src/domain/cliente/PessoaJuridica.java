package domain.cliente;

public class PessoaJuridica extends Cliente {
    private static final int PORCENTAGEM_DESCONTO_PJ = 5;
    private final String CNPJ;

    public PessoaJuridica(String nome, String telefone, String cnpj) {
        super(nome, telefone);
        this.CNPJ = cnpj;
    }

    @Override
    public double aplicarDesconto(double valor) {
        return valor * (1 - (PORCENTAGEM_DESCONTO_PJ / 100.0));
    }

    public String getCNPJ() {
        return CNPJ;
    }

    @Override
    public String toString() {
        return  super.toString();
    }
}
