package ihm2;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import moulin.*;

public class SceneStartGame {
    private static Scene scene;

    private static CustomCanvas customCanvas;

    public static void create(){
        VBox root=new VBox();
        customCanvas = new CustomCanvas(800,600,true,false);
        Button nextTurn=new Button("Next Turn");
        nextTurn.setDisable(true);

        customCanvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                customCanvas.select(SceneNewGame.getJeu());
                nextTurn.setDisable(customCanvas.getSelectedNode()==null);
            }
        });
        root.getChildren().addAll(customCanvas,nextTurn);

        nextTurn.setOnAction(e->{
            Jeu jeu=SceneNewGame.getJeu();
            //ICI
            nextTurn.setDisable(true);
            customCanvas.unselect(jeu);
        });

        scene=new Scene(root,Main.getDefaultSceneWidth(),Main.getDefaultSceneHeight());
    }

    public static Scene getScene(){
        return scene;
    }

    public static void switchTo(){
        Main.changeScene(scene);
    }
}
