package moulin;

public class TrapTeleport extends Trap{
    private Node destination;

    /**
     * Creates a TrapTeleport Class
     * @param turnsLeft amount of turns for the trap
     * @param destination destination of the teleporter
     */
    public TrapTeleport(int turnsLeft, Node destination){
        super(turnsLeft);
        this.destination=destination;
    }

    /**
     * sets the destination of the teleporter to a given node
     * @param destination destination of the teleporter
     */
    public void setDestination(Node destination) {
        this.destination = destination;
    }

    /**
     * gets the destination of the trap
     * @return the node where the trap leads
     */
    public Node getDestination() {
        return destination;
    }

    @Override
    /**
     * Creates a string version of the trap
     */
    public String toString() {
        return "TrapTeleport{" +
                "destination=" + destination +
                '}';
    }
}
