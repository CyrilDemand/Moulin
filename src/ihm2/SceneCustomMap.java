package ihm2;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import moulin.Jeu;
import moulin.Save;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        next.setOnAction(e->{
            Main.changeScene(SceneChoixJoueurs.getScene());
        });
        buttonBar.getChildren().addAll(goBackToMainMenu,goBack,next);

        loadGame.setOnAction(e->{
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Game save file (*.json)","*.json");
            fileChooser.getExtensionFilters().add(extensionFilter);
            File file = fileChooser.showOpenDialog(Main.getStage());
            if (file!=null){
                try {
                    JSONArray type = new JSONArray();
                    try{
                        String content = new String((Files.readAllBytes(Paths.get(file.getAbsolutePath()))));
                        JSONObject o = new JSONObject(content);
                        type = o.getJSONArray("type");
                    }catch (Exception error){
                        Alert a = new Alert(Alert.AlertType.ERROR,"The file is not correct");
                        a.setTitle("KARL");
                        a.show();
                    }
                    if (type.get(0).equals("board")){
                        Jeu jeu = new Jeu(null,null);
                        jeu.setBoard(Save.loadBoard(file.getAbsolutePath()));
                        next.setDisable(false);
                        CanvasRenderer.render(canvas,jeu);
                        next.setDisable(false);
                    }else{
                        Alert a = new Alert(Alert.AlertType.ERROR,"The file is not a map file");
                        a.setTitle("KARL");
                        a.show();
                    }
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
