public class Proyectil {
    private int daño = 1;
    private boolean activo;
    private int x;
    private int y;
    private int direccion;
    private boolean esDeJugador;

    public Proyectil(int x, int y, int direccion, boolean esDeJugador) {
        this.x = x;
        this.y = y;
        this.activo = true;
        this.direccion = direccion;
        this.esDeJugador = esDeJugador;
    }

    public boolean esDeJugador(){
        return esDeJugador;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isActivo() {
        return activo;
    }

    public void impacto() {
        this.activo = false;
    }

    public void mover() {
        x += direccion;
        if (x < 0 || x >= 10) {
            activo = false;
        }
    }

    public void desactivar() {
        activo = false;
    }

    public int getDaño() {
        return daño;
    }

    public void setDaño(int daño) {
        this.daño = daño;
    }
}