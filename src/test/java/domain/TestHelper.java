package domain;

import repository.*;

import java.util.Arrays;
import java.util.List;

public class TestHelper {
    public static List<Repository> getAllRepositories() {
        return Arrays.asList(
                CategoriaRepository.getInstance(),
                MarcaRepository.getInstance(),
                ModeloRepository.getInstance(),
                AutomovelRepository.getInstance(),
                ClienteRepository.getInstance(),
                LocacaoRepository.getInstance()
        );
    }
}
