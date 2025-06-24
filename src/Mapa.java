
public class Mapa {
	 private final char[][] mapa;
	    private final int filas = 10;
	    private final int columnas = 10;

	    public Mapa() {
	        mapa = new char[filas][columnas];
	        inicializarMapa();
	    }

	    private void inicializarMapa() {
	        for (int i = 0; i < filas; i++) {
	            for (int j = 0; j < columnas; j++) {
	                mapa[i][j] = '.';
	            }
	        }
	    }

	    public void colocarJugador(int x, int y) {
	        mapa[x][y] = 'J';
	    }

	    public void colocarEnemigo(int x, int y) {
	        mapa[x][y] = 'E';
	    }

	    public void mostrarMapa() {
	        for (int i = 0; i < filas; i++) {
	            for (int j = 0; j < columnas; j++) {
	                System.out.print(mapa[i][j] + " ");
	            }
	            System.out.println();
	        }
	    }

	    public void limpiarCelda(int x, int y) {
	        mapa[x][y] = '.';
	    }
	}

