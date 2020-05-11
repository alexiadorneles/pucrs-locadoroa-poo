package repository;

import domain.cliente.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class ClienteRepository implements Repository<Cliente> {
    private final List<Cliente> clientes;
    private static ClienteRepository instance = null;

    private ClienteRepository() {
        this.clientes = new ArrayList<>();
    }

    public static ClienteRepository getInstance() {
        if (instance == null)
            instance = new ClienteRepository();

        return instance;
    }

    @Override
    public void save(Cliente clientes) {
        this.clientes.add(clientes);
    }

    @Override
    public Cliente findOne(String nome) {
        return this.clientes.stream().filter(cliente -> cliente.getNome().equals(nome)).findFirst().orElse(null);
    }

    @Override
    public List<Cliente> filter(Predicate<? super Cliente> fn) {
        return this.clientes.stream().filter(fn).collect(toList());
    }

    @Override
    public List<Cliente> findAll() {
        return this.clientes;
    }

    @Override
    public String toString() {
        String listaCLientes="";
        for (Cliente cliente : clientes){
            listaCLientes+= cliente.toString()+'\'';
        }
        return listaCLientes;
    }
}
