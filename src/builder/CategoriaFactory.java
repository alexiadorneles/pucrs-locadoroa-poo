package builder;

import domain.automovel.Categoria;
import repository.CategoriaRepository;

import static domain.Constants.PONTO_E_VIRGULA;

public class CategoriaFactory implements Factory<Categoria> {
    @Override
    public void create(String line) {
        CategoriaRepository.getInstance().save(this.build(line));
    }

    @Override
    public Categoria build(String line) {
        String[] props = line.split(PONTO_E_VIRGULA);
        return new Categoria(Integer.valueOf(props[0]), props[1]);
    }

    @Override
    public boolean verify(String type) {
        return type.contains("CATEGORIA");
    }
}
