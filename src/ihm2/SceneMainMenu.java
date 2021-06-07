package ihm2;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SceneMainMenu{

    private static Scene scene;

    public static void create(){
        VBox root=new VBox();



        SwitchSceneButton playButton=new SwitchSceneButton("Play",SceneTest.getScene());
        SwitchSceneButton optionButton=new SwitchSceneButton("Options",null);
        SwitchSceneButton rulesButton=new SwitchSceneButton("Rules",null);
        SwitchSceneButton quitButton=new SwitchSceneButton("Quit",null);

        root.getChildren().addAll(new Label("The Mill Game"),playButton,optionButton,rulesButton,quitButton);

        scene=new Scene(root,1000,500);
    }

    public static Scene getScene(){
        return scene;
    }
}
