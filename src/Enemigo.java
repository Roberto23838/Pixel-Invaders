
public class Enemigo {
	//Atributos
	public String tipo;
	private int salud=1;
	private int ataque=1;
	//Constructor
	public Enemigo(String tipo, int salud, int ataque) {
		this.tipo=tipo;
		this.salud=salud;
		this.ataque=ataque;
	}
	//Metodos
	public void Atacar(int ataque) {
		
	}
	public void RecibirDaño(int daño) {
		
	}
	public boolean EstaVivo(int enemigo) {
		boolean sivive=true;
		if(salud!=1) {
			return sivive=false;
		} else {}
		return sivive;
	}
	public int MostrarEstado(int salud) {
		if(getSalud()>0) {
			String cantsalud=String.format("Salud: %d"+ getSalud());
		}
		return 1;
		
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo=tipo;
	}
	public int getSalud() {
		return this.salud;
	}
	public void setSalud(int salud) {
		this.salud = salud;
	}
	public int getAtaque() {
		return ataque;
	}
	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

}
