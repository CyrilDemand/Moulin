package ihm;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class SceneTemplate {

    private static Scene scene;

    /**
     * creates a scene
     */
    public static void create(){
        VBox root=new VBox();
        scene=new Scene(root,Main.getDefaultSceneWidth(),Main.getDefaultSceneHeight());
    }

    /**
     * gets the scene
     * @return the scene
     */
    public static Scene getScene(){
        return scene;
    }

    /**
     * switches to the scene
     */
    public static void switchTo(){
        Main.changeScene(scene);
    }
}
