package moulin;

public class Node {
    private int x;
    private int y;

    private int id;
    private static int counter;

    Node(int x,int y){
        this.x=x;
        this.y=y;
        this.id=Node.counter;
        counter++;
    }
}
