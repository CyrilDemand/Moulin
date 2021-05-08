package moulin;

import java.util.ArrayList;
import java.util.Scanner;

public class Piece {
    private final Color color;

    private Node node;

    public Piece(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void put(Node node){
        this.node=node;
        this.node.setPiece(this);
    }

    public Node getNode() {
        return node;
    }

}
