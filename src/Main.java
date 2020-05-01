import automovel.*;

public class Main {
    public static void main(String[] args) {
        Categoria categoria = new Categoria("SUV Médio");
        Marca marca = new Marca("Jeep");
        Modelo compass = new ModeloNacional("Compass", 120000, categoria, marca, 25);
        Modelo cheroke = new ModeloInternacional("Cheroke", 250000, categoria, marca, 75);
        Automovel automovel = new Automovel("AAA-1A11", 2020, 100, true, compass);
        Automovel automovelDois = new Automovel("BBB-B2BB", 2020, 200, true, cheroke);
        System.out.println(automovel.calcularValorFixo());
        System.out.println(automovelDois.calcularValorFixo());
    }
}
