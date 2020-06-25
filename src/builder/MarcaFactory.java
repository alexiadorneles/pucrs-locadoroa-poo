package builder;

import domain.automovel.Marca;
import repository.MarcaRepository;

import static domain.Constants.PONTO_E_VIRGULA;

public class MarcaFactory implements Factory<Marca> {
    @Override
    public void create(String line) {
        MarcaRepository.getInstance().save(this.build(line));
    }

    @Override
    public Marca build(String line) {
        String[] props = line.split(PONTO_E_VIRGULA);
        return new Marca(Integer.valueOf(props[0]), props[1]);
    }

    @Override
    public boolean verify(String type) {
        return type.contains("MARCA");
    }
}
