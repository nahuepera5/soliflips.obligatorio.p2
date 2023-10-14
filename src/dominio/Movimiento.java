package dominio;

public class Movimiento {

    private int x;
    private int y;

    public Movimiento(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }
    
    @Override
    public boolean equals(Object o){
        Movimiento mov = (Movimiento) o;
        return this.x == mov.getX() && this.y == mov.getY();
    }
}
