package factory;

import domain.automovel.Automovel;
import domain.automovel.Modelo;
import repository.AutomovelRepository;
import repository.ModeloRepository;

import static domain.Constants.PONTO_E_VIRGULA;

public class AutomovelFactory extends BaseFactory<Automovel, String> {

    public AutomovelFactory() {
        super(AutomovelRepository.getInstance());
    }

    @Override
    public Automovel build(String line) {
        String[] props = line.split(PONTO_E_VIRGULA);
        Modelo modelo = ModeloRepository.getInstance().findOne(Integer.valueOf(props[1]));
        return new Automovel(props[0], Integer.parseInt(props[2]), Double.parseDouble(props[3]), modelo);
    }

    @Override
    public boolean verify(String type) {
        return type.contains("AUTOMOVEL");
    }
}
