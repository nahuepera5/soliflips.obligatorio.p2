package interfaz;

import dominio.Ficha;
import dominio.Tablero;
import dominio.Sistema;
import java.util.Scanner;

/**
 *
 * @author nahuel
 */
public class Juego {
    static Sistema sistema;
    static Print print = new Print();
    static boolean terminarJuego = false;
    static boolean terminarPartida = false;
    static Scanner in = new Scanner(System.in);

    
    public static void main(String[] args) {
        sistema = new Sistema();
        while(!terminarJuego){
            String opcion = print.menuPrincipal();

            switch (opcion) {
                case "A":
                    sistema.iniciarPorLectura();
                    print.tablero(sistema);
                    Juego.jugar();
                    break;
                case "B":
                    sistema.iniciarPredeterminado();
                    print.tablero(sistema);
                    Juego.jugar();
    //                this.startGame();
                    break;
                case "C":
                    // Se piden Fila, Columna y Nivel para generar un tablero aleatorio e iniciar la partida
//                    Scanner in = new Scanner(System.in);
                    int filas;
                    int columnas;
                    int nivel;
                    System.out.print("ingrese el numero de filas: ");
                    filas = in.nextInt();
                    System.out.print("ingrese el numero de columnas: ");
                    columnas = in.nextInt();
                    System.out.print("ingrese el numero de nivel: ");
                    nivel = in.nextInt();
                    in.nextLine();

                    sistema.iniciarAleatorio(filas, columnas, nivel);
                    print.tablero(sistema);
                    Juego.jugar();
    //                this.startGame();

                    break;
                case "D":
                    terminarJuego = true;
                    break;

            }
        }

    }

    public static void jugar() {
        terminarPartida = false;
//        printTablero(tab);
//        System.out.println(Sistema.esVictoria());
        while(!terminarPartida){
            String fila;
            String columna;
            
            //Pedir fila para hacer una jugada
            System.out.print("fila: ");
            fila = resolverOpciones(sistema.getTablero().getTablero().length);
            if(!fila.equals("INVALID_OPTION")){
                //Pedir columna para hacer una jugada
                System.out.print("columna: ");
                columna = resolverOpciones(sistema.getTablero().getTablero()[0].length);
                if(!fila.equals("INVALID_OPTION")){
                    if(fila.equals("-1") && columna.equals("-1")){
                        System.out.println("retroceder un paso");
                    }else{
                        sistema.cambiarSegunFichaEn(Integer.parseInt(fila) - 1, Integer.parseInt(columna) - 1);
                        print.tablero(sistema);
                        if(sistema.esVictoria()){
                            terminarPartida = true;
                            System.out.println("GANASTE!!!!");
                        }
                    }
                    
                }
                
            }
            
            
        }
//        printTablero2(tab, tab);
    }
    
    public static String resolverOpciones(int maxLength){
        String opcion = in.nextLine().toUpperCase();
        
        try{
            int opt = Integer.parseInt(opcion);
            if((opt <= 0 && opt != -1) || opt > maxLength ){
                opcion = "INVALID_OPTION";
                System.out.println("Mostrar aviso de que ingreso mal algun valor");
            }
        }catch(Exception e){
            switch(opcion){
                case "X":
                    System.out.println("Termino la partida");
                    terminarPartida = true;
                    break;
                case "H":
                    System.out.println("Muestro Historial");
                    break;
                case "S":
                    System.out.println("Muestro solucion");
                    break;
            }
            opcion = "INVALID_OPTION";
        }
        
        return opcion;
    }

    public static void manejarOpcionesIngresadas(String opc) {
        if (opc.equals("X")) {

        } else if (opc.equals("H")) {

        } else if (opc.equals("S")) {

        }
    }

    public static void printMenuMovimiento() {
//        System.o
    }

    public static void printTablero2(Tablero tab, Tablero tab2) {
        int rowLength = tab.getTablero().length;
        int colLength = tab.getTablero()[0].length;

        // Print column number
        System.out.print("  ");
        for (int i = 1; i <= colLength; i++) {
            System.out.print("  " + i + " ");
        }
        System.out.print("      ");
        System.out.print("   ");
        for (int i = 1; i <= colLength; i++) {
            System.out.print("  " + i + " ");
        }

        System.out.println();

        //Print rows
        for (int j = 1; j <= tab.getTablero().length; j++) {
            Ficha[] row = tab.getTablero()[j - 1];
            Ficha[] row2 = tab2.getTablero()[j - 1];

            System.out.print("  +");
            for (int i = 1; i <= colLength; i++) {
                System.out.print("---+");
            }
            System.out.print("      ");
            System.out.print("  +");
            for (int i = 1; i <= colLength; i++) {
                System.out.print("---+");
            }
            System.out.println();

            System.out.print(j + " |");
            for (int i = 0; i < row.length; i++) {
                System.out.print(" " + printColorText(row[i].getColor(), row[i].getSymbolo() + "") + "\033[0m |");
            }
            System.out.print("  =>  ");
            System.out.print(j + " |");
            for (int i = 0; i < row.length; i++) {
                System.out.print(" " + printColorText(row2[i].getColor(), row2[i].getSymbolo() + "") + "\033[0m |");
            }
            System.out.println();

        }

        System.out.print("  +");
        for (int i = 1; i <= colLength; i++) {
            System.out.print("---+");
        }
        System.out.print("      ");
        System.out.print("  +");
        for (int i = 1; i <= colLength; i++) {
            System.out.print("---+");
        }

        System.out.println();

    }

    public static String printColorText(String color, String text) {
        String res = "";
        switch (color.toUpperCase()) {
            case "AZUL":
                res = "\033[34m";
                break;
            case "ROJO":
                res = "\033[31m";
                break;
            default:
                res = "\033[0m";
        }

        res = res + text + "\033[0m";

        return res;
    }

}
