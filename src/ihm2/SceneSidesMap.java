package ihm2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import moulin.Board;

public class SceneSidesMap {
    private static Scene scene;

    public static void create(){
        VBox root=new VBox();
        Spinner<Integer> spinner = new Spinner<>(3,10,3,1);
        Button loadButton = new Button("load");
        loadButton.setOnAction(actionEvent -> {
            SceneNewGame.getJeu().setBoard(Board.generateBoard(spinner.getValue()));
            SceneNewGame.getJeu().getBoard().render();
        });
        root.getChildren().addAll(spinner,loadButton);
        scene=new Scene(root,1000,500);
    }

    public static Scene getScene(){
        return scene;
    }
}
