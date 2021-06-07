package ihm2;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class SceneCustomMap {
    private static Scene scene;

    public static void create(){
        VBox root=new VBox();
        scene=new Scene(root,1000,500);
    }

    public static Scene getScene(){
        return scene;
    }
}
