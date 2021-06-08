package ihm2;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import moulin.Jeu;

public class CustomCanvas extends Canvas {
    private Jeu jeu;

    public CustomCanvas(double width, double height, Jeu jeu){
        super(width,height);
        this.jeu=jeu;
    }

    public void render(){
        GraphicsContext gc=this.getGraphicsContext2D();

        if (this.jeu==null){
            gc.setFill(new Color(1,0,0,1));
        }else{
            gc.setFill(new Color(0.9,0.9,0.9,1));
        }


        gc.fillRect(0,0,this.getWidth(),this.getHeight());
    }

    public Jeu getJeu() {
        return jeu;
    }
}
