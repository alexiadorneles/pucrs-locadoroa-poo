package domain.automovel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModeloInternacionalTest {

    @Test
    void calcularValorAdicao() {
        // arrange
        Modelo modelo = new ModeloInternacional(
                "Cherokee", 250000,
                new Categoria("SUV Médio"), new Marca("Jeep"), 75
        );

        // act
        final double resultado = modelo.calcularValorAdicao();

        // assert
        assertEquals(resultado, 187500.0);
    }
}