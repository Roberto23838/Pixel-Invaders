import java.util.Random;
import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);

    int cantidadEnemigos = 5;

    public void configuracionPartida() {

        while (true) {
            separacionTop();
            lineaDecorativa();
            System.out.printf("%28s", "PERSONALIZAR PARTIDA");
            lineaDecorativa();
            System.out.println("[1] TAMAÑO DEL MAPA");
            System.out.println("[2] CANTIDAD DE ENEMIGOS");
            System.out.println("[3] REGRESAR AL MENÚ PRINCIPAL");
            lineaDecorativa();
            System.out.print("\nElija una opción: ");

            int subopcion = scanner.nextInt();
            scanner.nextLine();

            switch (subopcion) {
                case 1:
                    separacionTop();
                    lineaDecorativa();
                    System.out.printf("%26s", "TAMAÑO DEL MAPA");
                    lineaDecorativa();
                    System.out.println("Ingrese el ancho del mapa [min. 10]: ");
                    System.out.println("Ingrese la altura del mapa [min. 10]: ");
                    lineaDecorativa();

                    break;

                case 2:
                    separacionTop();
                    lineaDecorativa();
                    System.out.printf("%26s", "CANTIDAD DE ENEMIGOS");
                    lineaDecorativa();
                    while (true) {
                        System.out.print("\nINGRESE LA CANTIDAD DE ENEMIGOS [MIN. 5]: ");
                        cantidadEnemigos = scanner.nextInt();
                        lineaDecorativa();

                        if (cantidadEnemigos > 0 && cantidadEnemigos <= 10) {
                            break;
                        } else {
                            System.out.println("¡ERROR AL INGRESAR LA CANTIDAD!");
                            lineaDecorativa();
                        }
                    }
                    break;

                case 3:
                    System.out.println("[Regresando al menú principal]\n");
                    return;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }

        }

    }

    public void iniciarPartida() {
        Random random = new Random();
        int randomNumero = random.nextInt(10000) + 1;

        System.out.println();
        System.out.print("INGRESE EL NOMBRE DEL JUGADOR: ");
        String nombre = scanner.nextLine().toUpperCase();

        Jugador jugador = new Jugador(nombre + "#" + randomNumero);
        jugador.setSalud(3);
        jugador.setAtaque(2);
        Combate combate = new Combate();
        combate.setJugador(jugador);
        combate.colocarJugador(9, 5);
        combate.colocarEnemigos(cantidadEnemigos);

        for (int i = 1; i <= 100; i++) {
            combate.mostrarMapa();

            System.out.print("\nMOVIMIENTO: ");
            String movimiento = scanner.nextLine().toLowerCase();

            if (movimiento.equals("0")) {
                System.out.println("[Saliendo del juego y regresando al menú principal]");
                break;
            }

            int[] posicion = combate.obtenerPosicionJugador();
            int x = posicion[0];
            int y = posicion[1];
            int nuevoX = x;
            int nuevoY = y;

            boolean movimientoRealizado = false;

            if (!movimiento.isEmpty()) {
                char tecla = movimiento.charAt(0);

                switch (tecla) {
                    case 'w':
                        nuevoX = x - 1;
                        movimientoRealizado = true;
                        break;
                    case 's':
                        nuevoX = x + 1;
                        movimientoRealizado = true;
                        break;
                    case 'a':
                        nuevoY = y - 1;
                        movimientoRealizado = true;
                        break;
                    case 'd':
                        nuevoY = y + 1;
                        movimientoRealizado = true;
                        break;
                    case ' ':
                        if (combate.esCeldaValida(x - 1, y) && combate.getContenido(x - 1, y) == '.') {
                            combate.dispararBala(x - 1, y, -1);
                        }
                        break;
                    default:
                        System.out.println("Movimiento no válido.");
                }

                if (movimientoRealizado) {
                    if (combate.esCeldaValida(nuevoX, nuevoY) && combate.getContenido(nuevoX, nuevoY) == '.') {
                        combate.moverJugador(x, y, nuevoX, nuevoY);
                    } else {
                        System.out.println("Movimiento no válido.");
                    }
                }

            }

            combate.limpiarProyectilesDelMapa();
            combate.actualizarBalas();

        }

    }


    public void separacionTop() {
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }

    public void lineaDecorativa() {
        System.out.println("\n=======================================");
    }

}
