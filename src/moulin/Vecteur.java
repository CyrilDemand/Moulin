package moulin;

public class Vecteur {
    int x;
    int y;

    public Vecteur(int x,int y){
        this.x=x;
        this.y=y;
    }

    public Vecteur(int xa,int xb,int ya,int yb){
        this.x=xb-xa;
        this.y=yb-ya;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isCollinear(Vecteur v1){
        return (this.getX()* v1.getY())-(v1.getX()*this.getY())==0;
    }
}
