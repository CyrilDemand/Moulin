package ihm2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import moulin.Config;

public class SceneChoixJoueurs {

    private static Scene scene;

    public static ListView<PlayerHBox> players = new ListView<>();

    private static Button addButton = new Button("+");
    public static void create(){
        VBox root=new VBox();

        for (int i = 0;i<2;i++){
            players.getItems().add(new PlayerHBox());
        }
        SceneChoixJoueurs.delButton();
        addButton.setOnAction(e->{
            players.getItems().add(new PlayerHBox());
            if (players.getItems().size()==Config.playerMax){
                addButton.setDisable(true);
            }
            SceneChoixJoueurs.delButton();
        });
        HBox buttonBar=new HBox();
        Button goBackToMainMenu=new Button("Go Back to main menu");
        goBackToMainMenu.setOnAction(e->{
            Main.changeScene(SceneMainMenu.getScene());
        });
        Button goBack=new Button("Go Back");
        goBack.setOnAction(e->{
            Main.changeScene(SceneNewGame.getScene());
        });
        Button next=new Button("Next");
        next.setDisable(true);
        buttonBar.getChildren().addAll(goBackToMainMenu,goBack,next);
        root.getChildren().addAll(addButton,players,buttonBar);
        scene=new Scene(root,1000,500);
    }

    public static Scene getScene(){
        return scene;
    }

    public static void delButton(){
        for (PlayerHBox p:SceneChoixJoueurs.players.getItems()){
            if (players.getItems().size()==2){
                p.getbDelete().setDisable(true);
            }else{
                p.getbDelete().setDisable(false);
            }
        }
        if (players.getItems().size()==Config.playerMax){
            addButton.setDisable(true);
        }
    }

    public void setColor(ComboBox<String> c){
        for (PlayerHBox pb : players.getItems()){
            for (ComboBox<String> cb : pb.getColors()){

            }
        }
        colors.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!colors.getItems().contains(s)){
                    colors.getItems().add(s);
                }
                colors.getItems().
            }
        });
    }
}
