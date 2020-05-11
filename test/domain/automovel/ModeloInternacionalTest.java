package domain.automovel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModeloInternacionalTest {

    @Test
    void calcularValorModelo() {
        // arrange
        Modelo modelo = new ModeloInternacional(
                "Cherokee", 250000,
                new Categoria("SUV Médio"), new Marca("Jeep"), 75
        );

        // act
        final double resultado = modelo.calcularValorModelo();

        // assert
        assertEquals(resultado, 437500);
    }
}