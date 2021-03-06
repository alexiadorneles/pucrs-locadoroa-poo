package repository;

import domain.cliente.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ClienteRepository implements Repository<Cliente, String> {
    private List<Cliente> clientes;
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
    public Cliente findOne(String id) {
        return this.clientes.stream().filter(cliente -> cliente.getCPFCNPJ().equals(id)).findFirst().orElse(null);
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
    public void clear() {
        this.clientes.clear();
    }

    @Override
    public String toString() {
        String listaCLientes = "";
        for (Cliente cliente : clientes) {
            listaCLientes += cliente.toString() + '\n';
        }
        return listaCLientes;
    }

    public void remove(String cpfcnpj) {
        this.clientes = this.clientes.stream()
                .filter(cli -> !cli.getCPFCNPJ().equals(cpfcnpj))
                .collect(Collectors.toList());
    }
}
