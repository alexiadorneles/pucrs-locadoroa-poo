package domain.automovel;

import repository.IDGenerator;

import java.util.Random;

public class Marca {
    private Integer codigo;
    private final String nome;

    public Marca(String nome) {
        this.nome = nome;
        this.codigo = IDGenerator.getInstance().getIdFor(this.getClass().getName());
    }

    public Marca(Integer codigo, String nome) {
        this.codigo = codigo;
        IDGenerator.getInstance().registerTopFor(this.getClass().getName(), codigo);
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
