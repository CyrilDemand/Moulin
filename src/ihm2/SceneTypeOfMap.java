package ihm2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SceneTypeOfMap {
    private static Scene scene;

    /**
     * creates a scene
     */
    public static void create(){
        VBox root=new VBox();

        Button generate =new Button("Use a pre-generated map");
        generate.setOnAction(e->{
            SceneSidesMap.switchTo();
        });
        Button load =new Button("Load a custom map");
        load.setOnAction(e->{
            SceneCustomMap.switchTo();
        });
        Button goBack =new Button("Go back");
        goBack.setOnAction(e->{
            SceneNewGame.switchTo();
        });
        Button goBackToMainMenu =new Button("Go back to main menu");
        goBackToMainMenu.setOnAction(e->{
            SceneMainMenu.switchTo();
        });
        root.getChildren().addAll(generate,load,goBack,goBackToMainMenu);
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
