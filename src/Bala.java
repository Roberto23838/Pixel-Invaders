
public class Bala {
	private int daño=1;
	private boolean activo;
	private int x;
	private int y;
	private boolean Deljugador;
	private int direccion;
	
	 public Bala(int x, int y, int direccion, boolean Deljugador) {
	        this.x = x;
	        this.y = y;
	        this.activo = true;
	        this.Deljugador=Deljugador;
	        this.direccion = direccion;
	    }
	
	public void mover() {
	        x += direccion; 
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
	    public void desactivar() {
	    	activo = false; 
	    	}

	    public int getDaño() {
	    	return daño; 
	    	}
	    public void setDaño(int daño) {
	    	this.daño = daño; 
	    	}
	    public boolean DelJugador() {
	        return Deljugador;
	    }
		public boolean isDeljugador() {
			return Deljugador;
		}

		public void setDeljugador(boolean deljugador) {
			this.Deljugador = deljugador;
		}
}
