package interfaz;

/*
    Joaquin Bonora: 305371
    Nahuel Merlo:   302561 
*/

import dominio.Ficha;
import dominio.Tablero;
import dominio.Sistema;
import java.util.*;

public class Print {
    Sistema sistema;
    
    /*
    Print: En esta clase se definen la mayoria la ui, como puede ser: El tablero, avisos y menus.
    */
    
    public Print(Sistema sistema){
        this.setSistema(sistema);
    }

    public String menuPrincipal() {
        String res = "";
        ArrayList<String> opciones = new ArrayList<String>(Arrays.asList("A", "B", "C", "D"));
        boolean tengoUnaOpcionValida = false;
        Scanner in = new Scanner(System.in);

        while (!tengoUnaOpcionValida) {
            System.out.println("a) Tomar datos del archivo \"datos.txt\".");
            System.out.println("b) Usar el tablero predefinido.");
            System.out.println("c) Usar un tablero al azar.");
            System.out.println("D) Salir.");
            
            res = in.nextLine().toUpperCase();
            if (opciones.contains(res)) {
                tengoUnaOpcionValida = true;
            } else {
                this.warning("Opcion incorrecta, por favor vuelva a intentar");
            }
        }
        return res;
    }

    public void tablero() {
        Tablero tab = this.getSistema().getTablero();
        int rowLength = tab.getTablero().length;
        int colLength = tab.getTablero()[0].length;

        // Print column number
        System.out.print("   ");
        for (int i = 1; i <= colLength; i++) {
            System.out.print("  " + i + " ");
        }
        System.out.println();

        //Print rows
        for (int j = 1; j <= tab.getTablero().length; j++) {
            Ficha[] row = tab.getTablero()[j - 1];

            System.out.print("  +");
            for (int i = 1; i <= colLength; i++) {
                System.out.print("---+");
            }
            System.out.println();

            System.out.print(j + " |");
            for (int i = 0; i < row.length; i++) {
                System.out.print(" " + printColorText(row[i].getColor(), row[i].getSymbolo() + "") + "\033[0m |");
            }
            System.out.println();

        }

        System.out.print("  +");
        for (int i = 1; i <= colLength; i++) {
            System.out.print("---+");
        }

        System.out.println();

    }
    
        public void tableroAnterior() {
        Tablero tab = this.getSistema().getAnterior();
        int rowLength = tab.getTablero().length;
        int colLength = tab.getTablero()[0].length;

        // Print column number
        System.out.print("   ");
        for (int i = 1; i <= colLength; i++) {
            System.out.print("  " + i + " ");
        }
        System.out.println();

        //Print rows
        for (int j = 1; j <= tab.getTablero().length; j++) {
            Ficha[] row = tab.getTablero()[j - 1];

            System.out.print("  +");
            for (int i = 1; i <= colLength; i++) {
                System.out.print("---+");
            }
            System.out.println();

            System.out.print(j + " |");
            for (int i = 0; i < row.length; i++) {
                System.out.print(" " + printColorText(row[i].getColor(), row[i].getSymbolo() + "") + "\033[0m |");
            }
            System.out.println();

        }

        System.out.print("  +");
        for (int i = 1; i <= colLength; i++) {
            System.out.print("---+");
        }

        System.out.println();

    }
        
    public void tableroAnteriorYActual() {
        Tablero tab2 = this.getSistema().getTablero();
        Tablero tab = this.getSistema().getAnterior();
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
    
    public void warning(String text){
        int len = text.length();
        String color = "rojo";
        
        System.out.print(this.printColorText(color, "+"));
        for(int i = 0; i < len + 2; i++){
            System.out.print(this.printColorText(color, "-"));
        }
        System.out.print(this.printColorText(color, "+"));
        System.out.println();
        System.out.print(this.printColorText(color, "| "));
        System.out.print(this.printColorText(color, text));
        System.out.print(this.printColorText(color, " |"));
        System.out.println();
        System.out.print(this.printColorText(color, "+"));
        for(int i = 0; i < len + 2; i++){
            System.out.print(this.printColorText(color, "-"));
        }
        System.out.print(this.printColorText(color, "+"));
        System.out.println();
    }
    
    public void success(String text){
        int len = text.length();
        String color = "verde";
        
        System.out.print(this.printColorText(color, "+"));
        for(int i = 0; i < len + 2; i++){
            System.out.print(this.printColorText(color, "-"));
        }
        System.out.print(this.printColorText(color, "+"));
        System.out.println();
        System.out.print(this.printColorText(color, "| "));
        System.out.print(this.printColorText(color, text));
        System.out.print(this.printColorText(color, " |"));
        System.out.println();
        System.out.print(this.printColorText(color, "+"));
        for(int i = 0; i < len + 2; i++){
            System.out.print(this.printColorText(color, "-"));
        }
        System.out.print(this.printColorText(color, "+"));
        System.out.println();
    }
    
    public void info(String text){
        int len = text.length();
        String color = "azul";
        
        System.out.print(this.printColorText(color, "+"));
        for(int i = 0; i < len + 2; i++){
            System.out.print(this.printColorText(color, "-"));
        }
        System.out.print(this.printColorText(color, "+"));
        System.out.println();
        System.out.print(this.printColorText(color, "| "));
        System.out.print(this.printColorText(color, text));
        System.out.print(this.printColorText(color, " |"));
        System.out.println();
        System.out.print(this.printColorText(color, "+"));
        for(int i = 0; i < len + 2; i++){
            System.out.print(this.printColorText(color, "-"));
        }
        System.out.print(this.printColorText(color, "+"));
        System.out.println();
    }

    public String printColorText(String color, String text) {
        String res = "";
        switch (color.toUpperCase()) {
            case "AZUL":
                res = "\033[34m";
                break;
            case "ROJO":
                res = "\033[31m";
                break;
            case "VERDE":
                res = "\u001B[32m";
                break;
            default:
                res = "\033[0m";
        }

        res = res + text + "\033[0m";

        return res;
    }
    
    private Sistema getSistema(){
        return this.sistema;
    }
    
    private void setSistema(Sistema sistema){
        this.sistema = sistema;
    }

}
