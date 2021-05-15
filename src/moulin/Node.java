package moulin;

import java.util.ArrayList;

public class Node {
    private int x;
    private int y;
    private int id;
    private static int counter=1;
    private Piece piece;

    public Node(int x,int y){
        this.x=x;
        this.y=y;
        this.id=Node.counter;
        Node.counter++;
        this.piece=null;
    }

    public Node(int x, int y, int id){
        this(x,y);
        this.id=id;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getId(){
        return this.id;
    }

    public int getCounter() {
        return Node.counter;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setX(int x){ this.x=x;}

    public void setY(int y){ this.y=y;}

    public void setId(int id){ this.id=id;}

    public static void resetCounter(){
        Node.counter=1;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", id=" + id +
                '}';
    }

    public boolean equals(Node other){
        if(this==other)return true;
        if(this==null)return false;
        if(other==null)return false;
        if(this.getId()==other.getId() &&
        this.getX()==other.getX() &&
        this.getY()==other.getY())return true;
        return false;
    }

    public boolean isEmpty(){
        return this.getPiece()==null;
    }

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