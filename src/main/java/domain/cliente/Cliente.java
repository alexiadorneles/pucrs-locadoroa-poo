package domain.cliente;

public abstract class Cliente {
    private String nome;
    private String telefone;

    public Cliente(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public Cliente() {
    }

    public abstract String getCPFCNPJ();

    public double aplicarDesconto(double valor) {
        return valor;
    }

    public String getNome() {
        return nome;
    }


    @Override
    public String toString() {
        return "Cliente: " + nome;
    }
}
