package moulin;

public class Node {
    private int x;
    private int y;

    private int id;
    private static int counter;

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

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
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

}