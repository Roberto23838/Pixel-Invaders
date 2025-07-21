import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Combate {

    private final char[][] mapa;
    private final Enemigo[][] enemigos;
    private final int filas = 10;
    private final int columnas = 17;
    private List<Proyectil> proyectiles;
    private Jugador jugador;
    private int probabilidadEnemigo = 20;
    private Juego juego;

    public Combate() {
        mapa = new char[filas][columnas];
        enemigos = new Enemigo[filas][columnas];
        proyectiles = new ArrayList<>();
        inicializarMapa();
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setProbabilidadEnemigo(int probabilidadEnemigo){
        this.probabilidadEnemigo = probabilidadEnemigo;
    }

    public int getProbabilidadEnemigo(){
        return probabilidadEnemigo;
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
        mapa[x][y] = '∆';
    }

    public boolean enemigosVivosSiNo() {
        for (int i = 0; i < enemigos.length; i++) {
            for (int j = 0; j < enemigos[i].length; j++) {
                if (enemigos[i][j] != null) {
                    return true;
                }

            }
        }
        return false;
    }

    public void colocarEnemigos(int cantidadEnemigos) {
        Random rand = new Random();
        int colocados = 0;
        while (colocados < cantidadEnemigos) {
            int x = 0;
            int y = rand.nextInt(columnas);
            if (mapa[x][y] == '.') {
                mapa[x][y] = 'w';
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

    public void dispararBala(int x, int y, int direccion, boolean esDeJugador) {
        if (esCeldaValida(x, y) && mapa[x][y] == '.') {
            proyectiles.add(new Proyectil(x, y, direccion, esDeJugador));
        }
    }

    public void disparoEnemigosAleatorio() {
        Random randomDisparoEnemigo = new Random();
        for (int i = 0; i < enemigos.length; i++) {
            for (int j = 0; j < enemigos[i].length; j++) {
                if (enemigos[i][j] != null) {
                    if (randomDisparoEnemigo.nextInt(100) < probabilidadEnemigo) {
                        int abajo = i + 1;
                        if (esCeldaValida(abajo, j) && mapa[abajo][j] == '.') {
                            proyectiles.add(new Proyectil(abajo, j, +1, false));
                        }
                    }
                }
            }
        }
    }


    public void actualizarBalasYColisionarBalas() {
        List<Proyectil> balasInactivas = new ArrayList<>();
        char[][] nuevoMapa = new char[filas][columnas];

        List<Proyectil> colisionados = new ArrayList<>();
        for (int i = 0; i < proyectiles.size(); i++) {
            Proyectil p1 = proyectiles.get(i);
            if (!p1.isActivo()) continue;

            for (int j = i + 1; j < proyectiles.size(); j++) {
                Proyectil p2 = proyectiles.get(j);
                if (!p2.isActivo()) continue;

                if (p1.getY() == p2.getY() && Math.abs(p1.getX() - p2.getX()) == 1) {
                    if (p1.esDeJugador() != p2.esDeJugador()) {
                        p1.desactivar();
                        p2.desactivar();
                        colisionados.add(p1);
                        colisionados.add(p2);

                        if (esCeldaValida(p1.getX(), p1.getY())) {
                            mapa[p1.getX()][p1.getY()] = '.';
                        }
                        if (esCeldaValida(p2.getX(), p2.getY())) {
                            mapa[p2.getX()][p2.getY()] = '.';
                        }
                    }
                }
            }
        }


        for (Proyectil p : proyectiles) {
            if (p.isActivo()) {
                int x = p.getX();
                int y = p.getY();
                if (esCeldaValida(x, y) && (mapa[x][y] == '^' || mapa[x][y] == '۞')) {
                    mapa[x][y] = '.';
                }
            }
        }

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

            if (celda == 'w' && p.esDeJugador()) {
                eliminarEnemigo(x, y);
                jugador.setPuntaje(jugador.getPuntaje() + 1);
                p.desactivar();
                balasInactivas.add(p);
                System.out.println("¡Enemigo eliminado en (" + x + "," + y + ")!");
                continue;
            }

            if (celda == '∆' && !p.esDeJugador()) {
                jugador.setSalud(jugador.getSalud() - 1);
                System.out.println("¡El jugador fue alcanzado! Salud restante: " + jugador.getSalud());
                p.desactivar();
                balasInactivas.add(p);

                if (jugador.getSalud() <= 0) {
                    System.out.println("\n¡PERDISTE! El jugador ha muerto.");
                    juego.mostrarMenuInicio();
                    return;

                }
                continue;
            }

            char anterior = nuevoMapa[x][y];
            if ((anterior == '^' && !p.esDeJugador()) || (anterior == '۞' && p.esDeJugador())) {
                p.desactivar();
                balasInactivas.add(p);

                for (Proyectil otro : proyectiles) {
                    if (otro != p && otro.isActivo() && otro.getX() == x && otro.getY() == y) {
                        otro.desactivar();
                        balasInactivas.add(otro);
                    }
                }

                nuevoMapa[x][y] = '.';
            } else {
                nuevoMapa[x][y] = p.esDeJugador() ? '^' : '۞';
            }
        }

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (nuevoMapa[i][j] == '^' || nuevoMapa[i][j] == '۞') {
                    mapa[i][j] = nuevoMapa[i][j];
                }
            }
        }

        proyectiles.removeIf(p -> !p.isActivo());
    }

    public void eliminarBalaEnPosicion(int x, int y) {
        proyectiles.removeIf(p -> p.isActivo() && p.getX() == x && p.getY() == y);
        mapa[x][y] = '.';
    }



    public void moverJugador(int xViejo, int yViejo, int xNuevo, int yNuevo) {
        mapa[xViejo][yViejo] = '.';
        mapa[xNuevo][yNuevo] = '∆';

    }

    public int[] obtenerPosicionJugador() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (mapa[i][j] == '∆') {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }


    public void mostrarMapa() {
        System.out.println("\n═════════════════════════════════════════════════════════════════════");
        if (jugador != null) {
            System.out.printf("Jugador: %-15s Salud: %-2d Ataque: %-2d Puntaje: %-2d | SALIR [0]\n",
                    jugador.getNombre(), jugador.getSalud(), jugador.getAtaque(), jugador.getPuntaje());
        }
        System.out.println("═════════════════════════════════════════════════════════════════════");

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("═════════════════════════════════════════════════════════════════════");


    }
}