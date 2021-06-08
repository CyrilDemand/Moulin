package ihm2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import moulin.Save;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;

public class SceneCustomMap {
    private static Scene scene;

    public static void create(){
        VBox root=new VBox();
        Label label = new Label("Custom Map");
        Button loadGame = new Button("Load a game file");
        loadGame.setOnAction(e->{
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Game save file (*.json)","*.json");
            fileChooser.getExtensionFilters().add(extensionFilter);
            File file = fileChooser.showOpenDialog(Main.getStage());
            if (file!=null){
                try {
                    SceneNewGame.setJeu(Save.loadJeu(file.getAbsolutePath()));
                } catch (JSONException | IOException jsonException) {
                    jsonException.printStackTrace();
                }
                SceneNewGame.getJeu().getBoard().render();
            }
        });
        Button goBack =new Button("Go back");
        goBack.setOnAction(e->{
            Main.changeScene(SceneTypeOfMap.getScene());
        });
        Button goBackToMainMenu =new Button("go back to main menu");
        goBack.setOnAction(e->{
            Main.changeScene(SceneMainMenu.getScene());
        });
        root.getChildren().addAll(label,loadGame,goBack,goBackToMainMenu);
        scene=new Scene(root,1000,500);
    }

    public static Scene getScene(){
        return scene;
    }
}
