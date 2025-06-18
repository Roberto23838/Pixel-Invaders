

public class Jugador {
		public String nombre;
		private int salud;
		private int ataque;
		private int puntaje;
		public Jugador(String nombre, int salud, int ataque, int puntaje) {
			this.nombre=nombre;
			this.salud=salud;
			this.ataque=ataque;
			this.puntaje=puntaje;
		}
		public String getNombre() {
			return nombre;
		}
		public void setSalud(String nombre) {
			this.nombre = nombre;
		}
		public int getSalud() {
			return salud;
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
		public int getPuntaje() {
			return puntaje;
		}
		public void setPuntaje(int puntaje) {
			this.puntaje = puntaje;
		}
}

