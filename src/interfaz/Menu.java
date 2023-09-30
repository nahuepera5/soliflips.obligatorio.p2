package interfaz;
import java.util.*;
import dominio.Tablero;

public class Menu {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Tablero tablero = new Tablero();
        boolean salio = false;
        Juego juego = new Juego();
        
        while(!salio){
            printMenu();
            String opt = in.nextLine().toUpperCase();
            
            switch (opt) { 
                case "A":
                    salio = true;
                    tablero.generarPorLectura();
//                    printTablero(tablero);
                    juego.startGame(tablero);
                 break;
                case "B":
                    salio = true;
                    tablero.generarPredeterminado();
//                    printTablero(tablero);
                    juego.startGame(tablero);
                 break;
                case "C" :
                    salio = true;
                    int filas;
                    int columnas;
                    int nivel;
                    System.out.print("ingrese el numero de filas: ");
                    filas = in.nextInt();
                    System.out.print("ingrese el numero de columnas: ");
                    columnas = in.nextInt();
                    System.out.print("ingrese el numero de nivel: ");
                    nivel = in.nextInt();
                    
                    tablero.generarAleatorio(filas, columnas, nivel);
                    
//                    printTablero(tablero);
                    juego.startGame(tablero);
                 break;
                default: 
                    //Limpiar la consola
                    System.out.print("\n\n\n\n\n");
            }
             
        }
    }
    
    public static void printMenu(){
        System.out.println("a) Tomar datos del archivo \"datos.txt\".");
        System.out.println("b) Usar el tablero predefinido.");
        System.out.println("c) Usar un tablero al azar.");
    }

}
