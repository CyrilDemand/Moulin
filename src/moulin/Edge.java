package moulin;


/**
 * class used to represent the edges that link the Nodes together on the board
 */
public class Edge {
    private Node start;
    private Node end;
    private Trap trap;


    /**
     * Creates an instance of an edge linking two Nodes together
     * @param start starting node
     * @param end ending node
     */
    public Edge(Node start,Node end){
        this.start=start;
        this.end=end;
    }

    /**
     * Creates a copy of the edge
     * @param edge the edge to copy
     */
    public Edge(Edge edge){
        this(new Node(edge.getStart()), new Node(edge.getEnd()));
    }

    /**
     * gets the starting node
     * @return the starting node
     */
    public Node getStart(){
        return this.start;
    }

    /**
     * gets the ending node
     * @return the ending node
     */
    public Node getEnd(){
        return this.end;
    }

    /**
     * changes the starting node
     * @param start the new starting node for the edge
     */
    public void setStart(Node start){
        this.start=start;
    }

    /**
     * changes the ending node
     * @param end the new ending node for the edge
     */
    public void setEnd(Node end){
        this.end=end;
    }

    /**
     * activates the trap on the edge
     * @param trap the trap
     */
    public void setTrap(Trap trap){
        this.trap=trap;
    }

    /**
     * gets the trap that is currently on the edge
     * @return the trap on that edge
     */
    public Trap getTrap(){
        return this.trap;
    }

    /**
     * checks if the edge is currently trapped or not
     * @return true if the edge is trapped, false otherwise
     */
    public boolean isTrapped(){
        return this.getTrap()!=null && this.getTrap().getTurnsLeft()>0;
    }

    /**
     * checks if two edges are equal (same start and same end)
     * @param other the other trap
     * @return true if the two edge are equal, false otherwise
     */
    public boolean equals(Edge other){
        if(this==other)return true;
        if(this==null)return false;
        if(other==null)return false;
        if(this.getStart().equals(other.getStart()) &&
                this.getEnd().equals(other.getEnd()))return true;
        return false;
    }


    /**
     * creates a text version of the edges
     * @return the text with the id of the starting and ending node
     */
    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start.getId() +
                ", end=" + end.getId() +
                '}';
    }

}
