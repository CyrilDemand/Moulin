package ihm2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import moulin.Jeu;
import moulin.Player;

import java.util.ArrayList;
import java.util.List;

public class SceneNewGame {

    private static Scene scene;
    private static Jeu jeu;


    public static void create(){
        VBox root=new VBox();
        Label title = new Label("New/Load a game");
        Button newGame = new Button("New game");
        newGame.setOnAction(e->{
            SceneTypeOfMap.switchTo();
        });
        Button loadGame = new Button("Load a game file");
        loadGame.setOnAction(e->{
            jeu=null;
            SceneAskFile.switchTo();
        });
        Button returnButton =new Button("Go back");
        returnButton.setOnAction(e->{
            SceneMainMenu.switchTo();
        });
        root.getChildren().addAll(title,newGame,loadGame,returnButton);
        scene=new Scene(root,Main.getDefaultSceneWidth(),Main.getDefaultSceneHeight());
    }

    public static Jeu getJeu() {
        return jeu;
    }

    public static void switchTo(){
        Main.changeScene(scene);
    }

    public static void setPlayers(ArrayList<Player> players){
        jeu.setPlayers(players);
    }

    public static void setJeu(Jeu jeu) {
        SceneNewGame.jeu = jeu;
    }

    public static Scene getScene(){
        return scene;
    }
}
