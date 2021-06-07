package ihm2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import moulin.Board;
import moulin.Jeu;

public class SceneSidesMap {
    private static Scene scene;

    public static void create(){

        VBox root=new VBox();
        Spinner<Integer> spinner = new Spinner<>(3,10,3,1);
        Button loadButton = new Button("load");
        loadButton.setOnAction(actionEvent -> {
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
        Button goBackToMainMenu =new Button("go back to main menu");
        goBack.setOnAction(e->{
            Main.changeScene(SceneMainMenu.getScene());
        });
        root.getChildren().addAll(spinner,loadButton,goBack,goBackToMainMenu);
        scene=new Scene(root,1000,500);
    }

    public static Scene getScene(){
        return scene;
    }
}
