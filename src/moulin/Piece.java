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

    public boolean move(Board board,int id){
        if(board.isLinked(this.getNode().getId(),id) && board.getNodeById(id).getPiece()==null){
            System.out.println(this.getNode().getPiece());
            this.getNode().setPiece(null);
            System.out.println(this.getNode().getPiece());
            this.put(board.getNodeById(id));
            this.getNode().setPiece(this);
            return true;
        }
        System.out.println(false);
        return false;
    }
}
