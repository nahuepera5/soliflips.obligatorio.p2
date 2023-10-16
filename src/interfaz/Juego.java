package interfaz;

import dominio.Movimiento;
import dominio.Sistema;
import java.util.ArrayList;
import java.util.Scanner;

public class Juego {
    /*
    Juego: en esta clase se encuentra la funcion main y se encarga de definir toda la jugabilidad.
    */
    static Sistema sistema;
    static Print print;
    static Scanner in = new Scanner(System.in);
    
    //Mientras terminarJuego se encuentre en false el programa va a estar corriendo.
    static boolean terminarJuego = false;
    
    //Cuando se inicie una partida (es decir, tengamos un tablero sobre el cual jugar y podamos ingresar movimientos)
    //La partida va a continuar mientras terminarPartida se encuentre como false.
    static boolean terminarPartida = false;
    
    public static void main(String[] args) {
        while(!terminarJuego){
            sistema = new Sistema();
            print = new Print(sistema);
            String opcion = print.menuPrincipal();

            switch (opcion) {
                case "A":
                    sistema.iniciarPorLectura();
                    Juego.jugar();
                    break;
                case "B":
                    sistema.iniciarPredeterminado();
                    Juego.jugar();;
                    break;
                case "C":
                    // Se piden Fila, Columna y Nivel para generar un tablero aleatorio e iniciar la partida
                    boolean tengoUnaOpcionValida = false;

                    while(!tengoUnaOpcionValida){
                        int filas;
                        int columnas;
                        int nivel;
                        int nivelMaximoSegunDimensiones;

                        System.out.print("ingrese el numero de filas: ");
                        filas = in.nextInt();
                        if(filas < 3 || filas > 9){
                            print.warning("Cuidado! seleccione un numero entre 3 y 9 para las filas");
                            continue;
                        }
                        System.out.print("ingrese el numero de columnas: ");
                        columnas = in.nextInt();
                        if(columnas < 3 || columnas > 9){
                            print.warning("Cuidado! seleccione un numero entre 3 y 9 para las filas");
                            continue;
                        }

                        nivelMaximoSegunDimensiones = filas * columnas;

                        System.out.print("ingrese el numero de nivel: ");
                        nivel = in.nextInt();
                        if( nivel > nivelMaximoSegunDimensiones || nivel < 1 || nivel > 8){
                            print.warning("Cuidado! no seleccionaste un nivel valido para este tablero. Recuerda que los niveles validos son de 1 a 8");
                        }else{
                            tengoUnaOpcionValida = true;
                            sistema.iniciarAleatorio(filas, columnas, nivel);
                        }
                    }
                    in.nextLine();
                    Juego.jugar();

                    break;
                case "D":
                    terminarJuego = true;
                    break;

            }
        }

    }

    public static void jugar() {
        print.tablero();
        terminarPartida = false;
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
                    if(fila.equals("-1")){
                        if(columna.equals("-1")){
                            print.tableroAnterior();
                            sistema.retroceder();
                        }
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
                                successText = "Ganaste, queremos felicitarte por resolver un tablero con la maxima dificultad a";
                            }
                            print.success(successText);
                        }
                    }   
                }
            }
        }
    }
    
    public static String resolverOpciones(int maxLength){
        String opcion = in.nextLine().toUpperCase();
        
        //Revisa si se ingreso un numero o una cadena
        try{
            //Si es un numero se revisa si es una posicion valida para el tablero
            int opt = Integer.parseInt(opcion);
            if((opt <= 0 && opt != -1) || opt > maxLength ){
                opcion = "INVALID_OPTION";
                print.warning("No se encuentra la posicion en el tablero, por favor vuelva a ingresar el movimiento");
            }
        }catch(Exception e){
            //En el caso de que se ingrese una cadena se revisa si esta dentro de las opciones validas y en caso correcto se ejecuta la funcion correspondiente.
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
            //cuando juego recibe INVALID_OPTION no hace ningun movimiento y vuelve a pedir fila y columna.
            opcion = "INVALID_OPTION";
        }
        
        return opcion;
    }
}
