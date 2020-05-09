package domain.automovel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModeloNacionalTest {

    @Test
    void calcularValorModelo() {
        // arrange
        Modelo modelo = new ModeloNacional(
                "Compass", 120000,
                new Categoria("SUV Médio"), new Marca("Jeep"), 25
        );

        // act
        final double resultado = modelo.calcularValorModelo();

        // assert
        assertEquals(resultado, 150000);
    }
}