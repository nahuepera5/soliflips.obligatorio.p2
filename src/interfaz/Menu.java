package interfaz;
import java.util.*;
import dominio.Tablero;
import dominio.Ficha;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Menu {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean salio = false;
        
        while(!salio){
            printMenu();
            String opt = in.nextLine().toUpperCase();
            
            switch (opt) { 
                case "A":
                    System.out.println("salio");
                    salio = true;
                 break;
                case "B":
                    System.out.println("salio");
                    salio = true;
                    Tablero predeterminado = new Tablero();
                    predeterminado.generarPredeterminado();
                    printTablero(predeterminado);
                 break;
                case "C" :
                    System.out.println("salio");
                    salio = true;
                 break;
                default: 
                    //Limpiar la consola
                    System.out.print("\n\n\n\n\n");
            }
             
        }
        
        String[][] tab = { { "/", "|", "\\" }, { "|", "|", "-" }, { "/", "|", "/"} };
    }
    
    public static void printMenu(){
        System.out.println("a) Tomar datos del archivo “datos.txt”.");
        System.out.println("b) Usar el tablero predefinido.");
        System.out.println("c) Usar un tablero al azar.");
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
            
            System.out.print("   +");
            for(int i = 1; i <= colLength; i++){
                System.out.print("---+");
            }
            System.out.println();
            
            System.out.print(j + "  |");
            for(int i = 0; i < row.length; i++){
                System.out.print(" " + printColorText(row[i].getColor(), row[i].getSymbolo() + "") + "\033[0m |");
            }
            System.out.println();
            
        }
        
        System.out.print("   +");
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