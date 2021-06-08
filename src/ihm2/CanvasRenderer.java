package ihm2;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import moulin.Board;
import moulin.Edge;
import moulin.Jeu;
import moulin.Node;

public class CanvasRenderer {

    private static final double marge=30;
    private static final Color bgColor=new Color(0.9,0.9,0.9,1);
    private static final Color strokeColor=new Color(0,0,0,1);
    private static final Color nodeColor=new Color(0.5,0.5,0.5,1);
    private static final double nodeSize=30;

    public static void render(Canvas canvas, Jeu jeu){
        GraphicsContext gc=canvas.getGraphicsContext2D();

        Board board=jeu.getBoard();

        gc.setFill(bgColor);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());

        gc.setStroke(strokeColor);
        for (Edge e : board.getEdges()){
            double x1=CanvasRenderer.map(e.getStart().getX(), board.getMinX(),board.getMaxX(),0+marge,canvas.getWidth()-marge);
            double y1=CanvasRenderer.map(e.getStart().getY(), board.getMinY(),board.getMaxY(),0+marge,canvas.getHeight()-marge);

            double x2=CanvasRenderer.map(e.getEnd().getX(), board.getMinX(),board.getMaxX(),0+marge,canvas.getWidth()-marge);
            double y2=CanvasRenderer.map(e.getEnd().getY(), board.getMinY(),board.getMaxY(),0+marge,canvas.getHeight()-marge);

            gc.strokeLine(x1,y1,x2,y2);
        }

        gc.setFill(nodeColor);
        for (Node n : board.getNodes()){
            double x=CanvasRenderer.map(n.getX(), board.getMinX(),board.getMaxX(),0+marge,canvas.getWidth()-marge);
            double y=CanvasRenderer.map(n.getY(), board.getMinY(),board.getMaxY(),0+marge,canvas.getHeight()-marge);

            gc.fillOval(x-nodeSize/2,y-nodeSize/2,nodeSize,nodeSize);
            gc.strokeOval(x-nodeSize/2,y-nodeSize/2,nodeSize,nodeSize);
        }



    }

    private static double map(double x, double a, double b, double c,double d){
        return (x-a)/(b-a) * (d-c) + c;
    }
}
