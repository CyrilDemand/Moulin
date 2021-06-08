package ihm2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class SceneChoixJoueurs {

    private static Scene scene;

    public static ListView<PlayerHBox> players = new ListView<>();
    public static void create(){
        VBox root=new VBox();;
        Button addButton = new Button("+");
        addButton.setOnAction(e->{
            players.getItems().add(new PlayerHBox());
        });
        root.getChildren().addAll(addButton,players);
        scene=new Scene(root,1000,500);
    }

    public static Scene getScene(){
        return scene;
    }
}
