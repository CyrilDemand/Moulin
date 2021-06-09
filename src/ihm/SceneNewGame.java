package ihm;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import moulin.Jeu;
import moulin.Player;

import java.util.ArrayList;

/**
 * creates a scene that allows you to create a new game
 */
public class SceneNewGame {

    private static Scene scene;
    private static Jeu jeu;

    /**
     * creates a scene
     */
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

    /**
     * gets the current game
     * @return the game
     */
    public static Jeu getJeu() {
        return jeu;
    }

    /**
     * switches to the scene
     */
    public static void switchTo(){
        Main.changeScene(scene);
    }

    /**
     * sets the players arraylist
     * @param players current player list
     */
    public static void setPlayers(ArrayList<Player> players){
        jeu.setPlayers(players);
    }

    /**
     * sets the game
     * @param jeu game to set
     */
    public static void setJeu(Jeu jeu) {
        SceneNewGame.jeu = jeu;
    }

    /**
     * gets the scene
     * @return the scene
     */
    public static Scene getScene(){
        return scene;
    }
}
