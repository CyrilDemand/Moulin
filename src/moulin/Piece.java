package moulin;

public class Piece {
    private final Color color;

    private Node node;

    Piece(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void put(Node node){
        this.node=node;
    }

    public Node getNode() {
        return node;
    }

}
