package moulin;

import java.util.ArrayList;

public class Node{
    private int x;
    private int y;
    private int id;
    private static int counter=1;
    private Piece piece;
    private TrapTeleport trap;

    /**
     * Creates a Node class
     * @param x
     * @param y
     */
    public Node(int x,int y){
        this.x=x;
        this.y=y;
        this.id=Node.counter;
        Node.counter++;
        this.piece=null;
    }

    /**
     * Creates a Node class
     * @param node
     */
    public Node(Node node){
        this(node.getX(),node.getY());
        this.setPiece(node.getPiece());
    }

    /**
     * Creates a Node class
     * @param x
     * @param y
     * @param id
     */
    public Node(int x, int y, int id){
        this(x,y);
        this.id=id;
    }

    /**
     * Gets the class' X value
     * @return int
     */
    public int getX(){
        return this.x;
    }

    /**
     * Gets the class' Y value
     * @return int
     */
    public int getY(){
        return this.y;
    }

    /**
     * Gets the class' Id value
     * @return int
     */

    public int getId(){
        return this.id;
    }

    /**
     * Gets the class' Counter value
     * @return int
     */

    public int getCounter() {
        return Node.counter;
    }

    /**
     * Gets the class' Piece value
     * @return Piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Sets the class' X to the given value
     * @param x
     */
    public void setX(int x){ this.x=x;}

    /**
     * Sets the class' Y to the given value
     * @param y
     */
    public void setY(int y){ this.y=y;}

    /**
     * Sets the class' Id to the given value
     * @param id
     */
    public void setId(int id){ this.id=id;}

    /**
     * Resets the Class' counter to the value 1
     */
    public static void resetCounter(){
        Node.counter=1;
    }

    /**
     * Sets the class' Piece to the given Piece
     * @param piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Sets the class' Trap to the given Trap
     * @param trap
     */
    public void setTrap(TrapTeleport trap){
        this.trap=trap;
    }

    /**
     * Returns the Class' trap
     * @return
     */
    public TrapTeleport getTrap(){
        return this.trap;
    }

    /**
     * Returns true if there's a teleporter on the node
     * @return true if there's a teleporter
     */
    public boolean isTrapped(){
        return this.getTrap()!=null && this.getTrap().getTurnsLeft()>0;
    }

    /**
     * Displays a node into a String
     * @return
     */

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", id=" + id +
                '}';
    }

    /**
     * checks if two nodes are the same
     * @param other other node to compare
     * @return
     */
    public boolean equals(Node other){
        if(this==other)return true;
        if(this==null)return false;
        if(other==null)return false;
        if(this.getId()==other.getId() &&
                this.getX()==other.getX() &&
                this.getY()==other.getY())return true;
        return false;
    }

    /**
     * checks if the node is empty or not
     * @return true if it is
     */
    public boolean isEmpty(){
        return this.getPiece()==null;
    }

    /**
     * checks if the current node is linked
     * @param board
     * @return an arrayList of the linked nodes
     */
    public ArrayList<Node> isLinkedWith(Board board){
        ArrayList<Node> res = new ArrayList<>();
        for (Node n:board.getNodes()) {
            if (board.isLinked(this.getId(),n.getId())){
                res.add(n);
            }
        }
        return res;
    }
}
