package domain.cliente;

public class PessoaFisica extends Cliente {
    private String CFP;

    public PessoaFisica(String nome, String telefone, String cpf){
        super(nome, telefone);
        this.CFP = cpf;
    }

    public String getCFP() {
        return CFP;
    }
}
