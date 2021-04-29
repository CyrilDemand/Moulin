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

    public static void main(String[] args) {
        Board board=Board.loadBoard("ressources\\map.json");
        board.getNodeById(20).setPiece(new Piece(Color.BLEU));
        board.render();
    }

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

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public Node getNodeById(int id){
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

    public boolean isLinked(int a, int b){
        for (Edge e:this.getEdges()) {
            if (e.getStart().equals(this.getNodeById(a))&&e.getEnd().equals(this.getNodeById(b))){
                return true;
            }
        }
        return false;
    }

    public void render() {
        int nodeSize = 4;
        int margeSize = 1;

        int maxX = 7, maxY = 7;
        int unit=(nodeSize+margeSize);
        int width=unit*maxX;
        int height=unit*maxY;

        char[][] pixels = new char[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x][y]=' ';
            }
        }

        for (Node node : this.nodes){
            for (int y = 0; y < nodeSize; y++) {
                for (int x = 0; x < nodeSize; x++) {
                    pixels[node.getX()*unit+x][node.getY()*unit+y]='+';
                }
            }
            if (node.getPiece()!=null){
                pixels[(node.getX() * unit + ((int) nodeSize / 2))][node.getY() * unit + ((int) nodeSize / 2)]=node.getPiece().getColor().getValue();
            }else {
                String id = String.valueOf(node.getId());
                for (int i = 0; i < id.length(); i++) {
                    pixels[(node.getX() * unit + ((int) nodeSize / 2)) + i][node.getY() * unit + ((int) nodeSize / 2)] = id.charAt(i);
                }
            }
        }

        for (Edge edge : this.edges){
            Node n1=edge.getStart();
            Node n2=edge.getEnd();

            int n1CenterX=n1.getX()*unit+((int)nodeSize/2);
            int n1CenterY=n1.getY()*unit+((int)nodeSize/2);
            int n2CenterX=n2.getX()*unit+((int)nodeSize/2);
            int n2CenterY=n2.getY()*unit+((int)nodeSize/2);

            int xDist=Math.abs(n1CenterX-n2CenterX);
            int yDist=Math.abs(n2CenterX-n2CenterY);
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(pixels[x][y]+" ");
            }
            System.out.println("");
        }
    }
}
