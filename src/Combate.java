import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Combate {
	//Atributo
    private final char[][] mapa;
    private final Enemigo[][] enemigos;
    private final int filas = 10;
    private final int columnas = 10;
    private List<Bala> balas;
    //Constructor
    public Combate() {
        mapa = new char[filas][columnas];
        enemigos = new Enemigo[filas][columnas];
        balas = new ArrayList<>();
        inicializarMapa();
    }
    //Metodos
    private void inicializarMapa() {
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
        int nuevoY = y + direccion;
        if (esCeldaValida(x, nuevoY)) {
			balas.add(new Bala(x, nuevoY, direccion));
        }
    }
    public void actualizarBalas() {
        List<Bala> balasInactivas = new ArrayList<>();

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

            char celda = getContenido(x, y);

            if (celda == 'O') {
                eliminarEnemigo(x, y);
                b.desactivar();
                balasInactivas.add(b);
                System.out.println("¡Enemigo eliminado en (" + x + "," + y + ")!");
            } else if (celda == 'Y') {
                System.out.println("¡El jugador fue alcanzado!");
                b.desactivar();
                balasInactivas.add(b);
            } else {
                mapa[x][y] = '|';
            }
        }

        balas.removeAll(balasInactivas);
    }

    public void mostrarMapa() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }
    private void disparosEnemigosAutomaticos() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (mapa[i][j] == 'O') {
                    int direccion = 1;
                    int nuevaY = j + direccion;
                    if (esCeldaValida(i, j + direccion)) {
                        balas.add(new Bala(i, j + direccion, direccion));
                        System.out.println("Enemigo en (" + i + "," + j + ") disparó");
                    }
                }
            }
        }
    }
}
