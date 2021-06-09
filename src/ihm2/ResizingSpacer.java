package ihm2;

import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ResizingSpacer extends Region {

    private double prefHeightRatio;

    public ResizingSpacer(Stage s, double prefHeightRatio){
        super();
        s.heightProperty().addListener( e->{
            this.setPrefHeight((s.getHeight()*prefHeightRatio));
        });
    }
    public ResizingSpacer(Stage s){
        this(s,0.05);
    }
}
