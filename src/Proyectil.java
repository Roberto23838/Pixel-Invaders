
public class Proyectil {
    private int daño = 1;
    private boolean activo;
    private int x;
    private int y;
    private int direcion;

    public Proyectil(int x, int y, int direcion) {
        this.x = x;
        this.y = y;
        this.activo = true;
        this.direcion = direcion;
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
        x += direcion;
        if (y < 0 || y >= 10) {
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