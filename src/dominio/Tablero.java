package dominio;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tablero {

    private Ficha[][] tablero;
    private ArrayList<Movimiento> listaSoluciones;
    private int nivel;

    public Tablero() {
    }

    public Ficha fichaEn(int x, int y) {
        return this.tablero[x][y];
    }

    // Todas las posibles generaciones: PREDETERMINADO, ALEATORIO, LECTURA
    public void generarPredeterminado() {
        // Usar el tablero por defecto
        Ficha[][] generado = {
            {new Ficha('|', "AZUL"), new Ficha('|', "AZUL"), new Ficha('-', "ROJO"), new Ficha('/', "AZUL"), new Ficha('|', "ROJO"), new Ficha('-', "ROJO")},
            {new Ficha('-', "ROJO"), new Ficha('/', "AZUL"), new Ficha('/', "AZUL"), new Ficha('|', "AZUL"), new Ficha('-', "ROJO"), new Ficha('-', "ROJO")},
            {new Ficha('-', "ROJO"), new Ficha('-', "ROJO"), new Ficha('|', "AZUL"), new Ficha('-', "ROJO"), new Ficha('/', "ROJO"), new Ficha('-', "ROJO")},
            {new Ficha('\\', "ROJO"), new Ficha('-', "ROJO"), new Ficha('|', "ROJO"), new Ficha('\\', "ROJO"), new Ficha('|', "AZUL"), new Ficha('|', "ROJO")},
            {new Ficha('\\', "ROJO"), new Ficha('/', "ROJO"), new Ficha('/', "ROJO"), new Ficha('|', "AZUL"), new Ficha('/', "AZUL"), new Ficha('\\', "AZUL")},};

        this.setTablero(generado);
        ArrayList<Movimiento> soluciones = new ArrayList<Movimiento>();
        soluciones.add(new Movimiento(5, 4));
        soluciones.add(new Movimiento(5, 6));
        soluciones.add(new Movimiento(4, 4));
        this.setListaSoluciones(soluciones);
    }
    // -- INICIO GENERACION ALEATORIA -- 

    public void generarAleatorio(int filas, int columnas, int nivel) {
        // Declaramos todo lo necesario para generar
        this.setTablero(new Ficha[filas][columnas]);
        int xPos = -1;
        int yPos = -1;
        Ficha f;
        // Generamos al azar el color de nuestra solucion generada
        String color = "ROJO";
        String opuesto = "AZUL";
        if ((int) (Math.random() * 2) == 1) {
            color = "AZUL";
            opuesto = "ROJO";
        }
        ArrayList<Movimiento> soluciones = new ArrayList<>();

        // Vamos tomando pares de (x, y) validos y fijandonos que
        // No haya otra ficha en ese lugar
        while (nivel > 0) {
            xPos = (int) (Math.random() * filas);
            yPos = (int) (Math.random() * columnas);
            if (this.tablero[xPos][yPos] == null) {
                f = Ficha.generarAleatorio(color);
                this.tablero[xPos][yPos] = f;
                // Vamos registrando los movimientos de solucion
                soluciones.add(new Movimiento(xPos, yPos));
                nivel--;
            }
        }
        // Ahora que tenemos todas las fichas iniciales, vamos a rellenar 
        // El movimiento que hacen con fichas del mismo color que la solucion
        // Cuando se cruza con un ficha ya creada las funciones
        // de rellenar entienden que solo tienen que invertir el color
        // de la ficha
        for (Movimiento mov : soluciones) {
            xPos = mov.getX();
            yPos = mov.getY();
            f = this.tablero[xPos][yPos];
            if (f.getSymbolo() == '-') {
                this.rellenarHorizonal(f, xPos, yPos, color);
            } else if (f.getSymbolo() == '|') {
                this.rellenarVertical(f, xPos, yPos, color);
            } else if (f.getSymbolo() == '/') {
                this.rellenarDiagonalDerecha(f, xPos, yPos, color);
            } else {
                this.rellenarDiagonalIzquierda(f, xPos, yPos, color);
            }
        }
        // Rellenamos todos los lugares que no son cubiertos con las fichas
        // generadas con las fichas de la solucion
        this.rellenarFaltantes(opuesto);
        this.setListaSoluciones(soluciones);
    }

    public void rellenarFaltantes(String color) {
        for (int i = 0; i < this.tablero.length; i++) {
            for (int j = 0; j < this.tablero[0].length; j++) {
                if (this.tablero[i][j] == null) {
                    this.tablero[i][j] = Ficha.generarAleatorio(color);
                }
            }
        }
    }

    public void rellenarDiagonalDerecha(Ficha f, int x, int y, String color) {
        int xPos = x;
        int yPos = y;

        // Rellenar hacia arriba
        while (xPos >= 0 && yPos < this.tablero[0].length) {
            // Aca queremos queremos tomar los casos en que se cruza con una ficha
            // que sea diferente a la que tomamos inicialmente y
            
            if (this.tablero[xPos][yPos] != null && this.tablero[xPos][yPos] != f) {
                this.tablero[xPos][yPos].invertirColor();
            } else if (this.tablero[xPos][yPos] == null) {
                this.tablero[xPos][yPos] = Ficha.generarAleatorio(color);
            }
            xPos--;
            yPos++;
        }
        xPos = x;
        yPos = y;

        // Rellenar hacia arriba
        while (xPos < this.tablero.length && yPos >= 0) {
            if (this.tablero[xPos][yPos] != null && this.tablero[xPos][yPos] != f) {
                this.tablero[xPos][yPos].invertirColor();
            } else if (this.tablero[xPos][yPos] != f) {
                this.tablero[xPos][yPos] = Ficha.generarAleatorio(color);
            }
            xPos++;
            yPos--;
        }
    }

    public void rellenarDiagonalIzquierda(Ficha f, int x, int y, String color) {
        int xPos = x;
        int yPos = y;

        // Rellenar hacia arriba
        while (xPos >= 0 && yPos >= 0) {
            if (this.tablero[xPos][yPos] != null && this.tablero[xPos][yPos] != f) {
                this.tablero[xPos][yPos].invertirColor();
            } else if (this.tablero[xPos][yPos] != f) {
                this.tablero[xPos][yPos] = Ficha.generarAleatorio(color);
            }
            xPos--;
            yPos--;
        }
        xPos = x;
        yPos = y;

        // Rellenar hacia arriba
        while (xPos < this.tablero.length && yPos < this.tablero[0].length) {
            if (this.tablero[xPos][yPos] != null && this.tablero[xPos][yPos] != f) {
                this.tablero[xPos][yPos].invertirColor();
            } else if (this.tablero[xPos][yPos] != f) {
                this.tablero[xPos][yPos] = Ficha.generarAleatorio(color);
            }
            xPos++;
            yPos++;
        }

    }

    public void rellenarHorizonal(Ficha f, int x, int y, String color) {
        for (int i = 0; i < this.tablero[0].length; i++) {
            if (this.tablero[x][i] != null && this.tablero[x][i] != f) {
                this.tablero[x][i].invertirColor();
            } else if (this.tablero[x][i] != f) {
                this.tablero[x][i] = Ficha.generarAleatorio(color);
            }
        }
    }

    public void rellenarVertical(Ficha f, int x, int y, String color) {
        for (int i = 0; i < this.tablero.length; i++) {
            if (this.tablero[i][y] != null && this.tablero[i][y] != f) {
                this.tablero[i][y].invertirColor();
            } else if (this.tablero[i][y] != f) {
                this.tablero[i][y] = Ficha.generarAleatorio(color);
            }
        }
    }

    // --- FIN RELLENAR ---
    public void generarPorLectura() {

        String nombreArchivo = "datos.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea = br.readLine();
            String[] pos = linea.split(" ");
            Ficha[][] generado = new Ficha[Integer.parseInt(pos[0])][Integer.parseInt(pos[1])];

            for (int i = 0; i < Integer.parseInt(pos[0]); i++) {
                linea = br.readLine();
                String[] valores = linea.split(" ");
                for (int j = 0; j < valores.length; j++) {
                    String color = "AZUL";
                    if (valores[j].charAt(1) == 'R') {
                        color = "ROJO";
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

    public ArrayList<Movimiento> getListaSoluciones() {
        return this.listaSoluciones;
    }

    private void setListaSoluciones(ArrayList<Movimiento> listaSoluciones) {
        this.listaSoluciones = listaSoluciones;
    }

    public int getFilas() {
        return this.tablero.length;
    }

    public int getColumnas() {
        return this.tablero[0].length;
    }

}
