package moulin;

public class Edge {
    private Node start;
    private Node end;

    public Edge(Node start,Node end){
        this.start=start;
        this.end=end;
    }

    public Node getStart(){
        return this.start;
    }
    public Node getEnd(){
        return this.end;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start.getId() +
                ", end=" + end.getId() +
                '}';
    }

    public boolean equals(Edge other){
        if(this==other)return true;
        if(this==null)return false;
        if(other==null)return false;
        if(this.getStart()==other.getStart() &&
                this.getEnd()==other.getEnd())return true;
        return false;
    }
}
