package factory;

import domain.automovel.*;
import repository.CategoriaRepository;
import repository.MarcaRepository;
import repository.ModeloRepository;

import static domain.Constants.PONTO_E_VIRGULA;

public class ModeloFactory extends BaseFactory<Modelo, Integer> {

    public ModeloFactory() {
        super(ModeloRepository.getInstance());
    }

    @Override
    public Modelo build(String line) {
        String[] props = line.split(PONTO_E_VIRGULA);
        String tipo = props[5];
        Categoria categoria = CategoriaRepository.getInstance().findOne(Integer.valueOf(props[3]));
        Marca marca = MarcaRepository.getInstance().findOne(Integer.valueOf(props[4]));
        Integer codigo = Integer.valueOf(props[0]);
        String nome = props[1];
        double valor = Double.parseDouble(props[2]);
        double ipiOuTaxa = Double.parseDouble(props[6]);

        if (tipo.equals("N")) {
            return new ModeloNacional(codigo, nome, valor, categoria, marca, ipiOuTaxa);
        }

        return new ModeloInternacional(codigo, nome, valor, categoria, marca, ipiOuTaxa);
    }

    @Override
    public boolean verify(String type) {
        return type.contains("MODELO");
    }
}
