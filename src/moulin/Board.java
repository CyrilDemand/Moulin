package moulin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public void addNode(int x,int y,int id){
        this.nodes.add(new Node(x,y,id));
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

    @Override
    public String toString() {
        return "Board{" +
                "edges=" + edges +
                ", nodes=" + nodes +
                '}';
    }
    public void renderBoard(){

    }

    public static Board loadBoard(String chemin){
        Board res = new Board();
        try {
            String content = new String((Files.readAllBytes(Paths.get(chemin))));
            JSONObject o = new JSONObject(content);
            JSONArray nodes = o.getJSONArray("nodes");
            JSONArray edges = o.getJSONArray("edges");
            for (int i = 0;i<nodes.length();i++){
                res.addNode(nodes.getJSONArray(i).getInt(0),nodes.getJSONArray(i).getInt(1),nodes.getJSONArray(i).getInt(2));
            }
            for (int i = 0;i<edges.length();i++){
                res.addEdge(edges.getJSONArray(i).getInt(0),edges.getJSONArray(i).getInt(1));
            }
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

}
