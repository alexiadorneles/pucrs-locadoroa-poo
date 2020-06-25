package reader;

import builder.Factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class TxtReader {

    public void read(String fileName, List<Factory<?>> factories) {
        Path path = Paths.get("resources/" + fileName + ".txt");
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("[")) {
                    String type = linha;
                    linha = reader.readLine();
                    while (linha != null && !linha.startsWith("//") && !linha.startsWith("[")) {
                        Optional<Factory<?>> factory = factories.stream().filter(b -> b.verify(type)).findAny();
                        String finalLinha = linha;
                        factory.ifPresent(factory1 -> factory1.create(finalLinha));
                        linha = reader.readLine();
                    }
                }
            }
        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }
    }
}
