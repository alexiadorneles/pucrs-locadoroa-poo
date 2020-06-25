package domain.cliente;

public class PessoaFisica extends Cliente {
    private final String cpf;

    public PessoaFisica(String nome, String telefone, String cpf) {
        super(nome, telefone);
        this.cpf = cpf;
    }

    @Override
    public String getCPFCNPJ() {
        return this.cpf;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
