package ihm2;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import moulin.Board;
import moulin.Edge;
import moulin.Jeu;

public class CanvasRenderer {

    private static final double marge=30;
    private static final Color bgColor=new Color(0.9,0.9,0.9,1);
    private static final Color strokeColor=new Color(0,0,0,1);
    private static final Color nodeColor=new Color(0,0,0,1);

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



    }

    private static double map(double x, double a, double b, double c,double d){
        return (x-a)/(b-a) * (d-c) + c;
    }
}
