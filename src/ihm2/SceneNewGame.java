package ihm2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import moulin.Jeu;

public class SceneNewGame {

    private static Scene scene;
    private static Jeu jeu;


    public static void create(){
        VBox root=new VBox();
        Label title = new Label("New/Load a game");
        Button newGame = new Button("New game");
        newGame.setOnAction(e->{

        });
        Button loadGame = new Button("Load a game file");
        loadGame.setOnAction(e->{
            Main.changeScene(SceneAskFile.getScene());
        });
        Button returnButton =new Button("Go back");
        returnButton.setOnAction(e->{
            Main.changeScene(SceneMainMenu.getScene());
        });
        root.getChildren().addAll(title,newGame,loadGame,returnButton);
        scene=new Scene(root,1000,500);
    }

    public static Jeu getJeu() {
        return jeu;
    }

    public static void setJeu(Jeu jeu) {
        SceneNewGame.jeu = jeu;
    }

    public static Scene getScene(){
        return scene;
    }
}
