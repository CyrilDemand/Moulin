package moulin;

import java.util.ArrayList;
import java.util.Scanner;

public class Piece implements Cloneable{
    private final Color color;
    private Node node;
    private int id;

    public Piece(Color color, int id){
        this.color = color;
        this.id = id;
    }

    public Piece(Piece piece){
        this(piece.getColor(),piece.getId());
        if (piece.getNode()!=null){
            this.node = new Node(piece.getNode());
            this.getNode().setPiece(this);
        }else{
            this.node = null;
        }

    }

    public Color getColor() {
        return color;
    }

    public Node getNode() {
        return node;
    }

    public int getId() {
        return id;
    }

    public void put(Node node){
        this.node=node;
        this.node.setPiece(this);
    }//(setter)

    public void setId(int id){ this.id=id;}

    public boolean isMovable(Board board){
        for (Node n:board.getNodes()) {
            if (board.isLinked(this.getNode().getId(), n.getId()) && n.isEmpty()){
                return true;
            }
        }
        return false;
    }

    public boolean move(Board board,int id){
        if(board.isLinked(this.getNode().getId(),id) && board.isLinked(id,this.getNode().getId()) && board.getNodeById(id).getPiece()==null){
            this.getNode().setPiece(null);
            this.put(board.getNodeById(id));
            this.getNode().setPiece(this);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", node=" + node.toString() +
                ", id=" + id +
                '}';
    }
}

