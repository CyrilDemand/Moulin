package moulin;

public class Vecteur{
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
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }

    public boolean isCollinear(Vecteur v1){
        return ((this.getX()* v1.getY())-(v1.getX()*this.getY()))==0;
    }

    public boolean equals(Vecteur other){
        if(this==other)return true;
        if(this==null)return false;
        if(other==null)return false;
        if(this.getX()==other.getX() &&
        this.getY()==other.getY()) return true;
        return false;
    }

    @Override
    public String toString() {
        return "Vecteur{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
