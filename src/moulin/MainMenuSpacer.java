package moulin;

import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class MainMenuSpacer extends Region {

    private double prefHeightRatio;

    public MainMenuSpacer(Stage s, double prefHeightRatio){
        super();
        s.heightProperty().addListener( e->{
            this.setPrefHeight((s.getHeight()*prefHeightRatio));
        });
    }
    public MainMenuSpacer(Stage s){
        this(s,0.05);
    }
}
