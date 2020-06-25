package domain.automovel;

public class Categoria {
    private Integer codigo;
    private final String nome;

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(Integer codigo, String nome) {
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
