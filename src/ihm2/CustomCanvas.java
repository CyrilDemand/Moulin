package ihm2;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import moulin.Board;
import moulin.Edge;
import moulin.Jeu;
import moulin.Node;

public class CustomCanvas extends Canvas {
    private static final double marge=30;
    private static final Color bgColor=new Color(0.9,0.9,0.9,1);
    private static final Color strokeColor=new Color(0,0,0,1);
    private static final Color nodeColor=new Color(0.5,0.5,0.5,1);
    private static final Color selectedColor=new Color(0,1,0,1);
    private static final double nodeSize=30;
    private static final double pieceSize=0.6;

    private Node selectedNode=null;
    private Edge selectedEdge=null;

    private boolean canSelectNode;
    private boolean canSelectEdge;

    private double mouseX,mouseY;

    /**
     * Creates a CustomCanvas class
     * @param width width of the canvas
     * @param height height of the canvas
     */
    public CustomCanvas(double width,double height){
        this(width,height,false,false);
    }

    /**
     * Creates a CustomCanvas class
     * @param width width of the canvas
     * @param height height of the canvas
     * @param canSelectNode shows if you can select a node
     * @param canSelectEdge shows if you can select an edge
     */
    public CustomCanvas(double width,double height,boolean canSelectNode,boolean canSelectEdge){
        super(width,height);
        super.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseX=mouseEvent.getX();
                mouseY=mouseEvent.getY();
                render(SceneNewGame.getJeu());
                //System.out.println("x="+mouseX+" y="+mouseY);
            }
        });
        this.canSelectNode=canSelectNode;
        this.canSelectEdge=canSelectEdge;


    }

    /**
     * unselect every selected elements
     * @param jeu current game
     */
    public void unselect(Jeu jeu){
        selectedNode=null;
        selectedEdge=null;
        this.render(jeu);
    }

    /**
     * selects the elements
     * @param jeu current game
     */
    public void select(Jeu jeu){
        selectedNode=null;
        selectedEdge=null;

        if (canSelectNode){
            selectedNode=this.mouseOverNode(jeu);
        }
        if (canSelectEdge && selectedNode==null){
            selectedEdge=this.mouseOverEdge(jeu);
        }
        this.render(jeu);
    }

    /**
     * gets the selected node
     * @return selected node
     */
    public Node getSelectedNode() {
        return selectedNode;
    }

    /**
     * gets the selected edge
     * @return selected edge
     */
    public Edge getSelectedEdge() {
        return selectedEdge;
    }

    /**
     * gets the X position of the mouse
     * @return the X position of the mouse
     */
    public double getMouseX(){
        return this.mouseX;
    }

    /**
     * gets the Y position of the mouse
     * @return the Y position of the mouse
     */
    public double getMouseY(){
        return this.mouseY;
    }

    /**
     * render the current game on the canvas
     * @param jeu current game
     */
    public void render(Jeu jeu){
        GraphicsContext gc=this.getGraphicsContext2D();

        if (jeu==null){
            gc.setFill(new Color(1,0,0,1));
            gc.fillRect(0,0,this.getWidth(),this.getHeight());
            return;
        }

        Board board=jeu.getBoard();

        gc.setFill(bgColor);
        gc.fillRect(0,0,this.getWidth(),this.getHeight());


        for (Edge e : board.getEdges()){
            double x1=CustomCanvas.map(e.getStart().getX(), board.getMinX(),board.getMaxX(),0+marge,this.getWidth()-marge);
            double y1=CustomCanvas.map(e.getStart().getY(), board.getMinY(),board.getMaxY(),0+marge,this.getHeight()-marge);

            double x2=CustomCanvas.map(e.getEnd().getX(), board.getMinX(),board.getMaxX(),0+marge,this.getWidth()-marge);
            double y2=CustomCanvas.map(e.getEnd().getY(), board.getMinY(),board.getMaxY(),0+marge,this.getHeight()-marge);

            if (this.selectedEdge==e){
                gc.setStroke(selectedColor);
            }else{
                gc.setStroke(strokeColor);
            }


            if (e.isTrapped()){
                gc.setStroke(new Color(1,0,0,1));
            }
            gc.strokeLine(x1,y1,x2,y2);
        }

        for (Node n : board.getNodes()){
            double x=CustomCanvas.map(n.getX(), board.getMinX(),board.getMaxX(),0+marge,this.getWidth()-marge);
            double y=CustomCanvas.map(n.getY(), board.getMinY(),board.getMaxY(),0+marge,this.getHeight()-marge);

            gc.setFill(nodeColor);
            if (this.selectedNode==n){
                gc.setStroke(selectedColor);
            }else{
                gc.setStroke(strokeColor);
            }

            gc.fillOval(x-nodeSize/2,y-nodeSize/2,nodeSize,nodeSize);
            gc.strokeOval(x-nodeSize/2,y-nodeSize/2,nodeSize,nodeSize);

            if (n.getPiece()!=null){
                gc.setFill(n.getPiece().getColor().getColor());
                gc.fillOval(x-nodeSize*pieceSize/2,y-nodeSize*pieceSize/2,nodeSize*pieceSize,nodeSize*pieceSize);
            }
        }
    }

    /**
     * gets the node hovered by the mouse
     * @param jeu current game
     * @return the node
     */
    public Node mouseOverNode(Jeu jeu){

        for (Node n:jeu.getBoard().getNodes()){
            double x=CustomCanvas.map(n.getX(), jeu.getBoard().getMinX(),jeu.getBoard().getMaxX(),0+marge,this.getWidth()-marge);
            double y=CustomCanvas.map(n.getY(), jeu.getBoard().getMinY(),jeu.getBoard().getMaxY(),0+marge,this.getHeight()-marge);

            if (CustomCanvas.distance(this.getMouseX(),this.getMouseY(),x,y)<nodeSize/2){
                return n;
            }
        }
        return null;
    }

    /**
     * gets the edge hovered by the mouse
     * @param jeu current game
     * @return the edge
     */
    public Edge mouseOverEdge(Jeu jeu){



        Board board=jeu.getBoard();

        for (Edge e:jeu.getBoard().getEdges()){
            double x1=CustomCanvas.map(e.getStart().getX(), board.getMinX(),board.getMaxX(),0+marge,this.getWidth()-marge);
            double y1=CustomCanvas.map(e.getStart().getY(), board.getMinY(),board.getMaxY(),0+marge,this.getHeight()-marge);
            double x2=CustomCanvas.map(e.getEnd().getX(), board.getMinX(),board.getMaxX(),0+marge,this.getWidth()-marge);
            double y2=CustomCanvas.map(e.getEnd().getY(), board.getMinY(),board.getMaxY(),0+marge,this.getHeight()-marge);

            double a=mouseX-x1,b=mouseY-y1,c=x2-x1,d=y2-y1;
            double dot=a*c+b*d;
            double len_sq = c*c+d*d;

            double param = -1;
            if (len_sq != 0) //in case of 0 length line
                param = dot / len_sq;

            double xx, yy;

            if (param < 0) {
                xx = x1;
                yy = y1;
            } else if (param > 1) {
                xx = x2;
                yy = y2;
            } else {
                xx = x1 + param * c;
                yy = y1 + param * d;
            }

            double dx = mouseX - xx;
            double dy = mouseY - yy;
            double dist=Math.sqrt(dx * dx + dy * dy);
            if (dist<10){
                return e;
            }
        }
        return null;
    }

    /**
     * Gives the distance between two spots
     * @param x1 x of the first spot
     * @param y1 y of the first spot
     * @param x2 x of the second spot
     * @param y2 y of the second spot
     * @return the distance
     */
    private static double distance (double x1,double y1, double x2,double y2){
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    /**
     * takes a point from an interval, and moves it to another one
     * @param x point
     * @param a beginning of the first interval
     * @param b ending of the first interval
     * @param c beginning of the second interval
     * @param d ending of the second interval
     * @return the point
     */
    private static double map(double x, double a, double b, double c,double d){
        return (x-a)/(b-a) * (d-c) + c;
    }
}
