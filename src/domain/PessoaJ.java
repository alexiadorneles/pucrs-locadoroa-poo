package domain;

public class PessoaJ extends Cliente {
    private String CNPJ;

    public PessoaJ(String nome, String telefone, String cnpj){
        super(nome, telefone);
        this.CNPJ = cnpj;
    }

    public String getCNPJ() {
        return CNPJ;
    }
}
