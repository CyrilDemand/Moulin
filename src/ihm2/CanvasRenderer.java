package ihm2;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import moulin.Jeu;

public class CanvasRenderer {
    public static void render(Canvas canvas, Jeu jeu){
        GraphicsContext gc=canvas.getGraphicsContext2D();
        if (jeu==null){
            gc.setFill(new Color(1,0,0,1));
        }else{
            gc.setFill(new Color(0.9,0.9,0.9,1));
        }
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
    }
}
