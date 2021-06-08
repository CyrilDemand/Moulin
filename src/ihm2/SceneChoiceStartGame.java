package ihm2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import moulin.Jeu;

public class SceneChoiceStartGame {
    private static Scene scene;

    public static void create(){
        VBox root=new VBox();
        Button randomStart=new Button("Random Start");
        randomStart.setOnAction(e->{
            SceneNewGame.setJeu(Jeu.randomStart(SceneNewGame.getJeu().getBoard(),SceneNewGame.getJeu().getPlayers()));
            SceneNewGame.getJeu().getBoard().render();
        });
        Button start=new Button("Start");
        start.setOnAction(e->{
            Main.changeScene(SceneStartGame.getScene());
        });
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
        root.getChildren().addAll(start,randomStart,buttonBar);
        scene=new Scene(root,Main.getDefaultSceneWidth(),Main.getDefaultSceneHeight());
    }

    public static Scene getScene(){
        return scene;
    }
}
