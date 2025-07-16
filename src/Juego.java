import java.util.Scanner;
import java.util.Random;
public class Juego {
	private static int puntajeMaximo = 0;
	
    public static void main(String[] args) {
    	System.out.println("Maximo Puntaje del Juego: " + puntajeMaximo);

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
                combate.actualizarYColisionarBalas();
                if (jugador.getSalud() <= 0) {
                    System.out.println("La nave ha sido destruida intentelo de nuevo.");
                    break;
                }
                continue;
            }

            char tecla = letra.charAt(0);
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
                case 'f':
                    combate.dispararBala(x, y, -1); 
                    break;
                default:
                    System.out.println("Movimiento inválido.");
                    break;
            }

            if (combate.esCeldaValida(nuevoX, nuevoY) && combate.getContenido(nuevoX, nuevoY) == '.') {
                combate.moverJugador(x, y, nuevoX, nuevoY);
            }else {
            	System.out.println("Movimiento no valido");
            }
                                    
            combate.actualizarYColisionarBalas(); 
            combate.disparosEnemigosAutomaticos();
            combate.mostrarMapa();
            }
        if (jugador.getPuntaje() > puntajeMaximo) {
            puntajeMaximo = jugador.getPuntaje();
            System.out.println("\nNuevo puntaje");
        }
        System.out.println("Puntaje final de " + jugador.getNombre() + " es de : " + jugador.getPuntaje()+" puntos");
        System.out.println("Puntaje maximo del juego: " + puntajeMaximo);
        }
        
    }
