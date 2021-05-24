package moulin;

public class TrapNode extends Trap{
    public Node start;
    public Node destination;

    public TrapNode(int turnLeft,Node start, Node node,Board board) {
        super(turnLeft);
        this.destination = node;
        this.start = start;
        moveToDestination(board, start.getPiece());
    }

    public boolean moveToDestination(Board board,Piece piece){
        return piece.move(board,destination.getId());
    }
}