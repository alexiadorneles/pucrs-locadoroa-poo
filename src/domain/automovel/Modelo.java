package domain.automovel;

public abstract class Modelo {
    private final String nome;
    private final Categoria categoria;
    private final Marca marca;
    private final double valor;

    public abstract double calcularValorModelo();

    protected Modelo(String nome, double valor, Categoria categoria, Marca marca) {
        this.nome = nome;
        this.valor = valor;
        this.categoria = categoria;
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    private Marca getMarca() {
        return marca;
    }

    public double getValor() {
        return valor;
    }

}
