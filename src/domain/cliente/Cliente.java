package domain.cliente;

public class Cliente {
    private String nome;
    private String telefone;

    public Cliente(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public double aplicarDesconto(double valor) {
        return valor;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

}
