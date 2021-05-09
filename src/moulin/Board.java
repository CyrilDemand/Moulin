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
        Board board=Board.loadBoard("ressources\\mapVerif.json");
        board.render(3,2);
    }
    /**
     * Génère une class Board
     *
     * Attributs : ArrayList de Edges (Arrêtes, Lignes du Plateau)
     * Attributs : ArrayList de Nodes (Noeuds, Sommets du plateau)
     *
     */
    public Board() {
        this.edges = new ArrayList<Edge>();
        this.nodes = new ArrayList<Node>();
    }

    /**
     * Ajoute une node à l'ArrayList du plateau
     *
     * @param x  : Position X de la node
     * @param y  : Position Y de la node
     *
     * l'id de la node est générée avec la création d'une nouvelle node, par l'incrémentation d'un compteur
     *
     */

    public void addNode(int x,int y){
        this.nodes.add(new Node(x,y));
    }

    /**
     * Ajoute une node à l'ArrayList du plateau
     *
     * @param x  : Position X de la node
     * @param y  : Position Y de la node
     * @param id  : ID de la node
     *
     */

    public void addNode(int x,int y,int id){
        this.nodes.add(new Node(x,y,id));
    }

    /**
     * Ajoute une node à l'ArrayList du plateau
     *
     * @param node : Node ajoutée
     *
     */

    public void addNode(Node node){
        this.nodes.add(new Node(node.getX(),node.getY(),node.getId()));
    }

    /**
     * Ajoute une edge à l'ArrayList du plateau
     *
     * @param idStart : id de la première node
     * @param idEnd : id de la seconde node
     *
     */

    public void addEdge(int idStart, int idEnd){
        this.addEdge(this.getNodeById(idStart),this.getNodeById(idEnd));
    }

    /**
     * Ajoute une edge à l'ArrayList du plateau
     *
     * @param start : première node
     * @param end : seconde node
     *
     */

    public void addEdge(Node start,Node end){
        this.edges.add(new Edge(start,end));
    }

    /**
     * Retourne le tableau d'Edges du plateau
     *
     * @return ArrayList<Edge>
     *
     */

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    /**
     * Retourne le tableau de Nodes du plateau
     *
     * @return ArrayList<Node>
     *
     */

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     * Retourne la node d'une ID en paramètre
     *
     * @param id : id de la node à récupérer
     * @return n
     *
     */

    public Node getNodeById(int id){
        for (Node n:this.nodes){
            if (n.getId()==id){
                return n;
            }
        }
        throw new Error("Il n'y a pas de Node avec cet id sur ce board");
    }

    /**
     * Vérifie l'égalité entre deux plateaux
     *
     * @param other : un plateau de jeu 'Board'
     * @return true : si bien égaux
     * @return false : si l'un des plateaux est null, ou différent de l'autre
     *
     */

    public boolean equals(Board other){
        if(this==other)return true;
        if(this==null)return false;
        if(other==null)return false;
        this.getEdges().equals(other.getEdges());
        this.getNodes().equals(other.getNodes());
        return true;
    }

    /**
     * Génère le plateau sous forme d'un String
     *
     * @return String
     *
     * Exemple :
     *
     * Node(0,0,1);
     * Node(1,0,2);
     * Node(2,0,3);
     *
     * Edge(1,2);
     * Edge(2,3);
     *
     * Génération : Board{edges=[Edge{start=1, end=2}, Edge{start=2, end=3}], nodes=[Node{x=0, y=0, id=1}, Node{x=1, y=0, id=2}, Node{x=2, y=0, id=3}]}
     *
     */

    @Override
    public String toString() {
        return "Board{" +
                "edges=" + edges +
                ", nodes=" + nodes +
                '}';
    }

    /**
     * Vérifie l'égalité entre deux plateaux
     * Génère un plateau de jeu depuis un .json
     *
     * @param chemin : chemin d'accès au format d'un String
     * @return Board
     *
     */

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

    /**
     * Vérifie si des l'ID de deux Nodes sont bien liées par une Edge
     *
     * @return true : si les Nodes sont liées
     * @return false : si elles ne le sont pas
     *
     */

    public boolean isLinked(int a, int b){
        for (Edge e:this.getEdges()) {
            if (e.getStart().equals(this.getNodeById(a))&&e.getEnd().equals(this.getNodeById(b))){
                return true;
            }
        }
        return false;
    }

    /**
     * Compare toutes les nodes et récupère le plus grand X connu parmi elles
     *
     * @return int
     *
     */

    private int getMaxX(){
        int maxX=0;
        for (Node node : this.nodes){
            if (node.getX()>maxX)maxX=node.getX();
        }
        return maxX;
    }

    /**
     * Compare toutes les nodes et récupère le plus grand Y connu parmi elles
     *
     * @return int
     *
     */

    private int getMaxY(){
        int maxY=0;
        for (Node node : this.nodes){
            if (node.getY()>maxY)maxY=node.getY();
        }
        return maxY;
    }

    /**
     * Génère un rendu du plateau avec gestion de la taille des nodes et de leur espacement
     *
     * @param nodeSize : Taille de node
     * @param margeSize : Taide des arêtes
     *
     */

    public void render(int nodeSize,int margeSize) {
        int maxX = this.getMaxX(), maxY = this.getMaxY();
        int unit=(nodeSize+margeSize);
        int width=unit*(maxX+1)-margeSize;
        int height=unit*(maxY+1)-margeSize;

        char[][] pixels = new char[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x][y]=' ';
            }
        }

        for (Node node : this.nodes){
            for (int y = 0; y < nodeSize; y++) {
                for (int x = 0; x < nodeSize; x++) {
                    pixels[node.getX()*unit+x][node.getY()*unit+y]='*';
                }
            }
            if (node.getPiece()!=null){
                pixels[(node.getX() * unit + ((int) nodeSize / 2))][node.getY() * unit + ((int) nodeSize / 2)]=node.getPiece().getColor().getValue();
            }else {
                String id = String.valueOf(node.getId());
                for (int i = 0; i < id.length(); i++) {
                    try {
                    pixels[(node.getX() * unit + ((int) nodeSize / 2)) + i][node.getY() * unit + ((int) nodeSize / 2)] = id.charAt(i);
                    } catch (ArrayIndexOutOfBoundsException e){
                        //System.out.printf("ATTENTION : Certains caractères n'ont pas pu être affichés lors du rendu (id trop long)");
                    }
                }
            }
        }


        for (Edge edge:this.edges){
            Node n1=edge.getStart();
            Node n2=edge.getEnd();
            //System.out.println("Node : n1="+n1.getId()+" n2="+n2.getId());
            //System.out.println("Start : x="+edge.getStart().getX()+" y="+edge.getStart().getY());
            //System.out.println("End : x="+edge.getEnd().getX()+" y="+edge.getEnd().getY());

            int n1CenterX=n1.getX()*unit+((int)nodeSize/2);
            int n1CenterY=n1.getY()*unit+((int)nodeSize/2);
            int n2CenterX=n2.getX()*unit+((int)nodeSize/2);
            int n2CenterY=n2.getY()*unit+((int)nodeSize/2);
            //System.out.println("Start Char: x="+n1CenterX+" y="+n1CenterY);
            //System.out.println("End Char : x="+n2CenterX+" y="+n2CenterY);

            int xDist=n2CenterX-n1CenterX;
            int yDist=n2CenterY-n1CenterY;
            //System.out.println("Dist : x="+xDist+" y="+yDist);

            int xStep,yStep;


            //System.out.println("Steps : x="+xDist+" y="+yDist);

            int x=n1CenterX;
            int y=n1CenterY;

            do{
                //System.out.println("x="+x+" y="+y);
                if (x>=0 && y>=0 && x<width && y<height && pixels[(int)x][(int)y]==' '){
                    pixels[(int)x][(int)y]='.';

                    /*if (xDist==0){
                        pixels[(int)x][(int)y]='|';
                    }else if (yDist==0){
                        pixels[(int)x][(int)y]='─';
                    }else if (xDist>0 && yDist>0 || xDist<0 && yDist<0){
                        pixels[(int) x][(int) y] = '╲';
                    }else{
                        pixels[(int) x][(int) y] = '╱';
                    }*/
                }
                if (xDist!=0)x+=(int)(xDist/Math.abs(xDist));
                if (yDist!=0)y+=(int)(yDist/Math.abs(yDist));
            }while (x!=n2CenterX || y!=n2CenterY);

            //System.out.println("Final Pos : x="+x+" y="+y);
        }


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(pixels[x][y]+" ");
            }
            System.out.println("");
        }
    }
}
