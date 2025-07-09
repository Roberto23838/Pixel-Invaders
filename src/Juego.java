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

        System.out.print("Ingrese el nombre del jugador: ");
        String nombre = scanner.nextLine().toUpperCase();

        Jugador jugador = new Jugador(nombre + randomNumero);
        jugador.setSalud(3);
        jugador.setAtaque(2);
        jugador.setPuntaje(0);

        Combate combate = new Combate();
        combate.setJugador(jugador);
        combate.colocarJugador(9, 5);
        combate.colocarEnemigos(5);

        for (int i = 1; i <= 100; i++) {
            System.out.println("\n--- Turno " + i + " ---");
            combate.mostrarMapa();

            System.out.print("Movimiento: ");
            String letra = scanner.nextLine().toLowerCase();

            if (letra.isEmpty()) {
                System.out.println("Entrada vacía.");
                continue;
            }

            char tecla = letra.toLowerCase().charAt(0);

            int[] posicion = combate.obtenerPosicionJugador();


            int x = posicion[0];
            int y = posicion[1];

            int nuevoX = x;
            int nuevoY = y;

            switch (tecla) {
                case 'w':
                    nuevoX = x - 1;
                    break;
                case 's':
                    nuevoX = x + 1;
                    break;
                case 'a':
                    nuevoY = y - 1;
                    break;
                case 'd':
                    nuevoY = y + 1;
                    break;
                default:
                    System.out.println("Movimiento inválido.");
                    continue;
            }

            if (combate.esCeldaValida(nuevoX, nuevoY) && combate.getContenido(nuevoX, nuevoY) == '.') {
                combate.moverJugador(x, y, nuevoX, nuevoY);
            } else {
                System.out.println("Movimiento no válido.");
            }
        }

        combate.actualizarBalas();

    }

}
