package moulin;

public class Trap {
    private int turnsLeft;

    public Trap(int turnsLeft){
        this.turnsLeft=turnsLeft;
    }

    public int getTurnsLeft(){
        return this.turnsLeft;
    }

    public void setTurnsLeft(int turnsLeft){
        this.turnsLeft=turnsLeft;
    }

    public void nextTurn(){
        this.turnsLeft--;
    }
}
