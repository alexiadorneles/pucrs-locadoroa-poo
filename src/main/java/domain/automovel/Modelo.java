package domain.automovel;

import repository.CategoriaRepository;
import repository.IDGenerator;
import repository.MarcaRepository;

import java.util.Random;

public abstract class Modelo {
    private Integer codigo;
    private final String nome;
    private final Integer codCategoria;
    private final Integer codMarca;
    private final double valor;

    public abstract double calcularValorAdicao();

    public abstract double getModificador();

    protected Modelo(String nome, double valor, Integer categoria, Integer marca) {
        this.nome = nome;
        this.valor = valor;
        this.codCategoria = categoria;
        this.codMarca = marca;
        this.codigo = IDGenerator.getInstance().getIdFor("Modelo");
    }

    protected Modelo(Integer codigo, String nome, double valor, Integer categoria, Integer marca) {
        this.codigo = codigo;
        IDGenerator.getInstance().registerTopFor("Modelo", codigo);
        this.nome = nome;
        this.valor = valor;
        this.codCategoria = categoria;
        this.codMarca = marca;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return CategoriaRepository.getInstance().findOne(this.codCategoria);
    }

    public Marca getMarca() {
        return MarcaRepository.getInstance().findOne(this.codMarca);
    }

    public Integer getCodigo() {
        return codigo;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return nome;
    }
}
