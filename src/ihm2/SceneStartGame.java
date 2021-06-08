package ihm2;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import moulin.Config;

public class SceneStartGame {
    private static Scene scene;

    public static void create(){
        VBox root=new VBox();
        CustomCanvas customCanvas = new CustomCanvas(500,500);
        root.getChildren().add(customCanvas);
        customCanvas.render(SceneNewGame.getJeu());

        scene=new Scene(root,Main.getDefaultSceneWidth(),Main.getDefaultSceneHeight());
    }

    public static Scene getScene(){
        return scene;
    }

    public static void switchTo(){
        Main.changeScene(scene);
    }
}
