package domain.automovel;

import org.junit.jupiter.api.Test;
import repository.CategoriaRepository;
import repository.MarcaRepository;

import static org.junit.jupiter.api.Assertions.*;

class ModeloNacionalTest {

    @Test
    void calcularValorAdicao() {
        // arrange
        Categoria categoria = new Categoria(1, "Qualquer");
        CategoriaRepository.getInstance().save(categoria);

        Marca marca = new Marca(1, "Honda");
        MarcaRepository.getInstance().save(marca);

        Modelo modelo = new ModeloNacional("Civic", 100000, 1,
                1, 10);

        // act
        final double resultado = modelo.calcularValorAdicao();

        // assert
        assertEquals(resultado, 10000.0);
    }
}