package ihm2;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
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

        HBox buttonBar=new HBox();
        Button goBackToMainMenu=new Button("Go Back to main menu");
        goBackToMainMenu.setOnAction(e->{
            Main.changeScene(SceneMainMenu.getScene());
        });
        Button goBack=new Button("Go Back");
        goBack.setOnAction(e->{
            Main.changeScene(SceneNewGame.getScene());
        });
        Button next=new Button("Next");
        next.setDisable(true);
        buttonBar.getChildren().addAll(goBackToMainMenu,goBack,next);

        Button loadGame = new Button("Load a game file");
        loadGame.setOnAction(e->{
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Game save file (*.json)","*.json");
            fileChooser.getExtensionFilters().add(extensionFilter);
            File file = fileChooser.showOpenDialog(Main.getStage());
            if (file!=null){
                try {
                    SceneNewGame.setJeu(Save.loadJeu(file.getAbsolutePath()));
                    next.setDisable(false);
                } catch (JSONException | IOException jsonException) {
                    jsonException.printStackTrace();
                }
                SceneNewGame.getJeu().getBoard().render();
            }
        });


        root.getChildren().addAll(canvas,loadGame,buttonBar);

        scene=new Scene(root,1000,500);
    }

    public static Scene getScene(){
        return scene;
    }
}
