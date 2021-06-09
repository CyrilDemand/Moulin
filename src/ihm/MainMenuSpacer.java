package ihm;

import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Creates an organized menu
 */
public class MainMenuSpacer extends Region {

    private double prefHeightRatio;

    /**
     * creates a MainMenuSpacer class
     * @param s used stage
     * @param prefHeightRatio used height
     */
    public MainMenuSpacer(Stage s, double prefHeightRatio){
        super();
        s.heightProperty().addListener( e->{
            this.setPrefHeight((s.getHeight()*prefHeightRatio));
        });
    }

    /**
     * creates a MainMenuSpacer class
     * @param s used Stage
     */
    public MainMenuSpacer(Stage s){
        this(s,0.05);
    }
}
