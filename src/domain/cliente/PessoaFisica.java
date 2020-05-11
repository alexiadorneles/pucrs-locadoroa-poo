package domain.cliente;

public class PessoaFisica extends Cliente {
    private final String CFP;

    public PessoaFisica(String nome, String telefone, String cpf){
        super(nome, telefone);
        this.CFP = cpf;
    }

    public String getCFP() {
        return CFP;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
