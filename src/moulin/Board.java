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
     * Generates a Board class
     *
     */

    public Board() {
        this.edges = new ArrayList<Edge>();
        this.nodes = new ArrayList<Node>();
        this.lines = new ArrayList<Line>();
    }

    /**
     *
     * Use the board given to copy it's attributes into the main Board
     *
     * @param board board used to copy it into the main board
     */

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
     * Adds a node to the "nodes" Arraylist
     *
     * @param x  X position of the node
     * @param y  Y position of the node
     *
     * the node's ID is generated with the creation of a new node, by the incrementation of a counter
     */

    public void addNode(int x,int y){
        this.nodes.add(new Node(x,y));
    }

    /**
     * Adds a node to the "nodes" ArrayList
     *
     * @param x X position of the node
     * @param y Y position of the node
     * @param id node's ID
     *
     */

    public void addNode(int x,int y,int id){
        this.nodes.add(new Node(x,y,id));
    }

    /**
     * Adds a node to the "nodes" ArrayList
     *
     * @param node added node
     *
     */

    public void addNode(Node node){
        this.nodes.add(node);
    }

    /**
     * Adds a list of nodes to the "nodes" ArrayList
     *
     * @param nodes added list of node
     *
     */

    public void addNodes(ArrayList<Node> nodes){
        for(Node n : nodes) {
            this.nodes.add(n);
        }
    }

    /**
     * Adds an Edge to the "edges" ArrayList
     *
     * @param idStart First node's ID
     * @param idEnd Second node's ID
     *
     */

    public void addEdge(int idStart, int idEnd){
        this.addEdge(this.getNodeById(idStart),this.getNodeById(idEnd));
    }

    /**
     * Adds an Edge to the "edges" ArrayList
     *
     * @param start First node
     * @param end Second node
     *
     */

    public void addEdge(Node start,Node end){
        this.edges.add(new Edge(start,end));
    }

    /**
     * Adds an Edge to the "edges" ArrayList
     *
     * @param edge the edge
     *
     */

    public void addEdge(Edge edge){
        this.edges.add(new Edge(edge.getStart(),edge.getEnd()));
    }

    /**
     * Add an ArrayList of nodes to the "nodes" ArrayList
     *
     * @param edges added list of node
     */

    public void addEdges(ArrayList<Edge> edges){
        for (Edge e : edges){
            this.addEdge(e);
        }
    }

    /**
     * Returns the Edge Table of the Board
     * @return the edge table
     */
    public ArrayList<Edge> getEdges() {
        return edges;
    }

    /**
     * return the nodes ArrayList
     * @return the list of the nodes
     */

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     * Gets the lines ArrayList
     * @return the lines ArrayList
     *
     */

    public ArrayList<Line> getLines() {
        return lines;
    }

    /**
     * Takes a nodes ArrayList to create a new Line in the "lines" ArrayList
     * @param nodes the ArrayList used to create a Line
     */

    public void addLine(ArrayList<Node> nodes){
        this.lines.add(new Line(nodes));
    }

    /**
     *
     * Creates a Trap on an Edge
     *
     * @param idStart Start of the Trap
     * @param idEnd End of the Trap
     * @param turns Amount of turns the Trap remains
     * @return true if you can place the Trap
     */


    public boolean trapEdge(int idStart,int idEnd, int turns){
        Node start=this.getNodeById(idStart),end=this.getNodeById(idEnd);
        for (Edge e:this.edges){
            if (e.getStart().equals(start) && e.getEnd().equals(end) || e.getStart().equals(end) && e.getEnd().equals(start) && !e.isTrapped()){
                e.setTrap(new Trap(turns));
                return true;
            }
        }
        return false;
    }

    /**
     * Places a Trap that teleports the Piece to another location
     *
     * @param id Trap's position
     * @param idDestination Destination of the teleporter
     * @param turns Amount of turn the teleporter remains
     * @return true if you can place the Trap
     */
    public boolean trapNode(int id, int idDestination,int turns){
        Node start=this.getNodeById(id);
        if (start==null)return false;
        Node destination=this.getNodeById(idDestination);
        if (destination==null)return false;

        if (destination.equals(start))return false;
        if (destination.isTrapped()) return false;
        if (start.isTrapped()) return false;
        for (Node n:this.nodes){
            if (n.isTrapped() && (n.getTrap().getDestination().equals(start)||n.getTrap().getDestination().equals(destination) ))return false;
        }

        start.setTrap(new TrapTeleport(turns,destination));
        return true;
    }


    /**
     * Switch to next turn for the Traps
     */

    public void nextTurn(){
        for (Edge edge:this.edges){
            if (edge.isTrapped())edge.getTrap().nextTurn();
        }
        for (Node node:this.nodes){
            if (node.isTrapped())node.getTrap().nextTurn();
        }
    }

    /**
     * Returns the node associated to the given ID
     *
     * @param id ID of the node
     * @return the Node
     *
     */


    public Node getNodeById(int id){
        for (Node n:this.nodes){
            if (n.getId()==id){
                return n;
            }
        }
        return null;
        //throw new Error("Il n'y a pas de Node avec cet id sur ce board");
    }

    /**
     * Check if two Boards are the same
     * @param other the second Board
     * @return true if they are the same, false otherwise
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
     * Generates the Board in a String
     *
     * @return String
     *
     * Example :
     *
     * Node(0,0,1);
     * Node(1,0,2);
     * Node(2,0,3);
     *
     * Edge(1,2);
     * Edge(2,3);
     *
     * Generation : Board{edges=[Edge{start=1, end=2}, Edge{start=2, end=3}], nodes=[Node{x=0, y=0, id=1}, Node{x=1, y=0, id=2}, Node{x=2, y=0, id=3}]}
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
     * Checks if the ID of two nodes are linked by an Edge
     * @param a first node ID
     * @param b second node ID
     * @return true if they are linked, false otherwise
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
     * Compare all the nodes and get the highest X position known within the nodes
     * @return int
     *
     */

    public int getMaxX(){
        int maxX=0;
        for (Node node : this.nodes){
            if (node.getX()>maxX)maxX=node.getX();
        }
        return maxX;
    }

    /**
     * Compare all the nodes and get the highest Y position known within the nodes
     *
     * @return int
     *
     */

    public int getMaxY(){
        int maxY=0;
        for (Node node : this.nodes){
            if (node.getY()>maxY)maxY=node.getY();
        }
        return maxY;
    }

    /**
     * Compare all the nodes and get the lowest X position known within the nodes
     *
     * @return int
     *
     */

    public int getMinX(){
        int minX=999999;
        for (Node node : this.nodes){
            if (node.getX()<minX)minX=node.getX();
        }
        return minX;
    }

    /**
     * Compare all the nodes and get the lowest Y position known within the nodes
     *
     * @return int
     *
     */

    public int getMinY(){
        int minY=99999;
        for (Node node : this.nodes){
            if (node.getY()<minY)minY=node.getY();
        }
        return minY;
    }

    /**
     * Compare all the nodes and get the lowest ID known within the nodes
     *
     * @return int
     *
     */

    public int getMinId(){
        int minId=99999;
        for (Node node : this.nodes){
            if (node.getId()<minId)minId=node.getId();
        }
        return minId;
    }

    /**
     * Compare all the nodes and get the highest ID known within the nodes
     *
     * @return int
     *
     */

    public int getMaxId(){
        int maxId=0;
        for (Node node : this.nodes){
            if (node.getId()>maxId)maxId=node.getId();
        }
        return maxId;
    }

    /**
     * Generates a Board with a node size/spacing management
     * @param nbSides amount of sides to the board
     * @return the Board generated
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

            //----------Nodes and Edges Creation------------------

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

        //----------Lines Creation------------------
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
     * Generates a board with nodes size/spacing management
     *
     * @param nodeSize node's size
     * @param margeSize edge's size
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

        //===================Node's render===============================

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

        //===================Lined render===============================

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

        //===================Final display===============================

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(pixels[x][y]+" ");
            }
            System.out.println("");
        }
    }
}
