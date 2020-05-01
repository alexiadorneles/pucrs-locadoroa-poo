import java.util.Scanner;

public class Menu {
    public Menu () {}
    public void mostrar(){
        Scanner in = new Scanner(System.in);

        System.out.println("////////// LOCADORA AJE //////////");

        System.out.println("1 - Atendendente");
        System.out.println("2 - Gerente");
        int opcao1 = in.nextInt();

        if(opcao1 == 1){
            System.out.println("1 - Cadastrar nomo Cliente");
            System.out.println("2 - Consultar Disponibilidade de Automóvel por Categoria");
            System.out.println("3 - Consultar o Valor de uma Locaçao ");
            System.out.println("4 - Realizar Locação");
            System.out.println("5 - Finalizar Locação ");
        }else{
            System.out.println("1 - Cadastrar Nova Categoria Automóvel");
            System.out.println("2 - Cadastrar Nova Marca do Automóvel");
            System.out.println("3 - Cadastrar Novo Modelo do Automóvel");
            System.out.println("4 - Cadastrar Novo Automóvel");
            System.out.println("5 - Consultar Locações");
            System.out.println("6 - Consultar Clientes");
            System.out.println("7 - Consultar Automóveis Cadastrados");
        }
        int opcao2 = in.nextInt();


        if(opcao1 == 1){
            switch(opcao2) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }else{
            switch (opcao2) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
            }
        }

    }
}
