package ihm;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.json.JSONException;

import java.io.IOException;

public class SceneChoixMain {
    private static Scene scene;

    /**
     * creates a scene
     */
    public static void create(){
        VBox root=new VBox();
        Button console = new Button("Console");
        console.setOnAction(actionEvent -> {
            ihm.Main.getStage().close();
            try {
                moulin.Main.mainConsole();
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });
        Button graphique = new Button("Graphic");
        graphique.setOnAction(actionEvent -> {
           Main.changeScene(SceneMainMenu.getScene());
        });
        root.getChildren().addAll(new Label("Please choose a execution mode !"),graphique,console);
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
