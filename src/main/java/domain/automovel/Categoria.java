package domain.automovel;

import repository.IDGenerator;

import java.util.Random;

public class Categoria {
    private Integer codigo;
    private final String nome;

    public Categoria(String nome) {
        this.nome = nome;
        this.codigo = IDGenerator.getInstance().getIdFor(this.getClass().getName());
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
