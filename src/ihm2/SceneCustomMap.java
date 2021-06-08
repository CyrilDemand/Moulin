package ihm2;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import moulin.Jeu;
import moulin.Save;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;

public class SceneCustomMap {
    private static Scene scene;

    public static void create(){
        VBox root=new VBox();
        Canvas canvas = new Canvas(400,300);
        Label label = new Label("Custom Map");
        Button loadGame = new Button("Load a map file");

        HBox buttonBar=new HBox();
        Button goBackToMainMenu=new Button("Go Back to main menu");
        goBackToMainMenu.setOnAction(e->{
            Main.changeScene(SceneMainMenu.getScene());
        });
        Button goBack=new Button("Go Back");
        goBack.setOnAction(e->{
            Main.changeScene(SceneTypeOfMap.getScene());
        });
        Button next=new Button("Next");
        next.setDisable(true);
        buttonBar.getChildren().addAll(goBackToMainMenu,goBack,next);

        loadGame.setOnAction(e->{
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Game save file (*.json)","*.json");
            fileChooser.getExtensionFilters().add(extensionFilter);
            File file = fileChooser.showOpenDialog(Main.getStage());
            if (file!=null){
                try {
                    Jeu jeu = new Jeu(null,null);
                    jeu.setBoard(Save.loadBoard(file.getAbsolutePath()));
                    SceneNewGame.setJeu(jeu);
                    CanvasRenderer.render(canvas,jeu);
                    next.setDisable(false);
                } catch (JSONException | IOException jsonException) {
                    jsonException.printStackTrace();
                }
            }
        });


        root.getChildren().addAll(label,canvas,loadGame,buttonBar);
        scene=new Scene(root,1000,500);
    }

    public static Scene getScene(){
        return scene;
    }
}
