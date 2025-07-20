import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Combate {
    private final char[][] mapa;
    private final Enemigo[][] enemigos;
    private int filas = 11;
    private int columnas = 11;
    private List<Bala> balas;
    private Jugador jugador;
    int[] posicion = obtenerPosicionJugador();

    public Combate(int filas, int columnas, int numEnemigos) {
        this.filas = filas;
        this.columnas = columnas;
        this.mapa = new char[filas][columnas];
        this.enemigos = new Enemigo[filas][columnas];
        this.balas = new ArrayList<>();

        inicializarMapa();
        colocarJugador(filas - 1, columnas / 2);
        colocarEnemigos(numEnemigos);

        this.posicion = obtenerPosicionJugador();
    }

    public void inicializarMapa() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                mapa[i][j] = '.';
                enemigos[i][j] = null;
            }
        }
    }

    public void colocarJugador(int x, int y) {
        mapa[x][y] = 'Y';
    }

    public void colocarEnemigos(int cantidadEnemigos) {
        Random rand = new Random();
        int colocados = 0;
        while (colocados < cantidadEnemigos) {
            int x = 0;
            int y = rand.nextInt(columnas);
            if (mapa[x][y] == '.') {
                mapa[x][y] = 'O';
                enemigos[x][y] = new Enemigo("Tipo" + colocados, 2 + rand.nextInt(3), 1 + rand.nextInt(2));
                colocados++;
            }
        }
    }

    public void moverJugador(int xViejo, int yViejo, int xNuevo, int yNuevo) {
        mapa[xViejo][yViejo] = '.';
        mapa[xNuevo][yNuevo] = 'Y';
    }

    public boolean esCeldaValida(int x, int y) {
        return x >= 0 && x < filas && y >= 0 && y < columnas;
    }

    public char getContenido(int x, int y) {
        return mapa[x][y];
    }

    public void eliminarEnemigo(int x, int y) {
        enemigos[x][y] = null;
        mapa[x][y] = '.';
    }

    public void dispararBala(int x, int y, int direccion) {
        if (esCeldaValida(x + direccion, y)) {
        	balas.add(new Bala(x, y, direccion, true));
        }
    }

    

    public int[] obtenerPosicionJugador() {
        if (mapa == null) {
            return null;
        }

        for (int i = filas - 1; i >= 0; i--) {
            for (int j = 0; j < columnas; j++) {
                if (mapa[i][j] == 'Y') {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{filas - 1, columnas / 2};
    }
    public void mostrarMapa() {
    	 System.out.println("\n════════════════════════════════════════════════════════════");
         if (jugador != null) {
             System.out.printf("Jugador: %-15s Salud: %-2d Ataque: %-2d Puntaje: %-2d\n",
                     jugador.getNombre(), jugador.getSalud(), jugador.getAtaque(), jugador.getPuntaje());
         }
         System.out.println("════════════════════════════════════════════════════════════\n");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    public void disparosEnemigosAutomaticos() {
    	Random rand = new Random();
    	for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (mapa[i][j] == 'O') {
                    if (rand.nextInt(100) < 40) {
                        int direccion = 1;
                        int nuevaX = i + direccion;
                        if (esCeldaValida(nuevaX, j)) {
                            balas.add(new Bala(i, j, direccion, false));
                        }
                    }
        }
            }
    	}
    }
    public void actualizarYColisionarBalas() {
        List<Bala> balasInactivas = new ArrayList<>();
        char[][] temporal = new char[filas][columnas];
        int[][] contadorBalas = new int[filas][columnas];

        for (Bala b : balas) {
            if (b.isActivo()) {
                int oldX = b.getX();
                int oldY = b.getY();
                if (esCeldaValida(oldX, oldY) && (mapa[oldX][oldY] == '^' || mapa[oldX][oldY] == 'v' || mapa[oldX][oldY] == '|')) {
                    mapa[oldX][oldY] = '.';
                }
            }
        }

        for (Bala b : balas) {
            if (!b.isActivo()) continue;

            b.mover();
            int x = b.getX();
            int y = b.getY();

            if (!esCeldaValida(x, y)) {
                b.desactivar();
                balasInactivas.add(b);
                continue;
            }

            contadorBalas[x][y]++;

            char celda = mapa[x][y];

            if (celda == 'O' && b.DelJugador()) {
                eliminarEnemigo(x, y);
                jugador.aumentarPuntaje(10);
                b.desactivar();
                balasInactivas.add(b);
                System.out.println("Puntaje: " + jugador.getPuntaje());
            } else if (celda == 'Y' && !b.DelJugador()) {
                jugador.setSalud(jugador.getSalud() - b.getDaño());
                System.out.println("Detonado por un misil enemigo XD		 Salud restante: " + jugador.getSalud());
                b.desactivar();
                balasInactivas.add(b);

                if (jugador.getSalud() <= 0) {
                    System.out.println("Muelto");
                    mapa[x][y] = '☠';
                }
            } else {
                if (b.DelJugador()) {
                    temporal[x][y] = (temporal[x][y] == 'v') ? 'X' : '^';
                } else {
                    temporal[x][y] = (temporal[x][y] == '^') ? 'X' : 'v';
                }
            }
        }

        for (Bala b : balas) {
            if (!b.isActivo()) continue;

            int x = b.getX();
            int y = b.getY();

            if (contadorBalas[x][y] > 1 || temporal[x][y] == 'X') {
                b.desactivar();
                mapa[x][y] = '.';
            }
        }

        for (Bala b : balas) {
            if (!b.isActivo()) continue;
            int x = b.getX();
            int y = b.getY();
            mapa[x][y] = b.DelJugador() ? '^' : 'v';
        }

        balas.removeIf(b -> !b.isActivo());
    }
}    