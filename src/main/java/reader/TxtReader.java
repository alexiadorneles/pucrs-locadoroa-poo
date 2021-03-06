package reader;

import factory.Factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class TxtReader {
    private final List<Factory<?>> factories;

    public TxtReader(List<Factory<?>> factories) {
        this.factories = factories;
    }

    public void read(String fileName) throws IOException {
        Path path = Paths.get("resources/" + fileName + ".txt");
        BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset());
        String linha;
        while ((linha = reader.readLine()) != null) {
            if (linha.startsWith("[")) {
                String type = linha;
                System.out.println("Processando " + type.substring(1, type.length() - 1));
                linha = reader.readLine();
                while (linha != null && !linha.startsWith("//") && !linha.startsWith("[") && !linha.isEmpty()) {
                    System.out.println("Lendo linha " + linha);
                    Optional<Factory<?>> factory = this.factories.stream().filter(b -> b.verify(type)).findAny();
                    String finalLinha = linha;
                    factory.ifPresent(factory1 -> factory1.createFromTxt(finalLinha));
                    System.out.println(type.substring(1, type.length() - 1) + " criado com sucesso");
                    linha = reader.readLine();
                }
                System.out.println();
                System.out.println("---------------------------");
                System.out.println();
            }
        }
    }
}

