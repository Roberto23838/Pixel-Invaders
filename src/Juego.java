import java.util.Scanner;
import java.util.Random;

public class Juego {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=======================================");
        System.out.printf("%28s", "PIXEL INVADER\n");
        System.out.println("=======================================");
        System.out.printf("%12s%12s%12s\n", "Controles[1]", "JUGAR[2]", "Salir[3]");
        System.out.println("=======================================\n");

        System.out.print("Ingrese la elección: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.printf("\nControles basicos: %-10s|%-20s|%-20s|%-20s\n", "Atacar: S", "Mover izquierda: A", "Mover derecha: D", "Mover arriba: W");
                break;
            case 2:
                Random random = new Random();

                Scanner scannerNombre = new Scanner(System.in);

                int randomNumero = random.nextInt(1000) + 1;


                System.out.print("\nIngrese el nombre de jugador: ");
                String nombre = scannerNombre.nextLine();

                Jugador jugador = new Jugador(nombre + randomNumero);
                jugador.jugadorNombreBase();
                break;

            case 3:
                System.out.println("¡Vuelva pronto!");
                break;
            default:
                System.out.println("Opción no válida. Intentalo de nuevo.");
        }


    }
}
