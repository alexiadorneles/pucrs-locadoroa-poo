package reader;

import factory.*;
import domain.automovel.*;
import domain.cliente.Cliente;
import domain.cliente.PessoaFisica;
import domain.cliente.PessoaJuridica;
import domain.locacao.Locacao;
import org.junit.jupiter.api.Test;
import repository.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TxtReaderTest {

    @Test
    void read() {
        // arrange
        TxtReader reader = new TxtReader(Arrays.asList(
                new AutomovelFactory(),
                new CategoriaFactory(),
                new ClienteFactory(),
                new LocacaoFactory(),
                new MarcaFactory(),
                new ModeloFactory()
        ));

        // act
        reader.read("carga");

        // assert
        checkCategoriasWereCreated();
        checkMarcasWasCreated();
        checkModelosWereCreated();
        checkAutomoveisWereCreated();
        checkClientesWereCreated();
        checkLocacoesWereCreated();
    }

    private void checkLocacoesWereCreated() {
        List<Locacao> locacoes = LocacaoRepository.getInstance().findAll();
        Optional<Locacao> loc1 = locacoes.stream().filter(l -> l.getCodigo().equals(1)).findAny();
        Optional<Locacao> loc2 = locacoes.stream().filter(l -> l.getCodigo().equals(2)).findAny();
        Optional<Locacao> loc3 = locacoes.stream().filter(l -> l.getCodigo().equals(3)).findAny();
        Optional<Locacao> loc4 = locacoes.stream().filter(l -> l.getCodigo().equals(4)).findAny();

        assertTrue(loc1.isPresent());
        Cliente cliente = ClienteRepository.getInstance().findOne("355.014.791-09");
        Categoria categoria = CategoriaRepository.getInstance().findOne(1);
        assertEquals(cliente, loc1.get().getCliente());
        assertEquals(categoria, loc1.get().getCategoria());
        assertEquals("10/10/2020", loc1.get().getDataInicial());
        assertEquals("14/10/2020", loc1.get().getDataFinal());

        assertTrue(loc2.isPresent());
        cliente = ClienteRepository.getInstance().findOne("88.630.413/0002-81");
        categoria = CategoriaRepository.getInstance().findOne(1);
        assertEquals(cliente, loc2.get().getCliente());
        assertEquals(categoria, loc2.get().getCategoria());
        assertEquals("05/09/2020", loc2.get().getDataInicial());
        assertEquals("14/09/2020", loc2.get().getDataFinal());

        assertTrue(loc3.isPresent());
        cliente = ClienteRepository.getInstance().findOne("61.797.924/0007-40");
        categoria = CategoriaRepository.getInstance().findOne(1);
        assertEquals(cliente, loc3.get().getCliente());
        assertEquals(categoria, loc3.get().getCategoria());
        assertEquals("01/10/2020", loc3.get().getDataInicial());
        assertEquals("09/10/2020", loc3.get().getDataFinal());

        assertTrue(loc4.isPresent());
        cliente = ClienteRepository.getInstance().findOne("336.212.777-06");
        categoria = CategoriaRepository.getInstance().findOne(2);
        assertEquals(cliente, loc4.get().getCliente());
        assertEquals(categoria, loc4.get().getCategoria());
        assertEquals("01/10/2020", loc4.get().getDataInicial());
        assertEquals("09/10/2020", loc4.get().getDataFinal());
    }

    private void checkClientesWereCreated() {
        List<Cliente> clientes = ClienteRepository.getInstance().findAll();
        Optional<Cliente> pedro = clientes.stream().filter(c -> c.getCPFCNPJ().equals("355.014.791-09")).findAny();
        Optional<Cliente> acme = clientes.stream().filter(c -> c.getCPFCNPJ().equals("88.630.413/0002-81")).findAny();
        Optional<Cliente> maria = clientes.stream().filter(c -> c.getCPFCNPJ().equals("336.212.777-06")).findAny();
        Optional<Cliente> hp = clientes.stream().filter(c -> c.getCPFCNPJ().equals("61.797.924/0007-40")).findAny();

        assertTrue(pedro.isPresent());
        assertTrue(pedro.get() instanceof PessoaFisica);
        assertEquals("Pedro da Silva", pedro.get().getNome());

        assertTrue(acme.isPresent());
        assertTrue(acme.get() instanceof PessoaJuridica);
        assertEquals("ACME", acme.get().getNome());

        assertTrue(maria.isPresent());
        assertTrue(maria.get() instanceof PessoaFisica);
        assertEquals("Maria de Souza", maria.get().getNome());

        assertTrue(hp.isPresent());
        assertTrue(hp.get() instanceof PessoaJuridica);
        assertEquals("HP", hp.get().getNome());
    }

    private void checkAutomoveisWereCreated() {
        List<Automovel> autos = AutomovelRepository.getInstance().findAll();
        Optional<Automovel> aa = autos.stream().filter(a -> a.getPlaca().equals("AAA-1A11")).findAny();
        Optional<Automovel> bb = autos.stream().filter(a -> a.getPlaca().equals("BBB-B2BB")).findAny();
        Optional<Automovel> cc = autos.stream().filter(a -> a.getPlaca().equals("CCC-3C33")).findAny();
        Optional<Automovel> dd = autos.stream().filter(a -> a.getPlaca().equals("DDD-4D44")).findAny();

        assertTrue(aa.isPresent());
        Modelo modelo = ModeloRepository.getInstance().findOne(1);
        assertEquals(2020, aa.get().getAno());
        assertEquals(100, aa.get().getValorDiaria());
        assertEquals(modelo, aa.get().getModelo());
        assertTrue(aa.get().isDisponivel());

        assertTrue(bb.isPresent());
        modelo = ModeloRepository.getInstance().findOne(2);
        assertEquals(2020, bb.get().getAno());
        assertEquals(200, bb.get().getValorDiaria());
        assertEquals(modelo, bb.get().getModelo());
        assertTrue(bb.get().isDisponivel());

        assertTrue(cc.isPresent());
        modelo = ModeloRepository.getInstance().findOne(3);
        assertEquals(2019, cc.get().getAno());
        assertEquals(10, cc.get().getValorDiaria());
        assertEquals(modelo, cc.get().getModelo());
        assertTrue(cc.get().isDisponivel());

        assertTrue(dd.isPresent());
        modelo = ModeloRepository.getInstance().findOne(4);
        assertEquals(2019, dd.get().getAno());
        assertEquals(10, dd.get().getValorDiaria());
        assertEquals(modelo, dd.get().getModelo());
        assertTrue(dd.get().isDisponivel());
    }

    private void checkModelosWereCreated() {
        List<Modelo> modelos = ModeloRepository.getInstance().findAll();
        Optional<Modelo> compass = modelos.stream().filter(m -> m.getCodigo().equals(1) && m.getNome().equals("Compass")).findAny();
        Optional<Modelo> cherokee = modelos.stream().filter(m -> m.getCodigo().equals(2) && m.getNome().equals("Cherokee")).findAny();
        Optional<Modelo> civic = modelos.stream().filter(m -> m.getCodigo().equals(3) && m.getNome().equals("Civic")).findAny();
        Optional<Modelo> classeA = modelos.stream().filter(m -> m.getCodigo().equals(4) && m.getNome().equals("Classe A")).findAny();

        assertTrue(compass.isPresent());
        assertTrue(compass.get() instanceof ModeloNacional);
        assertEquals(120000, compass.get().getValor());
        Marca marca = MarcaRepository.getInstance().findOne(1);
        Categoria categoria = CategoriaRepository.getInstance().findOne(1);
        assertEquals(marca, compass.get().getMarca());
        assertEquals(categoria, compass.get().getCategoria());
        assertEquals(25, ((ModeloNacional) compass.get()).getIpi());

        assertTrue(cherokee.isPresent());
        assertTrue(cherokee.get() instanceof ModeloInternacional);
        assertEquals(250000, cherokee.get().getValor());
        marca = MarcaRepository.getInstance().findOne(1);
        categoria = CategoriaRepository.getInstance().findOne(1);
        assertEquals(marca, cherokee.get().getMarca());
        assertEquals(categoria, cherokee.get().getCategoria());
        assertEquals(75, ((ModeloInternacional) cherokee.get()).getTaxaImportacao());

        assertTrue(civic.isPresent());
        assertTrue(civic.get() instanceof ModeloNacional);
        assertEquals(100000, civic.get().getValor());
        marca = MarcaRepository.getInstance().findOne(2);
        categoria = CategoriaRepository.getInstance().findOne(2);
        assertEquals(marca, civic.get().getMarca());
        assertEquals(categoria, civic.get().getCategoria());
        assertEquals(10, ((ModeloNacional) civic.get()).getIpi());


        assertTrue(classeA.isPresent());
        assertTrue(classeA.get() instanceof ModeloInternacional);
        assertEquals(100000, classeA.get().getValor());
        marca = MarcaRepository.getInstance().findOne(3);
        categoria = CategoriaRepository.getInstance().findOne(2);
        assertEquals(marca, classeA.get().getMarca());
        assertEquals(categoria, classeA.get().getCategoria());
        assertEquals(10, ((ModeloInternacional) classeA.get()).getTaxaImportacao());
    }

    private void checkCategoriasWereCreated() {
        List<Categoria> categorias = CategoriaRepository.getInstance().findAll();
        Optional<Categoria> suvMedio = categorias.stream().filter(cat -> cat.getCodigo().equals(1) && cat.getNome().equals("SUV Medio")).findAny();
        Optional<Categoria> sedan = categorias.stream().filter(cat -> cat.getCodigo().equals(2) && cat.getNome().equals("Sedan")).findAny();
        assertTrue(suvMedio.isPresent());
        assertTrue(sedan.isPresent());
    }

    private void checkMarcasWasCreated() {
        List<Marca> marcas = MarcaRepository.getInstance().findAll();
        Optional<Marca> jeep = marcas.stream().filter(marca -> marca.getCodigo().equals(1) && marca.getNome().equals("Jeep")).findAny();
        Optional<Marca> honda = marcas.stream().filter(marca -> marca.getCodigo().equals(2) && marca.getNome().equals("Honda")).findAny();
        Optional<Marca> mercedes = marcas.stream().filter(marca -> marca.getCodigo().equals(3) && marca.getNome().equals("Mercedes")).findAny();
        assertTrue(jeep.isPresent());
        assertTrue(honda.isPresent());
        assertTrue(mercedes.isPresent());
    }
}