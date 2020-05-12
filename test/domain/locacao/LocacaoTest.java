package domain.locacao;

import domain.automovel.*;
import domain.cliente.Cliente;
import domain.cliente.PessoaFisica;
import domain.cliente.PessoaJuridica;
import domain.locacao.Locacao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocacaoTest {
    @Test


    public void calcularValorLocacao() {
        Cliente cliente = new PessoaFisica("Pedro", "0900", "029307");
        Modelo modelo = new ModeloInternacional("Artic", 250000, new Categoria("Change"),
                new Marca("Snow"), 75);
        Automovel automovel = new Automovel("jdola", 2019, 100, modelo);
        Locacao locar = new Locacao(cliente, "02/01/2020", "04/01/2020", automovel);
        double resultado = locar.calcularValorLocacao();

        assertEquals(496.875, resultado);
    }

    @Test
    public void calcularValorLocacaoDeveRetornar201() {
        Cliente cliente = new PessoaFisica("Maria", "0900", "029307");
        Modelo modelo = new ModeloNacional("Civic", 100000, new Categoria("Qualquer"),
                new Marca("Honda"), 10);
        Automovel automovel = new Automovel("AAA-2A22", 2019, 10, modelo);
        Locacao locar = new Locacao(cliente, "02/01/2020", "12/01/2020", automovel);
        double resultado = locar.calcularValorLocacao();

        assertEquals(201, resultado);
    }

    @Test
    public void calcularValorLocacaoDeveRetornar192() {
        Cliente cliente = new PessoaJuridica("HP", "0900", "029307");
        Modelo modelo = new ModeloInternacional("Classe A", 100000, new Categoria("Sedan"),
                new Marca("Mercedes"), 10);
        Automovel automovel = new Automovel("BBB-5B55", 2019, 10, modelo);
        Locacao locar = new Locacao(cliente, "02/01/2020", "12/01/2020", automovel);
        double resultado = locar.calcularValorLocacao();

        assertEquals(197.5, resultado);
    }

    @Test
    public void calcularValorLocacaoDeveRetornar623() {
        Categoria categoria = new Categoria("SUV Médio");
        Marca marca = new Marca("Jeep");
        Modelo modelo = new ModeloNacional("Compass", 120000, categoria, marca, 25);
        Automovel automovel = new Automovel("AAA-1A11", 2020, 100, modelo);
        Cliente pessoaFisica = new PessoaFisica("Pedro", "123", "123");
        Locacao locacao = new Locacao(pessoaFisica, "10/05/2020", "15/05/2020", automovel);

        double resultado = locacao.calcularValorLocacao();

        assertEquals(623, resultado);
    }

    @Test
    public void calcularValorLocacaoDeveRetornar2196() {
        Categoria categoria = new Categoria("SUV Médio");
        Marca marca = new Marca("Jeep");
        Modelo modelo = new ModeloInternacional("Cherokee", 250000, categoria, marca, 75);
        Automovel automovel = new Automovel("BBB-2B22", 2020, 200, modelo);
        Cliente pessoaJuridica = new PessoaJuridica("ACME", "123", "123");
        Locacao locacao = new Locacao(pessoaJuridica, "10/05/2020", "20/05/2020", automovel);

        double resultado = locacao.calcularValorLocacao();

        assertEquals(2196.88, resultado, 0.1);
    }
}
