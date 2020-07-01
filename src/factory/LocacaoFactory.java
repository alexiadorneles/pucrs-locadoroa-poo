package factory;

import domain.automovel.Categoria;
import domain.cliente.Cliente;
import domain.locacao.Locacao;
import repository.CategoriaRepository;
import repository.ClienteRepository;
import repository.LocacaoRepository;

import static domain.Constants.PONTO_E_VIRGULA;

public class LocacaoFactory extends BaseFactory<Locacao, Integer> {

    public LocacaoFactory() {
        super(LocacaoRepository.getInstance());
    }

    @Override
    public Locacao build(String line) {
        String[] props = line.split(PONTO_E_VIRGULA);
        Integer codigo = Integer.parseInt(props[0]);
        String codCliente = props[1];
        Integer codCategoria = Integer.parseInt(props[2]);
        return new Locacao(codigo, codCliente, props[3], props[4], codCategoria);
    }

    @Override
    public boolean verify(String type) {
        return type.contains("PEDIDO");
    }
}
