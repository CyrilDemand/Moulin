package moulin;

public class TrapEdge extends Trap{
    private Edge edge;
    public TrapEdge(int turnLeft, Edge edge) {
        super(turnLeft);
        this.edge = edge;
        this.edge.setEnVie(false);
    }

    public void finish(){
        this.edge.setEnVie(true);
        this.edge=null;
    }
}
