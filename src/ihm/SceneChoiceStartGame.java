package ihm;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import moulin.Jeu;

/**
 * creates a scene to start the game
 */

public class SceneChoiceStartGame {
    private static Scene scene;

    /**
     * creates a scene
     */
    public static void create(){
        VBox root=new VBox();
        Button randomStart=new Button("Random Start");
        randomStart.setOnAction(e->{
            SceneNewGame.setJeu(Jeu.randomStart(SceneNewGame.getJeu().getBoard(),SceneNewGame.getJeu().getPlayers()));
            SceneNewGame.getJeu().getBoard().render();
        });
        Button start=new Button("Start");
        start.setOnAction(e->{
            SceneStartGame.switchTo();
        });
        HBox buttonBar=new HBox();
        Button goBackToMainMenu=new Button("Go Back to main menu");
        goBackToMainMenu.setOnAction(e->{
            SceneMainMenu.switchTo();
        });
        Button goBack=new Button("Go Back");
        goBack.setOnAction(e->{
            SceneTypeOfMap.switchTo();
        });
        Button next=new Button("Next");
        next.setDisable(true);
        next.setOnAction(e->{
            SceneChoixJoueurs.switchTo();
        });
        buttonBar.getChildren().addAll(goBackToMainMenu,goBack,next);
        root.getChildren().addAll(start,randomStart,buttonBar);
        scene=new Scene(root,Main.getDefaultSceneWidth(),Main.getDefaultSceneHeight());
    }

    /**
     * switches to the scene
     */
    public static void switchTo(){
        Main.changeScene(scene);
    }

    /**
     * gets the scene
     * @return the scene
     */
    public static Scene getScene(){
        return scene;
    }
}
