import java.util.Scanner;

public class Juego {

    private static int puntajeMaximo = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu opciones = new Menu();

        while (true) {
            System.out.println("\n=======================================");
            System.out.printf("%26s", "PIXEL INVADER");
            System.out.println("\n=======================================\n");
            System.out.printf("%11s%26s", "JUGAR [1]", "PERSONALIZAR PARTIDA [2]");
            System.out.printf("\n\n%26s", "SALIR [0]");
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
                case 0:
                    System.out.println("Gracias por jugar. ¡Hasta luego!");
                    return;
                default:
                    System.out.println("OPCIÓN INVÁLIDA. INTÉNTELO DE NUEVO.");
            }
        }
    }

    public static void setPuntajeMaximo(int puntaje) {
        if (puntaje > puntajeMaximo) {
            puntajeMaximo = puntaje;
        }
    }

    public static int getPuntajeMaximo() {
        return puntajeMaximo;
    }
}