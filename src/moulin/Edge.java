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
    public void setStart(Node start){
        this.start=start;
    }
    public void setEnd(Node end){
        this.end=end;
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
