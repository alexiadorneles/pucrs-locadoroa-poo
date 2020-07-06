package repository;

import java.util.Arrays;
import java.util.List;

public class Helper {
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
