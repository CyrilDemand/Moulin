package ihm2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SceneTypeOfMap {
    private static Scene scene;

    public static void create(){
        VBox root=new VBox();

        Button generate =new Button("generate a map with x side");
        generate.setOnAction(e->{
            Main.changeScene(SceneSidesMap.getScene());
        });
        Button load =new Button("load a custom map");
        load.setOnAction(e->{
            Main.changeScene(SceneCustomMap.getScene());
        });
        Button goBack =new Button("go back");
        goBack.setOnAction(e->{
            Main.changeScene(SceneNewGame.getScene());
        });
        Button goBackToMainMenu =new Button("go back to main menu");
        goBack.setOnAction(e->{
            Main.changeScene(SceneMainMenu.getScene());
        });
        root.getChildren().addAll(generate,load,goBack,goBackToMainMenu);
        scene=new Scene(root,1000,500);
    }

    public static Scene getScene(){
        return scene;
    }
}
