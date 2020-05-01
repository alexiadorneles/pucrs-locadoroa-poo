public class PessoaF  extends Cliente {
    private String CFP;

    public PessoaF (String nome, String telefone, String cpf){
        super(nome, telefone);
        this.CFP = cpf;
    }

    public String getCFP() {
        return CFP;
    }
}
