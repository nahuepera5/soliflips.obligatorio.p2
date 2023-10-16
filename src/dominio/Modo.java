package dominio;

/*
    Joaquin Bonora: 305371
    Nahuel Merlo:   302561 
*/

public class Modo {

    private String valor;
    public static Modo ALEATORIO = new Modo("ALEATORIO");
    public static Modo PREDETERMINADO = new Modo("PREDETERMINADO");
    public static Modo LECTURA = new Modo("LECTURA");

    Modo(String valor) {
        this.setValor(valor);
    }

    public String getValor() {
        return this.valor;
    }

    private void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object obj) {
        Modo m = (Modo) obj;
        return m.getValor().equals(this.getValor());
    }
}
