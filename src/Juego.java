import java.util.Scanner;

public class  Juego {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Menu opciones = new Menu();

        while (true) {
            System.out.println("\n=======================================");
            System.out.printf("%26s", "PIXEL INVADER");
            System.out.println("\n=======================================\n");
            System.out.printf("%11s%26s", "JUGAR [1]", "PERSONALIZAR PARTIDA [2]");
            System.out.println("\n\n=======================================\n");

            System.out.print("Seleccione una opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Intente nuevamente.");
                scanner.next();
                continue;
            }

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    opciones.iniciarPartida();
                    break;

                case 2:
                    opciones.configuracionPartida();
                    break;

                default:
                    System.out.println("OPCIÓN INVALIDA. INTENTELO DE NUEVO");

            }
        }
    }
}
