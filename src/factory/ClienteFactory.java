package factory;

import domain.cliente.Cliente;
import domain.cliente.PessoaFisica;
import domain.cliente.PessoaJuridica;
import repository.ClienteRepository;

import static domain.Constants.PONTO_E_VIRGULA;

public class ClienteFactory extends BaseFactory<Cliente, String> {

    public ClienteFactory() {
        super(ClienteRepository.getInstance());
    }

    @Override
    public Cliente build(String line) {
        String[] props = line.split(PONTO_E_VIRGULA);
        String tipo = props[2];
        if (tipo.equals("F")) {
            return new PessoaFisica(props[1], "", props[0]);
        }
        return new PessoaJuridica(props[1], "", props[0]);
    }

    @Override
    public boolean verify(String type) {
        return type.contains("CLIENTE");
    }
}
