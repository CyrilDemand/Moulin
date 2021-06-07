package ihm2;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class SceneChoixJoueurs {

    private static Scene scene;

    public static void create(){
        VBox root=new VBox();
        ListView<PlayerVBox> vBoxListView = new ListView<>();

        scene=new Scene(root,1000,500);
    }

    public static Scene getScene(){
        return scene;
    }
}
