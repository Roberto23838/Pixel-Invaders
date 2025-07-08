
public class Main {
	public static void main(String[] args) {
	    Combate combate = new Combate();
	    combate.colocarJugador(9, 5);
	    combate.colocarEnemigos(5);
	    combate.mostrarMapa();

	    for (int i = 1; i <= 10; i++) {
	        System.out.println("\n--- Turno " + i + " ---");
	        combate.mostrarMapa();

	        try { Thread.sleep(1000); } catch (InterruptedException e) {}
	    }
	}
}
