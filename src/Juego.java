import java.util.Scanner;
import java.util.Random;

public class Juego {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=======================================");
        System.out.printf("%28s", "PIXEL INVADER");
        System.out.println("\n=======================================\n");

        Random random = new Random();

        int randomNumero = random.nextInt(10000) + 1;

        System.out.print("\nIngrese el nombre de jugador: ");
        String nombre = scanner.nextLine();

        System.out.println("");
        Jugador jugador = new Jugador(nombre + randomNumero);
        jugador.jugadorNombreBase();
        System.out.println("");



    }
}
