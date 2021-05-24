package moulin;

public class Edge {
    private Node start;
    private Node end;
    private Trap trap;

    public Edge(Node start,Node end){
        this.start=start;
        this.end=end;
    }
    public Edge(Edge edge){
        this(new Node(edge.getStart()), new Node(edge.getEnd()));
    }

    public Node getStart(){
        return this.start;
    }
    public Node getEnd(){
        return this.end;
    }
    public void setStart(Node start){
        this.start=start;
    }
    public void setEnd(Node end){
        this.end=end;
    }
    public void setTrap(Trap trap){
        this.trap=trap;
    }
    public Trap getTrap(){
        return this.trap;
    }
    public boolean isTrapped(){
        return this.getTrap()!=null && this.getTrap().getTurnsLeft()>0;
    }

    public boolean equals(Edge other){
        if(this==other)return true;
        if(this==null)return false;
        if(other==null)return false;
        if(this.getStart().equals(other.getStart()) &&
                this.getEnd().equals(other.getEnd()))return true;
        return false;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start.getId() +
                ", end=" + end.getId() +
                '}';
    }

}
