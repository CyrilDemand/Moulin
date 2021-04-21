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

    public static void main(String[] args) {
        Board test = new Board();
        String first = "C:\\Users\\cyril\\IdeaProjects\\Moulin\\ressources\\map.json";
        try {
            String content = new String((Files.readAllBytes(Paths.get(first))));
            JSONObject o = new JSONObject(content);
            JSONArray nodes = o.getJSONArray("nodes");
            JSONArray edges = o.getJSONArray("edges");
            for (int i = 0;i<nodes.length();i++){
                test.addNode(nodes.getJSONArray(i).getInt(0),nodes.getJSONArray(i).getInt(1),nodes.getJSONArray(i).getInt(2));
            }
            for (int i = 0;i<edges.length();i++){
                test.addEdge(edges.getJSONArray(i).getInt(0),edges.getJSONArray(i).getInt(1));
            }
        }
             catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        for (Node n:test.nodes){
            System.out.println(n);
        }
        for (Edge e:test.edges){
            System.out.println(e);
        }
    }
}
