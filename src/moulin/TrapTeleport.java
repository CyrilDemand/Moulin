package moulin;

public class TrapTeleport extends Trap{
    private Node destination;

    public TrapTeleport(int turnsLeft, Node destination){
        super(turnsLeft);
        this.destination=destination;
    }
    public void setDestination(Node destination) {
        this.destination = destination;
    }
    public Node getDestination() {
        return destination;
    }
}
