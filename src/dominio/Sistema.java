package dominio;

import java.util.ArrayList;

public class Sistema {

    /*
	Sistema: Aqui se maneja toda la logica De la interaccion de un Juego con el Tablero.
	Tomamos el pensamiento que un tablero por si solo no es mas que informacion,
	se transforma en un Juego en cuanto alguien lo interpreta como tal
     */
    // Tablero donde se guardan y generan todos los datos necesarios para jugar
    private Tablero tablero;

    private Tablero anterior;
    // Modo elegido por el jugador
    private Modo modo;
    /*
	listaMovimiento se encarga de guardar los movimientos que tengan algun significado
	 Por ejemplo 2 movimientos en la misma (fila, columna) es equivalente a no moverse
	 Por lo cual lo descontamos
     */
    private ArrayList<Movimiento> listaMovimientos;
    // historial, tal como su nombre indica, guarda todos los movimientos hechos
    // sin tener ningun tipo de condiciones
    private ArrayList<Movimiento> historial;
    // Nos guardamos el tiempo inicial para luego poder obtener el tiempo
    // Total jugado
    private long tiempoInicio;

    public Sistema() {
        this.setTablero(new Tablero());
        this.setTiempoInicio(System.currentTimeMillis());
        this.setListaMovimientos(new ArrayList<Movimiento>());
        this.setHistorial(new ArrayList<>());
    }

    // Inciamos el tablero segun el modo pedido
    public void iniciarAleatorio(int filas, int columnas, int nivel) {
        this.getTablero().generarAleatorio(filas, columnas, nivel);
    }

    public void iniciarPorLectura() {
        this.getTablero().generarPorLectura();
    }

    public void iniciarPredeterminado() {
        this.getTablero().generarPredeterminado();
    }

    // El juego desde moverEn y retroceder
    public void moverEn(Movimiento nuevo) {
        this.setAnterior(this.getTablero().generarCopia());
        this.getHistorial().add(nuevo);
        if (!this.getListaMovimientos().contains(nuevo)) {
            this.getListaMovimientos().add(nuevo);
        } else {
            this.getListaMovimientos().remove(nuevo);
        }
        this.cambiarSegunFichaEn(nuevo.getX(), nuevo.getY());
    }

    public void retroceder() {
        Movimiento mov = this.getHistorial().get(this.getHistorial().size() - 1);
        this.moverEn(mov);
    }

    private void cambiarSegunFichaEn(int x, int y) {
        Ficha f = this.getTablero().fichaEn(x, y);

        if (f.getSymbolo() == '-') {
            this.cambioHorizontal(x, y);
        } else if (f.getSymbolo() == '|') {
            this.cambioVertical(x, y);
        } else if (f.getSymbolo() == '/') {
            this.cambioDiagonalDerecha(x, y);
        } else if (f.getSymbolo() == '\\') {
            this.cambioDiagonalIzquierda(x, y);
        }

    }

    // Todos los posibles cambios
    private void cambioHorizontal(int x, int y) {
        for (int i = 0; i < this.getTablero().getFilas(); i++) {
            this.getTablero().fichaEn(x, i).invertirColor();
        }
    }

    private void cambioDiagonalDerecha(int x, int y) {
        int xPos = x;
        int yPos = y;
        // Mirar hacia Arriba
        while (xPos >= 0 && yPos < this.getTablero().getColumnas()) {
            this.getTablero().fichaEn(xPos, yPos).invertirColor();
            xPos--;
            yPos++;
        }
        xPos = x + 1;
        yPos = y - 1;
        // Mirar hacia Abajo
        while (xPos < this.getTablero().getFilas() && yPos >= 0) {

            this.getTablero().fichaEn(xPos, yPos).invertirColor();
            xPos++;
            yPos--;
        }
    }

    private void cambioDiagonalIzquierda(int x, int y) {
        int xPos = x;
        int yPos = y;
        // Mirar hacia Arriba
        while (xPos >= 0 && yPos >= 0) {
            this.getTablero().fichaEn(xPos, yPos).invertirColor();
            xPos--;
            yPos--;
        }
        xPos = x + 1;
        yPos = y + 1;
        // Mirar hacia Abajo
        while (xPos < this.getTablero().getFilas() && yPos < this.getTablero().getColumnas()) {
            this.getTablero().fichaEn(xPos, yPos).invertirColor();
            xPos++;
            yPos++;
        }

    }

    private void cambioVertical(int x, int y) {
        for (int i = 0; i < this.getTablero().getFilas(); i++) {
            this.getTablero().fichaEn(i, y).invertirColor();
        }
    }

    public ArrayList<Movimiento> getListaSoluciones() {
        // Si para cada solucion del tablero hay un movimiento significativo
        // Asociado y nada mas. Entonces estas en la solucion
        ArrayList<Movimiento> soluciones = (ArrayList<Movimiento>) this.getTablero().getListaSoluciones().clone();
        
        for (Movimiento mov : this.listaMovimientos) {
            if (soluciones.contains(mov)) {
                soluciones.remove(mov);
            } else {
                soluciones.add(mov);
            }
        }

        return soluciones;
    }

    public boolean esVictoria() {
        // 
        Tablero tab = this.getTablero();
        boolean res = true;
        String color = tab.getTablero()[0][0].getColor();
        for (int i = 0; i < tab.getTablero().length && res; i++) {
            for (int j = 0; j < tab.getTablero()[0].length && res; j++) {
                if (!color.equals(tab.getTablero()[i][j].getColor())) {
                    res = false;
                }
            }
        }

        return res;
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    private void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Modo getModo() {
        return this.modo;
    }

    private void setModo(Modo modo) {
        this.modo = modo;
    }

    public ArrayList<Movimiento> getListaMovimientos() {
        return this.listaMovimientos;
    }

    private void setListaMovimientos(ArrayList<Movimiento> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }

    public ArrayList<Movimiento> getHistorial() {
        return this.historial;
    }

    private void setHistorial(ArrayList<Movimiento> historial) {
        this.historial = historial;
    }

    public long getTiempoInicio() {
        return this.tiempoInicio;
    }

    private void setTiempoInicio(long tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }
    
    public void setAnterior(Tablero anterior){
        this.anterior = anterior;
    }
    public Tablero getAnterior(){
        return this.anterior;
    }
}
