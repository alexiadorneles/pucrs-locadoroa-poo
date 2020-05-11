package domain.locacao;

import domain.automovel.*;
import domain.cliente.Cliente;
import domain.cliente.PessoaFisica;
import domain.locacao.Locacao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocacaoTest {
    @Test


    public void calcularValorLocacao(){
        Cliente cliente = new PessoaFisica("Pedro", "0900", "029307");
        Modelo modelo = new ModeloInternacional("Artic", 250000,new Categoria("Change"),
                new Marca("Snow"),75);
        Automovel automovel = new Automovel("jdola", 2019, 100, true, modelo);
        Locacao locar = new Locacao(cliente,"02/01/2020","04/01/2020",automovel);
        double resultado = locar.calcularValorLocacao();

        assertEquals(resultado, 700);

    }

}
