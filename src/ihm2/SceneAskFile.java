package ihm2;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import moulin.Save;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;

public class SceneAskFile {
    private static Scene scene;

    public static void create() throws JSONException, IOException {
        VBox root=new VBox();
        Canvas canvas = new Canvas(100,100);

        Button loadGame = new Button("File");
        loadGame.setOnAction(e->{
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("game save file (*.json)","*.json");
            fileChooser.getExtensionFilters().add(extensionFilter);
            File file = fileChooser.showOpenDialog(Main.stage);
            if (file!=null){
                try {
                    SceneNewGame.setJeu(Save.loadJeu(file.getAbsolutePath()));
                } catch (JSONException | IOException jsonException) {
                    jsonException.printStackTrace();
                }
                SceneNewGame.getJeu().getBoard().render();
            }
        });
        root.getChildren().addAll(canvas,loadGame);
        scene=new Scene(root,1000,500);
    }

    public static Scene getScene(){
        return scene;
    }
}
