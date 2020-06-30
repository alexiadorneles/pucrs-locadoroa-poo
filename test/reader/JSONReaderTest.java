package reader;

import domain.automovel.Categoria;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class JSONReaderTest {

    @Test
    void readAndWrite() throws IOException {
        // arrange
        Integer random = new Random().nextInt();
        String randomStr = new Random().toString();
        Categoria categoria = new Categoria(random, randomStr);
        JSONReader reader = new JSONReader();

        // act
        reader.write("resources/unittest.json", categoria);
        Categoria result = reader.read("resources/unittest.json", Categoria.class);

        // assert
        assertEquals(random, result.getCodigo());
        assertEquals(randomStr, result.getNome());
    }
}