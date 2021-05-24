package moulin;

public class Trap {
    private int turnLeft;
    private boolean isEnVie;

    public Trap(int turnLeft){
        this.turnLeft = turnLeft;
        this.isEnVie = true;
    }

    public void turnDecrementation(){
        if (!this.isFinished()){
            this.turnLeft--;
        }
    }

    public int getTurnLeft() {
        return turnLeft;
    }

    public boolean isFinished(){
        if (turnLeft==0)this.isEnVie=false;
        return turnLeft == 0;
    }
}
