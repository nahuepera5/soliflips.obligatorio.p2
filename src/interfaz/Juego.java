package interfaz;
import dominio.Ficha;
import dominio.Tablero;
import java.util.Scanner;

/**
 *
 * @author nahuel
 */
public class Juego {
    	
    public static void startGame(Tablero tab){
        Scanner in = new Scanner(System.in);
        printTablero(tab);
        System.out.println(tab.checkVictoria());
        boolean termino = false;
        while(!termino){
            String fila;
            String columna;
            System.out.print("fila: ");
            fila = in.nextLine();
            System.out.print("columna: ");
            columna = in.nextLine();
            tab.hacerMovimiento(Integer.parseInt(fila) - 1, Integer.parseInt(columna) - 1);
            if(tab.checkVictoria()){
                termino = true;
                printTablero(tab);
                System.out.println("GANASTE!!!!");
            }else{
                printTablero(tab);
            }
        }
    }

    public static void manejarOpcionesIngresadas( String opc ){
        if(opc.equals("X")){
            
        }else if(opc.equals("H")){
            
        }else if(opc.equals("S")){
            
        }
    }
    
    public static void printMenuMovimiento(){
//        System.o
    }
    
    public static void printTablero(Tablero tab){
        int rowLength = tab.getTablero().length;
        int colLength = tab.getTablero()[0].length;
        
        // Print column number
        System.out.print("   ");
        for(int i = 1; i <= colLength; i++){
            System.out.print("  " + i + " ");
        }
        System.out.println();

        //Print rows
        for(int j = 1; j <= tab.getTablero().length; j++){
            Ficha[] row = tab.getTablero()[j - 1];
            
            System.out.print("  +");
            for(int i = 1; i <= colLength; i++){
                System.out.print("---+");
            }
            System.out.println();
            
            System.out.print(j + " |");
            for(int i = 0; i < row.length; i++){
                System.out.print(" " + printColorText(row[i].getColor(), row[i].getSymbolo() + "") + "\033[0m |");
            }
            System.out.println();
            
        }
        
        System.out.print("  +");
        for(int i = 1; i <= colLength; i++){
            System.out.print("---+");
        }
        
        System.out.println();
        
    }
    
    public static String printColorText(String color, String text){
        String res = "";
        switch(color.toUpperCase()){
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
