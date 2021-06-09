package ihm2;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class SceneEndGame {
    private static Scene scene;

    public static void create(){
        VBox root=new VBox();
        scene=new Scene(root,Main.getDefaultSceneWidth(),Main.getDefaultSceneHeight());
    }

    public static Scene getScene(){
        return scene;
    }

    public static void switchTo(){
        Main.changeScene(scene);
    }
}
