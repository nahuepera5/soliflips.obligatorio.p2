package dominio;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

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
		int xPos = x;
		int yPos = y;
		// Mirar hacia Arriba
		while (xPos >= 0 && yPos < this.tablero[0].length) {
			this.tablero[xPos][yPos].setColor(color);
			xPos--;
			yPos++;
		}
		xPos = x;
		yPos = y;
		// Mirar hacia Abajo
		while (xPos < this.tablero.length && yPos >= 0) {
			System.out.println(x);
			this.tablero[xPos][yPos].setColor(color);
			xPos++;
			yPos--;
		}
	}

	private void cambioDiagonalIzquierda(int x, int y, String color) {
		int xPos = x;
		int yPos = y;
		// Mirar hacia Arriba
		while (xPos >= 0 && yPos >= 0) {
			this.tablero[xPos][yPos].setColor(color);
			xPos--;
			yPos--;
		}
		xPos = x;
		yPos = y;
		// Mirar hacia Abajo
		while (xPos < this.tablero.length && yPos < this.tablero[0].length) {
			this.tablero[xPos][yPos].setColor(color);
			xPos++;
			yPos++;
		}

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
            
            String nombreArchivo = "datos.txt";
        
            try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
                String linea;
                linea = br.readLine();
                String[] pos = linea.split(" ");
                Ficha[][] generado = new Ficha[ Integer.parseInt(pos[0]) ][ Integer.parseInt(pos[1])];

                for(int i = 0; i < Integer.parseInt(pos[0]); i++){
                    linea = br.readLine();
                    String[] valores = linea.split(" ");
                    for(int j = 0; j < valores.length; j++){
                        String color;
                        if(valores[j].charAt(1) == 'R'){
                            color = "ROJO";
                        }else{
                            color = "AZUL";
                        }
                        Ficha ficha = new Ficha(valores[j].charAt(0), color);
                        generado[i][j] = ficha;
                    }

                    
                }
                this.setTablero(generado);
                linea = br.readLine();
                        
            } catch (IOException e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
            }
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
