package moulin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Board {
    private ArrayList<Edge> edges;
    private ArrayList<Node> nodes;
    private ArrayList<Line> lines;

    public static void main(String[] args) {
        Board board=Board.generateBoard(4);
        board.trapNode(1,2,3);
        System.out.println(board.isLinked(1,2));
        board.render(3,2);

        System.out.println(board.toString());
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
        this.lines = new ArrayList<Line>();
    }

    public Board(Board board){
        this();
        for (Node n:board.getNodes()) {
            this.nodes.add(new Node(n));
        }
        for (Edge e:board.getEdges()) {
            this.edges.add(new Edge(e));
        }
        this.lines=board.lines;
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
        this.nodes.add(node);
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

    public ArrayList<Line> getLines() {
        return lines;
    }

    public void addLine(ArrayList<Node> nodes){
        this.lines.add(new Line(nodes));
    }

    public boolean trapEdge(int idStart,int idEnd, int turns){
        Node start=this.getNodeById(idStart),end=this.getNodeById(idEnd);
        boolean res=false;
        for (Edge e:this.edges){
            if (e.getStart().equals(start) && e.getEnd().equals(end) || e.getStart().equals(end) && e.getEnd().equals(start)){
                e.setTrap(new Trap(turns));
                res=true;
            }
        }
        return res;
    }

    public boolean trapNode(int id, int idDestination,int turns){
        Node start=this.getNodeById(id);
        if (start==null)return false;
        Node destination=this.getNodeById(idDestination);
        if (destination==null)return false;

        start.setTrap(new TrapTeleport(turns,destination));
        return true;
    }

    public void nextTurn(){
        for (Edge edge:this.edges){
            if (edge.isTrapped())edge.getTrap().nextTurn();
        }
        for (Node node:this.nodes){
            if (node.isTrapped())node.getTrap().nextTurn();
        }
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
                ", lines=" + lines +
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
            JSONArray lines = o.getJSONArray("lines");
            for (int i = 0;i<nodes.length();i++){
                res.addNode(nodes.getJSONArray(i).getInt(0),nodes.getJSONArray(i).getInt(1),nodes.getJSONArray(i).getInt(2));
            }
            for (int i = 0;i<edges.length();i++){
                res.addEdge(edges.getJSONArray(i).getInt(0),edges.getJSONArray(i).getInt(1));
            }
            for (int i = 0;i<lines.length();i++){
                ArrayList<Node> lineNodes=new ArrayList<Node>();
                for (int j=0;j<lines.getJSONArray(i).length();j++) {
                    lineNodes.add(res.getNodeById(lines.getJSONArray(i).getInt(j)));
                }
                res.addLine(lineNodes);
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
        Node na=this.getNodeById(a);
        Node nb=this.getNodeById(b);

        for (Edge e:this.getEdges()) {
            if (e.isTrapped())continue;
            if (e.getStart().equals(na)&&e.getEnd().equals(nb) ||
                    e.getStart().equals(nb)&&e.getEnd().equals(na)){
                return true;
            }
        }

        for (Node n :this.getNodes()) {
            if (n.isTrapped() && n.equals(na) && n.getTrap().getDestination().equals(nb))return true;
            if (n.isTrapped() && n.equals(nb) && n.getTrap().getDestination().equals(na))return true;
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
     * Compare toutes les nodes et récupère le plus petit X connu parmi elles
     *
     * @return int
     *
     */

    private int getMinX(){
        int minX=999999;
        for (Node node : this.nodes){
            if (node.getX()<minX)minX=node.getX();
        }
        return minX;
    }

    /**
     * Compare toutes les nodes et récupère le plus petit Y connu parmi elles
     *
     * @return int
     *
     */

    private int getMinY(){
        int minY=99999;
        for (Node node : this.nodes){
            if (node.getY()<minY)minY=node.getY();
        }
        return minY;
    }

    /**
     * Compare toutes les nodes et récupère le plus petit id connu parmi elles
     *
     * @return int
     *
     */

    private int getMinId(){
        int minId=99999;
        for (Node node : this.nodes){
            if (node.getId()<minId)minId=node.getId();
        }
        return minId;
    }

    /**
     * Compare toutes les nodes et récupère le plus grand id connu parmi elles
     *
     * @return int
     *
     */

    private int getMaxId(){
        int maxId=0;
        for (Node node : this.nodes){
            if (node.getId()>maxId)maxId=node.getId();
        }
        return maxId;
    }

    /**
     * Génère un rendu du plateau avec gestion de la taille des nodes et de leur espacement
     *
     * @param nbSides : nombre de cotés de la map
     */

    public static Board generateBoard(int nbSides){
        Board board=new Board();
        Node.resetCounter();

        double angleStep=(2*Math.PI)/nbSides;

        for (double layer=0;layer<3;layer++){
            double radius=2+layer*2;

            Node newNode=null;
            Node newNodeMiddle=null;
            Node startNode=null;

            //----------Creation Node et Edges------------------

            for (int i=0;i<nbSides;i++){


                double x=(Math.cos(angleStep*i)*radius);
                double y=(Math.sin(angleStep*i)*radius);

                double nextX=(Math.cos(angleStep*(i+1))*radius);
                double nextY=(Math.sin(angleStep*(i+1))*radius);

                double middleX=(x+nextX)/2;
                double middleY=(y+nextY)/2;

                newNode=new Node((int)Math.round(x),(int)Math.round(y));
                if (i==0)startNode=newNode;
                if (newNodeMiddle!=null)board.addEdge(newNodeMiddle,newNode);
                newNodeMiddle=new Node((int)Math.round(middleX),(int)Math.round(middleY));


                board.addNode(newNode);
                board.addNode(newNodeMiddle);
                board.addEdge(newNode,newNodeMiddle);
                if (i==nbSides-1)board.addEdge(startNode,newNodeMiddle);
            }
        }

        for (int i=1;i<board.getMaxId();i+=2){
            int nextId=i+2*nbSides;
            if (nextId<board.getMaxId())board.addEdge(i,nextId);
        }

        //----------Creation Lines------------------
        int nodesPerLayer=nbSides*2;
        for (int layer=0;layer<3;layer++){
            int startId=1+nodesPerLayer*layer;
            for (int side=0;side<nbSides;side++){
                ArrayList<Node> lineNodes=new ArrayList<Node>();


                lineNodes.add(board.getNodeById(startId+side*2));
                lineNodes.add(board.getNodeById(startId+side*2+1));
                lineNodes.add(board.getNodeById(startId+side*2+2==startId+nodesPerLayer ? startId : startId+side*2+2) );

                //System.out.println(lineNodes);

                board.addLine(lineNodes);
            }
        }

        for (int side=0;side<nbSides;side++){
            ArrayList<Node> lineNodes=new ArrayList<Node>();

            lineNodes.add(board.getNodeById(1+side*2 + 0*nodesPerLayer));
            lineNodes.add(board.getNodeById(1+side*2 + 1*nodesPerLayer));
            lineNodes.add(board.getNodeById(1+side*2 + 2*nodesPerLayer));

            //System.out.println(lineNodes);

            board.addLine(lineNodes);
        }

        int minX=board.getMinX();
        int minY=board.getMinY();
        for (Node node : board.getNodes()) {
            if (minX < 0) node.setX(node.getX() + Math.abs(minX));
            if (minY < 0) node.setY(node.getY() + Math.abs(minY));
        }
        return board;
    }

    /**
     * Génère un rendu du plateau avec gestion de la taille des nodes et de leur espacement
     *
     * @param nodeSize : Taille des nodes
     * @param margeSize : Taide des arêtes
     *
     */

    public void render(int nodeSize,int margeSize) {
        int maxX = this.getMaxX(), maxY = this.getMaxY();
        int unit=(nodeSize+margeSize);
        int width=unit*(maxX+1)-margeSize;
        int height=unit*(maxY+1)-margeSize;

        String[][] pixels = new String[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x][y]=" ";
            }
        }

        //===================RENDU DES NODES===============================

        for (Node node : this.nodes){
            int offset=(nodeSize%2==0 ? 1 : 0);
            int centerX=node.getX()*unit+((int)(nodeSize/2))-offset;
            int centerY=node.getY()*unit+((int)(nodeSize/2))-offset;

            for (int y = 0; y < nodeSize; y++) {
                for (int x = 0; x < nodeSize; x++) {
                    pixels[node.getX()*unit+x][node.getY()*unit+y]="░";
                }
            }
            if (node.getPiece()!=null) {
                try {
                    pixels[centerX][centerY] = node.getPiece().getColor().getString() + node.getPiece().getId() + Color.ANSI_RESET;
                } catch (ArrayIndexOutOfBoundsException e) {
                    //System.out.printf("ATTENTION : Certains caractères n'ont pas pu être affichés lors du rendu (id trop long)");
                }
            }
            String id = String.valueOf(node.getId());
            for (int i = 0; i < id.length(); i++) {
                try {
                    pixels[node.getX()*unit+i][node.getY()*unit] = ""+id.charAt(i);
                } catch (ArrayIndexOutOfBoundsException e){
                    //System.out.printf("ATTENTION : Certains caractères n'ont pas pu être affichés lors du rendu (id trop long)");
                }
            }
            if (node.isTrapped()){
                pixels[node.getX()*unit][node.getY()*unit+nodeSize-1] = ">";
                id = String.valueOf(node.getTrap().getDestination().getId());
                for (int i = 0; i < id.length(); i++) {
                    try {
                        pixels[node.getX()*unit+1+i][node.getY()*unit+nodeSize-1] = ""+id.charAt(i);
                    } catch (ArrayIndexOutOfBoundsException e){
                        //System.out.printf("ATTENTION : Certains caractères n'ont pas pu être affichés lors du rendu (id trop long)");
                    }
                }

            }

        }

        //===================RENDU DES LIGNES===============================

        for (Edge edge:this.edges){
            Node n1=edge.getStart();
            Node n2=edge.getEnd();
            //System.out.println("Node : n1="+n1.getId()+" n2="+n2.getId());
            //System.out.println("Start : x="+edge.getStart().getX()+" y="+edge.getStart().getY());
            //System.out.println("End : x="+edge.getEnd().getX()+" y="+edge.getEnd().getY());

            int n1CenterX=n1.getX()*unit+((int)(nodeSize/2));
            int n1CenterY=n1.getY()*unit+((int)(nodeSize/2));
            int n2CenterX=n2.getX()*unit+((int)(nodeSize/2));
            int n2CenterY=n2.getY()*unit+((int)(nodeSize/2));
            //System.out.println("Start Char: x="+n1CenterX+" y="+n1CenterY);
            //System.out.println("End Char : x="+n2CenterX+" y="+n2CenterY);

            float xDist=n2CenterX-n1CenterX;
            float yDist=n2CenterY-n1CenterY;
            //System.out.println("Dist : x="+xDist+" y="+yDist);

            float xStep,yStep;
            if (xDist==0){
                xStep=0;yStep=(yDist>=0 ? 1 : -1);
            }else if (yDist==0){
                yStep=0;xStep=(xDist>=0 ? 1 : -1);
            }else{
                xStep=Math.abs(xDist/yDist)*(xDist>=0 ? 1 : -1);
                yStep=Math.abs(yDist/xDist)*(yDist>=0 ? 1 : -1);
            }
            xStep=Math.min(Math.max(xStep,-1),1);
            yStep=Math.min(Math.max(yStep,-1),1);

            //System.out.println("Steps : x="+xStep+" y="+yStep);

            float x=n1CenterX;
            float y=n1CenterY;

            //for (int i=0;i<10;i++)
            do{
                //System.out.println("x="+x+" y="+y);
                if (x>=0 && y>=0 && x<width && y<height && pixels[(int)x][(int)y].equals(" ")){

                    if (edge.isTrapped()) {
                        pixels[(int)x][(int)y]=".";
                    }else{
                        pixels[(int)x][(int)y]="*";
                    }


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
                if (xDist!=0)x+=xStep;
                if (yDist!=0)y+=yStep;
            }

            while ( !(x>n2CenterX-(float)nodeSize/2 && x<n2CenterX+(float)nodeSize/2 && y>n2CenterY-(float)nodeSize/2 && y<n2CenterY+(float)nodeSize/2) );

            //System.out.println("Final Pos : x="+x+" y="+y);
        }

        //===================AFFICHAGE FINAL===============================

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(pixels[x][y]+" ");
            }
            System.out.println("");
        }
    }
}
