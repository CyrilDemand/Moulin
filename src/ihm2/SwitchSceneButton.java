package ihm2;

import ihm.MainMenuButton;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SwitchSceneButton extends Button {

    private static Stage stage;

    private Scene sceneToSwitch;

    public SwitchSceneButton(String name,Scene sceneToSwitch){
        super(name);
        this.sceneToSwitch=sceneToSwitch;


        if (sceneToSwitch==null){
            this.setOnAction(e->{ Main.stage.close(); });
        }else{
            this.setOnAction(e->{ Main.stage.setScene(sceneToSwitch); });
        }
    }

    public Scene getSceneToSwitch() {
        return sceneToSwitch;
    }
}
