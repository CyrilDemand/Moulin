package ihm2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SceneTypeOfMap {
    private static Scene scene;

    public static void create(){
        VBox root=new VBox();

        Button generate =new Button("Use a pre-generated map");
        generate.setOnAction(e->{
            Main.changeScene(SceneSidesMap.getScene());
        });
        Button load =new Button("Load a custom map");
        load.setOnAction(e->{
            Main.changeScene(SceneCustomMap.getScene());
        });
        Button goBack =new Button("Go back");
        goBack.setOnAction(e->{
            Main.changeScene(SceneNewGame.getScene());
        });
        Button goBackToMainMenu =new Button("Go back to main menu");
        goBackToMainMenu.setOnAction(e->{
            Main.changeScene(SceneMainMenu.getScene());
        });
        root.getChildren().addAll(generate,load,goBack,goBackToMainMenu);
        scene=new Scene(root,Main.getDefaultSceneWidth(),Main.getDefaultSceneHeight());
    }

    public static Scene getScene(){
        return scene;
    }
}
