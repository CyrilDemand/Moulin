package ihm2;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import moulin.Jeu;
import moulin.Node;
import moulin.Save;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SceneAskFile {
    private static Scene scene;

    public static void create() throws JSONException, IOException {
        VBox root=new VBox();
        CustomCanvas canvas = new CustomCanvas(800,600);

        HBox buttonBar=new HBox();
        Button goBackToMainMenu=new Button("Go Back to main menu");
        goBackToMainMenu.setOnAction(e->{
            SceneMainMenu.switchTo();
        });
        Button goBack=new Button("Go Back");
        goBack.setOnAction(e->{
            SceneNewGame.switchTo();
        });
        Button next=new Button("Next");
        next.setDisable(true);
        next.setOnAction(e->{
            SceneChoixJoueurs.switchTo();
        });
        buttonBar.getChildren().addAll(goBackToMainMenu,goBack,next);

        Button loadGame = new Button("Load a game file");
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
                        a.setTitle("Error");
                        a.show();
                    }
                    if (type.get(0).equals("game")){
                        Jeu jeu = new Jeu(Save.loadJeu(file.getAbsolutePath()));
                        SceneNewGame.setJeu(jeu);
                        next.setDisable(false);
                        jeu.getBoard().render();
                        canvas.render(SceneNewGame.getJeu());
                        SceneNewGame.getJeu().getBoard().render();
                    }else{
                        Alert a = new Alert(Alert.AlertType.ERROR,"The file is not a game file");
                        a.setTitle("Error");
                        a.show();
                    }
                } catch (JSONException | IOException jsonException) {
                    jsonException.printStackTrace();
                }

            }
        });


        root.getChildren().addAll(canvas,loadGame,buttonBar);

        scene=new Scene(root,Main.getDefaultSceneWidth(),Main.getDefaultSceneHeight());
    }

    public static void switchTo(){
        SceneNewGame.setJeu(null);
        Main.changeScene(scene);
    }

    public static Scene getScene(){
        return scene;
    }
}
