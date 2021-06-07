package ihm2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SceneMainMenu{

    private static Scene scene;

    public static void create(){
        VBox root=new VBox();



        Button playButton=new Button("Play");
        playButton.setOnAction(e->{Main.stage.setScene(SceneTest.getScene());});
        Button optionButton=new Button("Options");
        Button rulesButton=new Button("Rules");
        Button quitButton=new Button("Quit");

        root.getChildren().addAll(new Label("The Mill Game"),playButton,optionButton,rulesButton,quitButton);

        scene=new Scene(root,1000,500);
    }

    public static Scene getScene(){
        return scene;
    }
}
