package moulin;

import java.util.ArrayList;

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
}
