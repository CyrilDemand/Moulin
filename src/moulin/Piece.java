package moulin;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class used to represent the pieces of the players on the board
 */

public class Piece{
    private final Color color;
    private Node node;
    private int id;


    /**
     * create a new piece
     * @param color the color of the piece
     * @param id the id of the piece
     */
    public Piece(Color color, int id){
        this.color = color;
        this.id = id;
    }

    /**
     * Creates a copy of the piece
     * @param piece the piece to copy
     */
    public Piece(Piece piece){
        this(piece.getColor(),piece.getId());
        if (piece.getNode()!=null){
            this.node = new Node(piece.getNode());
            this.getNode().setPiece(this);
        }else{
            this.node = null;
        }

    }

    /**
     * gets the color of the piece
     * @return the color of the piece
     */
    public Color getColor() {
        return color;
    }

    /**
     * gets the node where the piece is
     * @return the node
     */
    public Node getNode() {
        return node;
    }

    /**
     * gets the id of the piece
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * changes the node where the piece is
     * @param node the new node
     * @return true if the piece could be moved
     */
    public boolean put(Node node){
        if (node==null)return false;
        if(node.getPiece()==null){
            this.node=node;
            this.node.setPiece(this);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * sets the id of the piece
     * @param id the new id
     */
    public void setId(int id){ this.id=id;}

    /**
     * checks if the piece can move anywhere else on the board
     * @param board the board
     * @return true if the piece can be moved, false otherwise
     */
    public boolean isMovable(Board board){
        for (Node n:board.getNodes()) {
            if (board.isLinked(this.getNode().getId(), n.getId()) && n.isEmpty()){
                return true;
            }
        }
        return false;
    }

    /**
     * moves the piece to a new location
     * @param board the board
     * @param id id of the node you want to put the piece on
     * @return true if the piece could move, false otherwise
     */
    public boolean move(Board board,int id){
        if((board.isLinked(this.getNode().getId(),id) || board.isLinked(id,this.getNode().getId())) && board.getNodeById(id).getPiece()==null){
            Node destination=board.getNodeById(id);

            this.getNode().setPiece(null);
            this.put(board.getNodeById(id));
            this.getNode().setPiece(this);

            if (destination.isTrapped()){
                return this.move(board,((TrapTeleport)destination.getTrap()).getDestination().getId());
            }

            return true;
        }
        return false;
    }

    /**
     * gets the text version of the piece with the color and id
     * @return the text version of the piece
     */
    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", node=" + node.toString() +
                ", id=" + id +
                '}';
    }
}

