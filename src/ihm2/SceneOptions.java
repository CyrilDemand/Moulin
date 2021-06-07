package ihm2;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SceneOptions {

    private static Scene scene;

    public static void create(){
        VBox root=new VBox();
        root.getChildren().add(new Label("Options"));
        scene=new Scene(root,1000,500);
    }

    public static Scene getScene(){
        return scene;
    }

}
