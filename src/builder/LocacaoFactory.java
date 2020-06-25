package builder;

import domain.automovel.Categoria;
import domain.cliente.Cliente;
import domain.locacao.Locacao;
import repository.CategoriaRepository;
import repository.ClienteRepository;
import repository.LocacaoRepository;

import static domain.Constants.PONTO_E_VIRGULA;

public class LocacaoFactory implements Factory<Locacao> {
    @Override
    public void create(String line) {
        LocacaoRepository.getInstance().save(this.build(line));
    }

    @Override
    public Locacao build(String line) {
        String[] props = line.split(PONTO_E_VIRGULA);
        Integer codigo = Integer.parseInt(props[0]);
        String codCliente = props[1];
        Integer codCategoria = Integer.parseInt(props[2]);
        Categoria categoria = CategoriaRepository.getInstance().findOne(codCategoria);
        Cliente cliente = ClienteRepository.getInstance().findOne(codCliente);
        return new Locacao(codigo, cliente, props[3], props[4], categoria);
    }

    @Override
    public boolean verify(String type) {
        return type.contains("PEDIDO");
    }
}
