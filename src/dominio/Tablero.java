package dominio;

import java.util.ArrayList;

public class Tablero {

	private Ficha[][] tablero;
	private ArrayList<Movimiento> listaMovimientos;

	public Tablero() {
		this.listaMovimientos = new ArrayList<Movimiento>();
	}

	public void cambiarColorEnFicha(int x, int y) {
		this.listaMovimientos.add(new Movimiento(x, y));
		Ficha f = this.tablero[x][y];
		String color = f.getColorOpuesto();

		if (f.getSymbolo() == '-') {
			this.cambioHorizontal(x, y, color);
		} else if (f.getSymbolo() == '|') {
			this.cambioVertical(x, y, color);
		} else if (f.getSymbolo() == '/') {
			this.cambioDiagonalDerecha(x, y, color);
		} else if (f.getSymbolo() == '\\') {
			this.cambioDiagonalIzquierda(x, y, color);
		}

	}

	// Todos los posibles cambios
	private void cambioHorizontal(int x, int y, String color) {
		for (int i = 0; i < this.tablero[0].length; i++) {
			this.tablero[x][i].setColor(color);
		}
	}

	private void cambioDiagonalDerecha(int x, int y, String color) {

	}

	private void cambioDiagonalIzquierda(int x, int y, String color) {
		
	}

	private void cambioVertical(int x, int y, String color) {
		for (int i = 0; i < this.tablero.length; i++) {
			this.tablero[i][y].setColor(color);
		}
	}

	// Todas las posibles generaciones
	public void generarPredeterminado() {
		// Usar el tablero por defecto
		Ficha[][] generado = {
			{new Ficha('|', "AZUL"), new Ficha('|', "AZUL"), new Ficha('-', "ROJO"), new Ficha('/', "AZUL"), new Ficha('|', "ROJO"), new Ficha('-', "ROJO")},
			{new Ficha('-', "ROJO"), new Ficha('/', "AZUL"), new Ficha('/', "AZUL"), new Ficha('|', "AZUL"), new Ficha('-', "ROJO"), new Ficha('-', "ROJO")},
			{new Ficha('-', "ROJO"), new Ficha('-', "ROJO"), new Ficha('|', "AZUL"), new Ficha('-', "ROJO"), new Ficha('/', "ROJO"), new Ficha('-', "ROJO")},
			{new Ficha('\\', "ROJO"), new Ficha('-', "ROJO"), new Ficha('|', "ROJO"), new Ficha('\\', "ROJO"), new Ficha('|', "AZUL"), new Ficha('|', "ROJO")},
			{new Ficha('\\', "ROJO"), new Ficha('/', "ROJO"), new Ficha('/', "ROJO"), new Ficha('|', "AZUL"), new Ficha('/', "AZUL"), new Ficha('\\', "AZUL")},};

		this.setTablero(generado);
	}

	public void generarAleatorio(int filas, int columnas, int nivel) {
		// Usar un tablero creado en el momento
		this.tablero = new Ficha[filas][columnas];
	}

	public void generarPorLectura() {
		// Usar un tablero especificado en el archivo "datos.txt"
	}

	public Ficha[][] getTablero() {
		return this.tablero;
	}

	private void setTablero(Ficha[][] tablero) {
		this.tablero = tablero;
	}

	public ArrayList<Movimiento> getListaMovimientos() {
		return this.listaMovimientos;
	}

	private void setListaMovimientos(ArrayList<Movimiento> listaMovimientos) {
		this.listaMovimientos = listaMovimientos;
	}

}
