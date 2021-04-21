package moulin;

import java.util.ArrayList;

public class Board {
    private ArrayList<Edge> edges;
    private ArrayList<Node> nodes;

    Board() {
        this.edges = new ArrayList<Edge>();
        this.nodes = new ArrayList<Node>();
    }

    public void addNode(int x,int y){
        this.nodes.add(new Node(x,y));
    }

    public void addEdge(int idStart, int idEnd){
        this.addEdge(this.getNodeById(idStart),this.getNodeById(idEnd));
    }

    public void addEdge(Node start,Node end){
        this.edges.add(new Edge(start,end));
    }

    private Node getNodeById(int id){
        for (Node n:this.nodes){
            if (n.getId()==id){
                return n;
            }
        }
        throw new Error("Il n'y a pas de Node avec cet id sur ce board");
    }
}
