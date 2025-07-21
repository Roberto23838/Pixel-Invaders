import java.util.Random;
import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);

    int cantidadEnemigos = 8;
    int vidaJugador = 3;
    Combate combate = new Combate();

    public void configuracionPartida() {
        while (true) {
            espaciado();
            linea();
            System.out.printf("%28s", "CONFIGURACIÓN DE PARTIDA");
            linea();
            System.out.println("  [1] Cantidad de enemigos");
            System.out.println("  [2] Dificultad");
            System.out.println("  [3] Vida inicial del jugador");
            System.out.println("  [4] Volver al menú principal");
            linea();
            System.out.print("Eliga una opción (1-4): ");

            int subopcion = scanner.nextInt();
            scanner.nextLine();

            switch (subopcion) {
                case 1:
                    cantidadEnemigosMapa();
                    break;
                case 2:
                    dificultad();
                    break;
                case 3:
                    vida();
                    break;
                case 4:
                    System.out.println("Regresando al menú principal");
                    return;
                default:
                    System.out.println("¡Opción no válida!");
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
        jugador.setSalud(vidaJugador);
        jugador.setAtaque(1);

        int dificultadActual = combate.getProbabilidadEnemigo();
        combate = new Combate();
        combate.setProbabilidadEnemigo(dificultadActual);

        combate.setJugador(jugador);
        combate.colocarJugador(9, 8);
        combate.colocarEnemigos(cantidadEnemigos);

        for (int i = 1; i <= 300; i++) {
            combate.mostrarMapa();
            System.out.print("Movimiento (WASD): ");
            String entrada = scanner.nextLine().toLowerCase();

            if (entrada.equals("0")) {
                System.out.println("  Regresando al menú principal");
                break;
            }

            int[] posicion = combate.obtenerPosicionJugador();
            int x = posicion[0];
            int y = posicion[1];
            int nuevoX = x;
            int nuevoY = y;
            boolean movimientoRealizado = false;

            if (!entrada.isEmpty()) {
                char tecla = entrada.charAt(0);

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
                        if (combate.esCeldaValida(x, nuevoY)) {
                            char contenido = combate.getContenido(x, nuevoY);
                            if (contenido == '۞') {
                                jugador.setSalud(jugador.getSalud() - 1);
                                System.out.println("¡Has recibido daño por bala enemiga! Salud restante: " + jugador.getSalud());
                                combate.eliminarBalaEnPosicion(x, nuevoY);
                                combate.moverJugador(x, y, x, nuevoY);
                                movimientoRealizado = false;
                            } else if (contenido == '.') {
                                movimientoRealizado = true;
                            } else {
                                System.out.println("Movimiento no válido");
                                movimientoRealizado = false;
                            }
                        }
                        break;
                    case 'd':
                        nuevoY = y + 1;
                        movimientoRealizado = true;
                        if (combate.esCeldaValida(x, nuevoY)) {
                            char contenido = combate.getContenido(x, nuevoY);
                            if (contenido == '۞') {
                                jugador.setSalud(jugador.getSalud() - 1);
                                System.out.println("¡Has recibido daño por bala enemiga! Salud restante: " + jugador.getSalud());
                                combate.eliminarBalaEnPosicion(x, nuevoY);
                                combate.moverJugador(x, y, x, nuevoY);
                                movimientoRealizado = false;
                            } else if (contenido == '.') {
                                movimientoRealizado = true;
                            } else {
                                System.out.println("Movimiento no válido");
                                movimientoRealizado = false;
                            }
                        }
                        break;
                    case ' ':
                        if (combate.esCeldaValida(x - 1, y) && combate.getContenido(x - 1, y) == '.') {
                            combate.dispararBala(x - 1, y, -1, true);
                        }
                        break;
                    default:
                        System.out.println("  Movimiento no válido.");
                }

                if (movimientoRealizado) {
                    if (combate.esCeldaValida(nuevoX, nuevoY) && combate.getContenido(nuevoX, nuevoY) == '.') {
                        combate.moverJugador(x, y, nuevoX, nuevoY);
                    } else {
                        System.out.println("  Movimiento no válido");
                    }
                }
            }

            combate.actualizarBalasYColisionarBalas();
            combate.disparoEnemigosAleatorio();
            combate.mostrarMapa();

            if (!combate.enemigosVivosSiNo()) {
                combate.mostrarMapa();
                System.out.println("¡GANASTE!");
                break;
            }
            if (vidaJugador <= 0){
                combate.mostrarMapa();
                System.out.println("Perdiste");
                break;
            }
        }
    }

    // Cantidad de enemigos
    public void cantidadEnemigosMapa() {
        while (true) {
            espaciado();
            linea();
            System.out.printf("%26s", "CANTIDAD DE ENEMIGOS");
            linea();
            System.out.print("  Enemigos a generar (max. 17)");
            linea();
            cantidadEnemigos = scanner.nextInt();

            if (cantidadEnemigos > 0 && cantidadEnemigos <= 17) {
                System.out.println("    Se ha generado " + cantidadEnemigos + " enemigos");
                break;
            } else {
                System.out.println("¡Error!");
            }
        }
    }

    // Dificultad del juego
    public void dificultad() {
        while (true) {
            espaciado();
            linea();
            System.out.printf("%22s", "DIFICULTAD");
            linea();
            System.out.println(" [1] Fácil");
            System.out.println(" [2] Medio");
            System.out.println(" [3] Difícil");
            linea();
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    combate.setProbabilidadEnemigo(8);
                    System.out.println("\n     Dificultad: Fácil");
                    return;
                case 2:
                    combate.setProbabilidadEnemigo(20);
                    System.out.println("\n     Dificultad: Medio");
                    return;
                case 3:
                    combate.setProbabilidadEnemigo(40);
                    System.out.println("\n     Dificultad: Difícil");
                    return;
                default:
                    System.out.println("\n  ¡Opción no válida!");
            }
        }
    }

    // Vida inicial del jugador
    public void vida() {
        while (true) {
            espaciado();
            linea();
            System.out.printf("%24s", "VIDA DEL JUGADOR");
            linea();
            System.out.println("  Nota: 10 > Vida > 0");
            linea();
            System.out.print("Ingresa la vida: ");
            vidaJugador = scanner.nextInt();
            if (vidaJugador < 10 && vidaJugador >= 1){
                System.out.println("\n       Se añadió vida: " + vidaJugador);
                break;
            } else {
                System.out.println("\n       ¡Error!");
            }
        }
    }

    // Métodos extra
    public void linea() {
        System.out.println("\n═════════════════════════════════");
    }

    public void espaciado() {
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }
}
