package domain.automovel;

import org.junit.jupiter.api.Test;
import repository.CategoriaRepository;
import repository.MarcaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModeloInternacionalTest {

    @Test
    void calcularValorAdicao() {
        // arrange
        Categoria categoria = new Categoria(1, "SUV Médio");
        CategoriaRepository.getInstance().save(categoria);

        Marca marca = new Marca(1, "Jeep");
        MarcaRepository.getInstance().save(marca);

        Modelo modelo = new ModeloInternacional(
                "Cherokee", 250000,
                1, 1, 75
        );

        // act
        final double resultado = modelo.calcularValorAdicao();

        // assert
        assertEquals(resultado, 187500.0);
    }
}