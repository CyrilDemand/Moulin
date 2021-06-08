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
import moulin.ColorEnum;
import moulin.Config;
import moulin.Player;

import java.util.ArrayList;

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
        next.setOnAction(e->{
            ArrayList<Player> players = new ArrayList<>();
            for (PlayerHBox pb:SceneChoixJoueurs.players.getItems()) {
                if (pb!=null){
                    System.out.println(pb);
                    if (pb.isHuman()){
                        players.add(pb.getPlayer());
                    }else{
                        players.add(pb.getAI());
                    }
                }
            }
            System.out.println(players);
        });
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

}
