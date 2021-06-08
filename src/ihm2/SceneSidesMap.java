package ihm2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import moulin.Board;
import moulin.Jeu;

import java.awt.*;

public class SceneSidesMap {
    private static Scene scene;

    public static void create(){

        VBox root=new VBox();
        HBox buttonBar=new HBox();
        Canvas canvas = new Canvas(100,100);
        Spinner<Integer> spinner = new Spinner<>(3,10,3,1);
        spinner.valueProperty().addListener((obs, oldValue, newValue) ->
                {
            Jeu jeu = new Jeu(null,null);
            int side = spinner.getValue();
            jeu.setBoard(Board.generateBoard(side));
            SceneNewGame.setJeu(jeu);
            SceneNewGame.getJeu().getBoard().render();
        });
        Button goBack =new Button("Go back");
        goBack.setOnAction(e->{
            Main.changeScene(SceneTypeOfMap.getScene());
        });
        Button goBackToMainMenu=new Button("Go Back to main menu");
        goBackToMainMenu.setOnAction(e->{
            Main.changeScene(SceneMainMenu.getScene());
        });
        Button next=new Button("Next");
        next.setDisable(true);
        buttonBar.getChildren().addAll(goBackToMainMenu,goBack,next);
        root.getChildren().addAll(canvas,spinner,buttonBar);
        scene=new Scene(root,1000,500);
    }

    public static Scene getScene(){
        return scene;
    }
}
