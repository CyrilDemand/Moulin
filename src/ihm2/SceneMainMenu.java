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
        Button optionButton=new Button("Options");
        optionButton.setOnAction(e->{
            Main.stage.setScene(SceneOptions.getScene());
        });
        Button rulesButton=new Button("Rules");
        rulesButton.setOnAction(e->{
            Main.stage.setScene(SceneRules.getScene());
        });
        Button quitButton=new Button("Quit");
        quitButton.setOnAction(e->{
            Main.stage.close();
        });

        root.getChildren().addAll(new Label("The Mill Game"),playButton,optionButton,rulesButton,quitButton);

        scene=new Scene(root,1000,500);
    }

    public static Scene getScene(){
        return scene;
    }
}
