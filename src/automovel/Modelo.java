package automovel;

public abstract class Modelo {
    private String nome;
    private Categoria categoria;
    private Marca marca;
    private double valor;

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

    private Categoria getCategoria() {
        return categoria;
    }

    private Marca getMarca() {
        return marca;
    }

    public double getValor() {
        return valor;
    }


}
