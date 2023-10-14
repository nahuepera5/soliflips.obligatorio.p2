package interfaz;

import dominio.Ficha;
import dominio.Tablero;
import dominio.Movimiento;
import dominio.Sistema;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nahuel
 */
public class Juego {
    static Sistema sistema;
    static Print print;
    static boolean terminarJuego = false;
    static boolean terminarPartida = false;
    static Scanner in = new Scanner(System.in);

    
    public static void main(String[] args) {
        sistema = new Sistema();
        print = new Print(sistema);
        while(!terminarJuego){
            String opcion = print.menuPrincipal();

            switch (opcion) {
                case "A":
                    sistema.iniciarPorLectura();
                    print.tablero();
                    Juego.jugar();
                    break;
                case "B":
                    sistema.iniciarPredeterminado();
                    print.tablero();
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
                    print.tablero();
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
                        print.tableroAnterior();
                        sistema.retroceder();
                    }else{
                        Movimiento movimiento = new Movimiento(Integer.parseInt(fila) - 1, Integer.parseInt(columna) - 1);
                        sistema.moverEn(movimiento);
                        print.tableroAnteriorYActual();
                        if(sistema.esVictoria()){
                            terminarPartida = true;
                            String successText;
                            if(sistema.getTablero().getNivel() < 9){
                                successText = "Ganaste, felicitaciones, ahora te invitamos a subir la dificultad " + (sistema.getTablero().getNivel() + 1);
                            }else{
                                successText = "Ganaste, queremos felicitarte por resolver un tablero con la maxima dificukltad";
                            }
                            print.success(successText);
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
                    terminarPartida = true;
                    break;
                case "H":
                    ArrayList<Movimiento> historial = sistema.getHistorial();
                    if(historial.isEmpty()){
                        print.warning("El historial esta vacio");
                    }else{
                        print.info("Historial");
                    }
                    for(int i = 0; i < sistema.getHistorial().size(); i++){
                        System.out.print("Movimiento " + ( i + 1 ) + ": ");
                        System.out.println(sistema.getHistorial().get(i));   
                    }
                    System.out.println();
                    break;
                case "S":
                    print.info("Solucion");
                    ArrayList<Movimiento> solucion = sistema.getListaSoluciones();
                    for(int i = 0; i < solucion.size(); i++){
                        System.out.print("Movimiento " + ( i + 1 ) + ": ");
                        System.out.println(solucion.get(i));   
                    }
                    System.out.println();
                    break;
            }
            opcion = "INVALID_OPTION";
        }
        
        return opcion;
    }

    public static void printMenuMovimiento() {
//        System.o
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
