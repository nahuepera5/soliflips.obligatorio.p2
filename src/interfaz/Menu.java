package interfaz;

import dominio.Ficha;
import dominio.Tablero;
import java.util.Arrays;


public class Menu {
	public static void main(String[] args) {
		Tablero tab = new Tablero();
		tab.generarPredeterminado();
		tab.cambiarColorEnFicha(0, 4);
		for (Ficha[] row: tab.getTablero()){
			System.out.println(Arrays.toString(row));
		}
	}
}
