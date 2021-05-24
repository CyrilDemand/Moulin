package moulin;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private ArrayList<Node> nodes;

    public Line(){
        this.nodes = new ArrayList<>();
    }

    public Line(ArrayList<Node> nodes){
        this();
        this.nodes = nodes;
    }

    public void addNode(Node node){
        this.nodes.add(node);
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    @Override
    public String toString() {
        return "Line{" +
                "nodes=" + nodes +
                '}';
    }

    public Node whichIsNotIn(List<Node> other){
        for (Node n:this.getNodes()) {
            if (!other.contains(n))return n;
        }
        return null;
    }
}
