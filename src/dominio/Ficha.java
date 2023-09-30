package dominio;

public class Ficha {

	private char symbolo;
	private String color;

	public Ficha(char symbolo, String color) {
		this.setColor(color);
		this.setSymbolo(symbolo);
	}

	public String getColorOpuesto() {
		String opuesto = "ROJO";
		if (this.color.equals("ROJO")) {
			opuesto = "AZUL";
		}
		return opuesto;
	}

	public void invertirColor() {
		if (this.color.equals("ROJO")) {
			this.setColor("AZUL");
		} else {
			this.setColor("ROJO");
		}
	}

	public static Ficha generarAleatorio(String color) {
		char symbolo;
		int fDir = (int) (Math.random() * 4);

		if (fDir == 0) {
			// Horizontal
			symbolo = '-';
		} else if (fDir == 1) {
			// Vertical
			symbolo = '|';
		} else if (fDir == 2) {
			// Diagonal Izquierda
			symbolo = '\\';
		} else {
			// Digaonal Derecha
			symbolo = '/';
		}
		return new Ficha('a', color);
	}

	@Override
	public String toString() {
		return "" + this.symbolo + " " + this.color;
	}

	public char getSymbolo() {
		return symbolo;
	}

	private void setSymbolo(char symbolo) {
		this.symbolo = symbolo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
