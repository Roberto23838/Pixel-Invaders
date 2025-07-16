import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Combate {
    private final char[][] mapa;
    private final Enemigo[][] enemigos;
    private final int filas = 10;
    private final int columnas = 10;
    private List<Proyectil> proyectiles;
    private Jugador jugador;


    public Combate() {
        mapa = new char[filas][columnas];
        enemigos = new Enemigo[filas][columnas];
        proyectiles = new ArrayList<>();
        inicializarMapa();
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    private void inicializarMapa() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                mapa[i][j] = '.';
                enemigos[i][j] = null;
            }
        }
    }

    public void colocarJugador(int x, int y) {
        mapa[x][y] = 'U';
    }

    public void colocarEnemigos(int cantidadEnemigos) {
        Random rand = new Random();
        int colocados = 0;
        while (colocados < cantidadEnemigos) {
            int x = 0;
            int y = rand.nextInt(columnas);
            if (mapa[x][y] == '.') {
                mapa[x][y] = '0';
                enemigos[x][y] = new Enemigo("Tipo" + colocados, 2 + rand.nextInt(3), 1 + rand.nextInt(2));
                colocados++;
            }
        }
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
        if (esCeldaValida(x, y) && mapa[x][y] == '.') {
            proyectiles.add(new Proyectil(x, y, direccion));
        }
    }

    public void actualizarBalas() {
        List<Proyectil> balasInactivas = new ArrayList<>();

        for (Proyectil p : proyectiles) {
            if (!p.isActivo()) continue;

            p.mover();
            int x = p.getX();
            int y = p.getY();

            if (!esCeldaValida(x, y)) {
                p.desactivar();
                balasInactivas.add(p);
                continue;
            }

            char celda = getContenido(x, y);

            if (celda == '0') {
                eliminarEnemigo(x, y);
                p.desactivar();
                balasInactivas.add(p);
                System.out.println("¡Enemigo eliminado en (" + x + "," + y + ")!");
            } else if (celda == 'U') {
                System.out.println("¡El jugador fue alcanzado!");
                p.desactivar();
                balasInactivas.add(p);
            } else {
                mapa[x][y] = '|';
            }
        }

        proyectiles.removeAll(balasInactivas);
    }

    public void moverJugador(int xViejo, int yViejo, int xNuevo, int yNuevo) {
        mapa[xViejo][yViejo] = '.';
        mapa[xNuevo][yNuevo] = 'U';

    }

    public void limpiarProyectilesDelMapa() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] == '|') {
                    mapa[i][j] = '.';
                }
            }
        }
    }

    public int[] obtenerPosicionJugador() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (mapa[i][j] == 'U') {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public void mostrarMapa() {
        System.out.println("\n============================================================");
        if (jugador != null) {
            System.out.printf("Jugador: %-15s Salud: %-2d Ataque: %-2d Puntaje: %-2d\n",
                    jugador.getNombre(), jugador.getSalud(), jugador.getAtaque(), jugador.getPuntaje());
        }
        System.out.println("============================================================\n");

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }
}
