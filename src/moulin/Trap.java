package moulin;

public class Trap {
    private int turnsLeft;

    /**
     * Creates a Trap class
     * @param turnsLeft amount of turns given to a trap
     */
    public Trap(int turnsLeft){
        this.turnsLeft=turnsLeft;
    }

    /**
     * returns the amount of turns left
     * @return
     */
    public int getTurnsLeft(){
        return this.turnsLeft;
    }

    /**
     * sets the amount of turns left
     * @param turnsLeft amount of turns desired
     */
    public void setTurnsLeft(int turnsLeft){
        this.turnsLeft=turnsLeft;
    }

    /**
     * Switches to the next turn and reduces the amount of turns remaining
     */
    public void nextTurn(){
        this.turnsLeft--;
    }
}
