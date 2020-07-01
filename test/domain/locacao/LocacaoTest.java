package domain.locacao;

import domain.TestHelper;
import domain.automovel.*;
import domain.cliente.Cliente;
import domain.cliente.PessoaFisica;
import domain.cliente.PessoaJuridica;
import domain.locacao.Locacao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocacaoTest {

    @BeforeEach
    public void setup() {
        TestHelper.getAllRepositories().forEach(Repository::clear);
    }

    @Test
    public void calcularValorLocacao() {
        Cliente cliente = new PessoaFisica("Pedro", "0900", "029307");
        ClienteRepository.getInstance().save(cliente);

        Categoria categoria = new Categoria(1, "Change");
        CategoriaRepository.getInstance().save(categoria);

        Marca marca = new Marca(1, "Snow");
        MarcaRepository.getInstance().save(marca);

        Modelo modelo = new ModeloInternacional(1, "Artic", 250000, 1,
                1, 75);
        ModeloRepository.getInstance().save(modelo);

        Automovel automovel = new Automovel("jdola", 2019, 100, 1);
        AutomovelRepository.getInstance().save(automovel);

        Locacao locar = new Locacao(cliente.getCPFCNPJ(), "02/01/2020", "04/01/2020", automovel.getPlaca());
        double resultado = locar.calcularValorLocacao();

        assertEquals(496.875, resultado);
    }

    @Test
    public void calcularValorLocacaoDeveRetornar201() {
        Cliente cliente = new PessoaFisica("Maria", "0900", "029307");
        ClienteRepository.getInstance().save(cliente);

        Categoria categoria = new Categoria(1, "Qualquer");
        CategoriaRepository.getInstance().save(categoria);

        Marca marca = new Marca(1, "Honda");
        MarcaRepository.getInstance().save(marca);

        Modelo modelo = new ModeloNacional(1, "Civic", 100000, 1,
                1, 10);
        ModeloRepository.getInstance().save(modelo);

        Automovel automovel = new Automovel("AAA-2A22", 2019, 10, modelo.getCodigo());
        AutomovelRepository.getInstance().save(automovel);

        Locacao locar = new Locacao(cliente.getCPFCNPJ(), "02/01/2020", "12/01/2020", automovel.getPlaca());
        double resultado = locar.calcularValorLocacao();

        assertEquals(201, resultado);
    }

    @Test
    public void calcularValorLocacaoDeveRetornar192() {
        Cliente cliente = new PessoaJuridica("HP", "0900", "029307");
        ClienteRepository.getInstance().save(cliente);

        Categoria categoria = new Categoria(1, "Sedan");
        CategoriaRepository.getInstance().save(categoria);

        Marca marca = new Marca(1, "Mercedes");
        MarcaRepository.getInstance().save(marca);


        Modelo modelo = new ModeloInternacional(1, "Classe A", 100000, 1,
                1, 10);
        ModeloRepository.getInstance().save(modelo);

        Automovel automovel = new Automovel("BBB-5B55", 2019, 10, modelo.getCodigo());
        AutomovelRepository.getInstance().save(automovel);

        Locacao locar = new Locacao(cliente.getCPFCNPJ(), "02/01/2020", "12/01/2020", automovel.getPlaca());
        double resultado = locar.calcularValorLocacao();

        assertEquals(197.5, resultado);
    }

    @Test
    public void calcularValorLocacaoDeveRetornar623() {
        Categoria categoria = new Categoria(1, "SUV Médio");
        CategoriaRepository.getInstance().save(categoria);

        Marca marca = new Marca(1, "Jeep");
        MarcaRepository.getInstance().save(marca);

        Modelo modelo = new ModeloNacional(1, "Compass", 120000, 1, 1, 25);
        ModeloRepository.getInstance().save(modelo);

        Automovel automovel = new Automovel("AAA-1A11", 2020, 100, modelo.getCodigo());
        AutomovelRepository.getInstance().save(automovel);

        Cliente pessoaFisica = new PessoaFisica("Pedro", "123", "123");
        ClienteRepository.getInstance().save(pessoaFisica);

        Locacao locacao = new Locacao(pessoaFisica.getCPFCNPJ(), "10/05/2020", "15/05/2020", automovel.getPlaca());
        double resultado = locacao.calcularValorLocacao();

        assertEquals(623, resultado);
    }

    @Test
    public void calcularValorLocacaoDeveRetornar2196() {
        Categoria categoria = new Categoria(1, "SUV Médio");
        CategoriaRepository.getInstance().save(categoria);

        Marca marca = new Marca(1, "Jeep");
        MarcaRepository.getInstance().save(marca);

        Modelo modelo = new ModeloInternacional(1, "Cherokee", 250000, 1, 1, 75);
        ModeloRepository.getInstance().save(modelo);

        Automovel automovel = new Automovel("BBB-2B22", 2020, 200, 1);
        AutomovelRepository.getInstance().save(automovel);

        Cliente pessoaJuridica = new PessoaJuridica("ACME", "123", "123");
        ClienteRepository.getInstance().save(pessoaJuridica);

        Locacao locacao = new Locacao(pessoaJuridica.getCPFCNPJ(), "10/05/2020", "20/05/2020", automovel.getPlaca());
        double resultado = locacao.calcularValorLocacao();

        assertEquals(2196.88, resultado, 0.1);
    }
}
