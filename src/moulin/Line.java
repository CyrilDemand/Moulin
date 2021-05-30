package moulin;

import java.util.ArrayList;
import java.util.List;

/**
 * class used to represent the way player can put their pieces in order to win
 */

public class Line {
    private ArrayList<Node> nodes;

    /**
     * Create a new empty line
     */
    public Line(){
        this.nodes = new ArrayList<>();
    }

    /**
     * creates a new line from a series of Node
     * @param nodes the nodes used in line
     */
    public Line(ArrayList<Node> nodes){
        this();
        this.nodes = nodes;
    }

    /**
     * adds a new node to the line
     * @param node the node to add
     */
    public void addNode(Node node){
        this.nodes.add(node);
    }

    /**
     * gets the node that make up the line
     * @return the list of nodes
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     * gets the text version of the line
     * @return the text version of the line with the ids of all the nodes in that line
     */
    @Override
    public String toString() {
        return "Line{" +
                "nodes=" + nodes +
                '}';
    }

    /**
     * return the first node that is not in the line
     * @param other the list of nodes to compare
     * @return the node, null if all the nodes are in the line
     */

    public Node whichIsNotIn(List<Node> other){
        for (Node n:this.getNodes()) {
            if (!other.contains(n))return n;
        }
        return null;
    }
}
