import java.util.Random;

public class Mapa {
	//Atributo
    private final char[][] mapa;
    private final Enemigo[][] enemigos;
    private final int filas = 10;
    private final int columnas = 10;
    //Constructor
    public Mapa() {
        mapa = new char[filas][columnas];
        enemigos = new Enemigo[filas][columnas];
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
            int x = rand.nextInt(filas);
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
    public void mostrarMapa() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }
}
