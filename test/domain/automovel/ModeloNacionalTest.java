package domain.automovel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModeloNacionalTest {

    @Test
    void calcularValorAdicao() {
        // arrange
        Modelo modelo = new ModeloNacional("Civic", 100000, new Categoria("Qualquer"),
                new Marca("Honda"), 10);


        // act
        final double resultado = modelo.calcularValorAdicao();

        // assert
        assertEquals(resultado, 10000.0);
    }
}