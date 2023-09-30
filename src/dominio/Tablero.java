package dominio;

import java.util.ArrayList;
import java.util.Iterator;

public class Tablero {

	private Ficha[][] tablero;
	private ArrayList<Movimiento> listaMovimientos;
	private Ficha[][] original;

	public Tablero() {
		this.listaMovimientos = new ArrayList<Movimiento>();
	}

	public boolean retrocederMovimiento() {
		boolean ret = false;

		if (this.listaMovimientos.size() > 0) {
			ret = true;
			this.tablero = this.generarCopia(this.original);
			for (int i = 0; i < this.listaMovimientos.size() - 1; i++) {
				Movimiento m = this.listaMovimientos.get(i);
				this.cambiarColorEnFicha(m.getX(), m.getY());
			}
			this.listaMovimientos.remove(this.listaMovimientos.size() - 1);
		}
		return ret;
	}

	public void hacerMovimiento(int x, int y) {
		this.cambiarColorEnFicha(x, y);
		this.listaMovimientos.add(new Movimiento(x, y));
	}

	// Todos los movimientos son por aca
	public void cambiarColorEnFicha(int x, int y) {
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
			this.tablero[x][i].invertirColor();
		}
	}

	private void cambioDiagonalDerecha(int x, int y, String color) {
		int xPos = x;
		int yPos = y;
		// Mirar hacia Arriba
		while (xPos >= 0 && yPos < this.tablero[0].length) {
			this.tablero[xPos][yPos].invertirColor();
			xPos--;
			yPos++;
		}
		xPos = x;
		yPos = y;
		// Mirar hacia Abajo
		while (xPos < this.tablero.length && yPos >= 0) {
			System.out.println(x);
			this.tablero[xPos][yPos].invertirColor();
			xPos++;
			yPos--;
		}
	}

	private void cambioDiagonalIzquierda(int x, int y, String color) {
		int xPos = x;
		int yPos = y;
		// Mirar hacia Arriba
		while (xPos >= 0 && yPos >= 0) {
			this.tablero[xPos][yPos].invertirColor();
			xPos--;
			yPos--;
		}
		xPos = x;
		yPos = y;
		// Mirar hacia Abajo
		while (xPos < this.tablero.length && yPos < this.tablero[0].length) {
			this.tablero[xPos][yPos].invertirColor();
			xPos++;
			yPos++;
		}

	}

	private void cambioVertical(int x, int y, String color) {
		for (int i = 0; i < this.tablero.length; i++) {
			this.tablero[i][y].invertirColor();
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
		this.setOriginal(this.generarCopia(generado));
	}

	public void generarAleatorio(int filas, int columnas, int nivel) {
		// Usar un tablero creado en el momento
		this.setTablero(new Ficha[filas][columnas]);
		int xPos;
		int yPos;
		int fDir;
		Ficha f = new Ficha('X', "ROJO");
		char symbolo;
		String color = "ROJO";
		if ((int) (Math.random() * 2) == 1) {
			color = "AZUL";
		}

		while (nivel > 0) {
			xPos = (int) (Math.random() * filas);
			yPos = (int) (Math.random() * columnas);
			fDir = (int) (Math.random() * 4);
			if (this.tablero[xPos][yPos] == null) {
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
				f = new Ficha(symbolo, color);
				this.tablero[xPos][yPos] = f;
				nivel--;
			}
		}
		color = f.getColorOpuesto();
		for (int i = 0; i < this.tablero.length; i++) {
			for (int j = 0; j < this.tablero[0].length; j++) {
				if (this.tablero[i][j] == null) {
					fDir = (int) (Math.random() * 4);
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
					this.tablero[i][j] = new Ficha(symbolo, color);
				}
			}
		}
		this.setOriginal(this.generarCopia(this.tablero));
	}

	public void generarPorLectura() {
		// Usar un tablero especificado en el archivo "datos.txt"
	}

	private Ficha[][] generarCopia(Ficha[][] tablero) {
		Ficha[][] copia = new Ficha[tablero.length][tablero[0].length];
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				copia[i][j] = new Ficha(tablero[i][j].getSymbolo(), tablero[i][j].getColor());
			}
		}
		return copia;
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

	private void setOriginal(Ficha[][] original) {
		this.original = original;
	}

	private Ficha[][] getOriginal() {
		return this.original;
	}

}
