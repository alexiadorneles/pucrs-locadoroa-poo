package domain.automovel;

public abstract class Modelo {
    private Integer codigo;
    private final String nome;
    private final Categoria categoria;
    private final Marca marca;
    private final double valor;

    public abstract double calcularValorAdicao();

    public abstract double getModificador();

    protected Modelo(String nome, double valor, Categoria categoria, Marca marca) {
        this.nome = nome;
        this.valor = valor;
        this.categoria = categoria;
        this.marca = marca;
    }

    protected Modelo(Integer codigo, String nome, double valor, Categoria categoria, Marca marca) {
        this.codigo = codigo;
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

    public Marca getMarca() {
        return marca;
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
