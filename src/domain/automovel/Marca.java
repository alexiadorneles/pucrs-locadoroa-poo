package domain.automovel;

public class Marca {
    private Integer codigo;
    private final String nome;

    public Marca(String nome) {
        this.nome = nome;
    }

    public Marca(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return nome;
    }
}
